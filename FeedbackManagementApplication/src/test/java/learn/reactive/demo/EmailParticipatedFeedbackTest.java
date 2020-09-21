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
import learn.reactive.demo.model.ParticipatedFeedback;
import learn.reactive.demo.repoistory.EmailParticipatedFeedbackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class EmailParticipatedFeedbackTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Autowired
	EmailParticipatedFeedbackRepo participatedRepo;
	
	@Test
	public void getAllParticipatedFeedback() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(ParticipatedFeedback.class)
			.hasSize(2);
	}
	
	@Test
	public void getAllParticipatedFeedback_approach1() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBodyList(ParticipatedFeedback.class)
			.hasSize(3).consumeWith((response) -> {
				List<ParticipatedFeedback> roles= response.getResponseBody();
				roles.forEach((r) -> {
					assertTrue(r.getEmployeeId() !=null);
				});
			});
	}
	
	@Test
	public void getAllParticipatedFeedback_approach2() {
		Flux<ParticipatedFeedback> empRoleFlux=webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/all"))
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.returnResult(ParticipatedFeedback.class)
			.getResponseBody();
		
		StepVerifier.create(empRoleFlux.log("Value from Employee Role :::"))
					.expectNextCount(3)
					.verifyComplete();
	}
	
	@Test
	public void getParticipatedFeedbackByEmpID() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/{eid}"),1)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.activityLike", "Employee Feedback for ActivityLike");
	}

	@Test
	public void getParticipatedFeedbackBy_NOTFOUND() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/{eid}"),3)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	@Test
	public void createParticipatedFeedback() {
		ParticipatedFeedback role=new ParticipatedFeedback(3,3,3,4,"activity like","activityImproved");
		webTestClient.post().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/save"))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(role), ParticipatedFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.employeeId").isNotEmpty()
			.jsonPath("$.rating").isEqualTo(4);
				
	}
	
	@Test
	public void deleteRole() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/delete/{eid}"),3)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isOk()
			.expectBody(Void.class);
	}
	
	@Test
	public void deleteRole_NOTFOUND() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/delete/{eid}"),6)
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus().isNotFound();
	}
	
	//Update the role by employeeId
	@Test
	public void updateEmployeeRole() {
		ParticipatedFeedback role=new ParticipatedFeedback(1,1,1,4,"Employee Feedback for ActivityLike","Employee Feedback for activityImproved");
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/update/{eid}"),1)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(role),ParticipatedFeedback.class)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$.eventId", 4);
	}
	
	@Test
	public void updateEmployeeRole_NOTFOUND() {
		ParticipatedFeedback role=new ParticipatedFeedback(1,1,1,4,"Employee Feedback for ActivityLike","Employee Feedback for activityImproved");
		
		webTestClient.put().uri(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT.concat("/update/{eid}"),6)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(Mono.just(role),ParticipatedFeedback.class)
			.exchange()
			.expectStatus().isNotFound();
		
	}
}
