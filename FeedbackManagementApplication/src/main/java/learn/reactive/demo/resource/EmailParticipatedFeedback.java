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
import learn.reactive.demo.model.ParticipatedFeedback;
import learn.reactive.demo.repoistory.EmailParticipatedFeedbackRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(FeedbackConstant.ADMIN_MAIL_PARTICIPATED_ENDPOINT)
public class EmailParticipatedFeedback {

	@Autowired
	EmailParticipatedFeedbackRepo participatedRepo;
	
	@GetMapping("/all")
	public Flux<ParticipatedFeedback> getAllParticipatedFeedback() {
		return participatedRepo.findAll();
	}
	
	@GetMapping("/{eid}")
	public Mono<ResponseEntity<ParticipatedFeedback>> getParticipatedEmployeeId(@PathVariable Integer eid) {
		return participatedRepo.findByemployeeId(eid).map(fetchId -> ResponseEntity.ok(fetchId))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/save")
	public Mono<ResponseEntity<ParticipatedFeedback>> addFeedback(@RequestBody ParticipatedFeedback questions) {
		return participatedRepo.save(questions).map(question -> ResponseEntity.ok(question));
	}
	
	@PutMapping("/update/{eid}")
	public Mono<ResponseEntity<ParticipatedFeedback>> updateFeedbackbyEmpId(@PathVariable Integer eid,
			@RequestBody ParticipatedFeedback feedbackParticipated) {
		
		return participatedRepo.findByemployeeId(eid).flatMap(existing -> {
			existing.setEmployeeId(feedbackParticipated.getEmployeeId());
			existing.setEventId(feedbackParticipated.getEventId());
			existing.setRating(feedbackParticipated.getRating());
			existing.setActivityLike(feedbackParticipated.getActivityLike());
			existing.setActivityImproved(feedbackParticipated.getActivityImproved()); 
			return participatedRepo.save(existing);
		}).map(updated-> new ResponseEntity<>(updated, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@DeleteMapping("/delete/{eid}")
	public Mono<ResponseEntity<Void>> deleteEmpId(@PathVariable Integer eid) {
		return participatedRepo.findByemployeeId(eid)
				.flatMap(existing -> participatedRepo.delete(existing)
						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
				.defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
}
