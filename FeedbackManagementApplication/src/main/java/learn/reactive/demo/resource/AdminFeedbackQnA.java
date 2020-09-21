package learn.reactive.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.reactive.demo.model.FeedbackQuestions;
import learn.reactive.demo.repoistory.AdminFeedbackQuestionAndAnswerRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// Feedback Question and Answer configuration resource
@RestController
@RequestMapping("/admin/FeadbackQnA")
public class AdminFeedbackQnA {

	@Autowired
	private AdminFeedbackQuestionAndAnswerRepo feedbackQnARepo;

	// Get all Feedback Question and Answer by Admin
	@GetMapping("/all")
	public Flux<FeedbackQuestions> getAllFeedbackQuestions() {
		return feedbackQnARepo.findAll();
	}

	// Get Question Id Feedback Question and Answer by Admin
	@GetMapping("/{qid}")
	public Mono<ResponseEntity<FeedbackQuestions>> getQuestionId(@PathVariable Integer qid) {
		return feedbackQnARepo.findByquestionId(qid).map(fetchId -> ResponseEntity.ok(fetchId))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	// Create New Feedback Question and Answer by Admin
	@PostMapping("/createQnA")
	public Mono<ResponseEntity<FeedbackQuestions>> addRole(@RequestBody FeedbackQuestions questions) {
		return feedbackQnARepo.save(questions).map(question -> ResponseEntity.ok(question));
	}

	// Edit Feedback Question and Answer by Admin
	@PutMapping("/update/{qid}")
	public Mono<ResponseEntity<FeedbackQuestions>> updateQuestionId(@PathVariable Integer qid,
			@RequestBody FeedbackQuestions feedbackQnA) {
		return feedbackQnARepo.findByquestionId(qid).flatMap(existingQnA -> {
			existingQnA.setQuestion(feedbackQnA.getQuestion());
			existingQnA.setFeedbackType(feedbackQnA.getFeedbackType());
			existingQnA.setQuestionType(feedbackQnA.getQuestionType());
			existingQnA.setFeedbackAnswer(feedbackQnA.getFeedbackAnswer());
			return feedbackQnARepo.save(existingQnA);
		}).map(updateQnA -> new ResponseEntity<>(updateQnA, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Remove Feedback Question and Answer by Admin
	@DeleteMapping("/delete/{qid}")
	public Mono<ResponseEntity<Void>> deleteQuestionId(@PathVariable Integer qid) {
		return feedbackQnARepo.findByquestionId(qid)
				.flatMap(existingQnA -> feedbackQnARepo.delete(existingQnA)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
