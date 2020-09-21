package learn.reactive.demo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
import learn.reactive.demo.model.FeedbackAnswer;
import learn.reactive.demo.model.FeedbackQuestions;
import learn.reactive.demo.repoistory.AdminFeedbackQuestionAndAnswerRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class AdminFeedbackQnATest {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	AdminFeedbackQuestionAndAnswerRepo feedbackQnARepo;

	@Test
	public void getAllQuestionAndAnswer() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/all")).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(FeedbackQuestions.class).hasSize(3);
	}

	@Test
	public void getAllQuestionAndAnswer_approach1() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/all")).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(FeedbackQuestions.class).hasSize(3).consumeWith((response) -> {
					List<FeedbackQuestions> roles = response.getResponseBody();
					roles.forEach((r) -> {
						assertTrue(r.getQuestion() != null);
					});
				});
	}

	@Test
	public void getAllQuestionAndAnswer_approach2() {
		Flux<FeedbackQuestions> questionAndAnswerFlux = webTestClient.get()
				.uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/all")).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).returnResult(FeedbackQuestions.class)
				.getResponseBody();

		StepVerifier.create(questionAndAnswerFlux.log("Value from Employee Role :::")).expectNextCount(3)
				.verifyComplete();
	}

	@Test
	public void getQuestionId() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/{qid}"), 1).exchange()
				.expectStatus().isOk().expectBody().jsonPath("$.questionType", "Allow Multiple Answer");
	}

	@Test
	public void getRoleByEmpID_NOTFOUND() {
		webTestClient.get().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/{qid}"), 2).exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void createQuestionAndAnswer() {
		List<FeedbackAnswer> ans = new ArrayList<FeedbackAnswer>();
		FeedbackAnswer answer = new FeedbackAnswer(3, 3, "Third answer");
		ans.add(answer);

		FeedbackQuestions question = new FeedbackQuestions(3, "Third Question", "unRegistered", "Free Text", ans);

		webTestClient.post().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/createQnA"))
				.contentType(MediaType.APPLICATION_JSON).body(Mono.just(question), FeedbackQuestions.class).exchange()
				.expectStatus().isOk().expectBody().jsonPath("$.questionId").isNotEmpty()
				.jsonPath("$.questionType").isEqualTo("Free Text");

	}

	@Test
	public void deleteQuestionAndAnswer() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/delete/{qid}"), 3)
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody(Void.class);
	}

	@Test
	public void deleteQuestionAndAnswer_NOTFOUND() {
		webTestClient.delete().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/delete/{qid}"), 6)
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isNotFound();
	}

	@Test
	public void updateQuestionAndAnswer() {
		
		List<FeedbackAnswer> ans = new ArrayList<FeedbackAnswer>();
		FeedbackAnswer answer = new FeedbackAnswer(1, 1, "first answer");
		ans.add(answer);

		FeedbackQuestions question = new FeedbackQuestions(1, "Fist Question", "participated", "Allow Multiple Answer", ans);

		webTestClient.put().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/update/{qid}"), 1)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(question), FeedbackQuestions.class).exchange().expectStatus().isOk().expectBody()
				.jsonPath("$.empRole", "ADMIN");
	}

	@Test
	public void updateQuestionAndAnswer_NOTFOUND() {
		List<FeedbackAnswer> ans = new ArrayList<FeedbackAnswer>();
		FeedbackAnswer answer = new FeedbackAnswer(6, 6, "Sixth answer");
		ans.add(answer);

		FeedbackQuestions question = new FeedbackQuestions(6, "Sixth Question", "participated", "Allow Multiple Answer", ans);
		webTestClient.put().uri(FeedbackConstant.ADMIN_QUESTION_ANSWER_ENDPOINT.concat("/update/{qid}"), 3)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(question), FeedbackQuestions.class).exchange().expectStatus().isNotFound();

	}

}
