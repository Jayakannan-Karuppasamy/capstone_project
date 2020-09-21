package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.NotParticipatedFeedback;
import reactor.core.publisher.Mono;

public interface EmailNotParticipatedFeedbackRepo extends ReactiveMongoRepository<NotParticipatedFeedback, Integer> {
	public Mono<NotParticipatedFeedback> findByemployeeId(Integer employeeId);
}
