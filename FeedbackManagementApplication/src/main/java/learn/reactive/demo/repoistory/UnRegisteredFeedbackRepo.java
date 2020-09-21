package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.UnRegisteredFeedback;
import reactor.core.publisher.Mono;

public interface UnRegisteredFeedbackRepo extends ReactiveMongoRepository<UnRegisteredFeedback, Integer>{
	public Mono<UnRegisteredFeedback> findByemployeeId(Integer employeeId);
}
