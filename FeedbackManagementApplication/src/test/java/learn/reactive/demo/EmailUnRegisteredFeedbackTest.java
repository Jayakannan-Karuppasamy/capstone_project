package learn.reactive.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import learn.reactive.demo.constant.FeedbackConstant;
import learn.reactive.demo.model.EmployeeRole;
import learn.reactive.demo.model.UnRegisteredFeedback;
import learn.reactive.demo.repoistory.UnRegisteredFeedbackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class EmailUnRegisteredFeedbackTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	UnRegisteredFeedbackRepo repo;

	@Test
	public void getAllUnRegisteredFeedback() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(UnRegisteredFeedback.class)
			.hasSize(4);
	}

	@Test
	public void getAllAllUnRegisteredFeedback_approach1() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(UnRegisteredFeedback.class)
			.hasSize(4).consumeWith((response) -> {
				List<UnRegisteredFeedback> roles= response.getResponseBody();
				roles.forEach((r) -> {
					assertTrue(r.getEmployeeId() !=null);
				});
			});
	}
	
	@Test
	public void getAllAllUnRegisteredFeedback_approach2() {
		Flux<UnRegisteredFeedback> empRoleFlux=webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.returnResult(UnRegisteredFeedback.class)
			.getResponseBody();
		
		StepVerifier.create(empRoleFlux.log("Value from Employee Role :::"))
					.expectNextCount(4)
					.verifyComplete();
	}
	
	
	@Test
	public void getUnRegisteredFeedbackEmpID() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/{eid}"),1)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.employeeId", 1);
	}

	@Test
	public void getAllUnRegisteredFeedbackEmpID_NOTFOUND() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/{eid}"),3)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	
	
	@Test
	public void createUnRegisteredFeedback() {
		UnRegisteredFeedback unReg=new UnRegisteredFeedback(5,5,5,5,1);
		webTestClient.post().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/save"))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(unReg), UnRegisteredFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.employeeId").isNotEmpty()
			.jsonPath("$.questionId").isEqualTo(5);
				
	}
	
	@Test
	public void deleteUnRegisteredFeedback() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/delete/{eid}"),4)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Void.class);
	}
	
	@Test
	public void deleteUnRegisteredFeedback_NOTFOUND() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/delete/{eid}"),6)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	public void updateUnRegisteredFeedback() {
		UnRegisteredFeedback unReg=new UnRegisteredFeedback(1,1,2,2,1);
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/update/{eid}"),1)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(unReg),UnRegisteredFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.eventId", 2);
	}
	
	@Test
	public void updateUnRegisteredFeedback_NOTFOUND() {
		UnRegisteredFeedback unReg=new UnRegisteredFeedback(3,3,3,3,1);
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_UNREGISTERED_ENDPOINT.concat("/update/{eid}"),3)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(unReg),UnRegisteredFeedback.class)
			.exchange()
			.expectStatus().isNotFound();
		
	}
}
