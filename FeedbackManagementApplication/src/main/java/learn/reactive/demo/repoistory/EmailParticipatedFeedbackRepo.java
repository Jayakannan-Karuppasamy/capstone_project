package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.ParticipatedFeedback;
import reactor.core.publisher.Mono;

public interface EmailParticipatedFeedbackRepo extends ReactiveMongoRepository<ParticipatedFeedback, Integer>{
	public Mono<ParticipatedFeedback> findByemployeeId(Integer employeeId);
}
