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
import learn.reactive.demo.model.NotParticipatedFeedback;
import learn.reactive.demo.repoistory.EmailNotParticipatedFeedbackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class EmailNotParticipatedFeedbackTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	EmailNotParticipatedFeedbackRepo repo;

	@Test
	public void getAllNotParticipated() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(NotParticipatedFeedback.class)
			.hasSize(3);
	}

	@Test
	public void getAllNotParticipated_approach1() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(NotParticipatedFeedback.class)
			.hasSize(3).consumeWith((response) -> {
				List<NotParticipatedFeedback> roles= response.getResponseBody();
				roles.forEach((r) -> {
					assertTrue(r.getEmployeeId() !=null);
				});
			});
	}
	
	
	@Test
	public void getAllNotParticipated_approach2() {
		Flux<NotParticipatedFeedback> notParticipatedFlux=webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.returnResult(NotParticipatedFeedback.class)
			.getResponseBody();
		
		StepVerifier.create(notParticipatedFlux.log("Value from not participated  :::"))
					.expectNextCount(3)
					.verifyComplete();
	}
	
	@Test
	public void getNotParticipatedEmpID() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/{eid}"),1)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.eventId", 1);
	}
	
	@Test
	public void getNotParticipatedEmpID_NOTFOUND() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/{eid}"),6)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	
	@Test
	public void createNotParticipated() {
		NotParticipatedFeedback notParticipated=new NotParticipatedFeedback(4,4,4,2,1);
		
		webTestClient.post().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/save"))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(notParticipated), NotParticipatedFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.employeeId").isNotEmpty()
			.jsonPath("$.eventId").isEqualTo(4);
	}
	
	@Test
	public void deleteNotParticipated() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/delete/{eid}"),3)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Void.class);
	}
	
	@Test
	public void deleteNotParticipated_NOTFOUND() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/delete/{eid}"),6)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	
	@Test
	public void updateNotParticipated() {
		NotParticipatedFeedback notParticipated=new NotParticipatedFeedback(4,4,3,2,1);
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/update/{eid}"),4)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(notParticipated),NotParticipatedFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.eventId", 3);
	}
	
	@Test
	public void updateNotParticipated_NOTFOUND() {
		NotParticipatedFeedback notParticipated=new NotParticipatedFeedback(6,4,4,2,1);
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT.concat("/update/{eid}"),6)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(notParticipated),NotParticipatedFeedback.class)
			.exchange()
			.expectStatus().isNotFound();
		
	}
}
