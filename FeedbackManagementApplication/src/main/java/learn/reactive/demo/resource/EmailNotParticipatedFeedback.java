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

import learn.reactive.demo.constant.FeedbackConstant;
import learn.reactive.demo.model.NotParticipatedFeedback;
import learn.reactive.demo.repoistory.EmailNotParticipatedFeedbackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(FeedbackConstant.ADMIN_MAIL_NOT_PARTICIPATED_ENDPOINT)
public class EmailNotParticipatedFeedback {

	@Autowired
	EmailNotParticipatedFeedbackRepo repo;
	
	@GetMapping("/all")
	public Flux<NotParticipatedFeedback> getAllNotParticipated() {
		return repo.findAll();
	}
	
	@GetMapping("/{eid}")
	public Mono<ResponseEntity<NotParticipatedFeedback>> getParticipatedEmployeeId(@PathVariable Integer eid) {
		return repo.findByemployeeId(eid).map(fetchId -> ResponseEntity.ok(fetchId))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/save")
	public Mono<ResponseEntity<NotParticipatedFeedback>> addFeedback(@RequestBody NotParticipatedFeedback questions) {
		return repo.save(questions).map(question -> ResponseEntity.ok(question));
	}
	
	@PutMapping("/update/{eid}")
	public Mono<ResponseEntity<NotParticipatedFeedback>> updateFeedbackbyEmpId(@PathVariable Integer eid,
			@RequestBody NotParticipatedFeedback feedbackNotParticipated) {
		
		return repo.findByemployeeId(eid).flatMap(existing -> {
			existing.setEmployeeId(feedbackNotParticipated.getEmployeeId());
			existing.setEventId(feedbackNotParticipated.getEventId());
			existing.setQuestionId(feedbackNotParticipated.getQuestionId());
			existing.setAnswerId(feedbackNotParticipated.getAnswerId());
			return repo.save(existing);
		}).map(updated-> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/delete/{eid}")
	public Mono<ResponseEntity<Void>> deleteEmpId(@PathVariable Integer eid) {
		return repo.findByemployeeId(eid)
				.flatMap(existing -> repo.delete(existing)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
}
