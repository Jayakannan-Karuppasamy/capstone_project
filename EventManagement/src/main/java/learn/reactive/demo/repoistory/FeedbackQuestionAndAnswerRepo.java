package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.FeedbackQuestions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FeedbackQuestionAndAnswerRepo extends ReactiveMongoRepository<FeedbackQuestions, Integer> {
	public Mono<FeedbackQuestions> findByquestionId(Integer questionId);
	public Flux<FeedbackQuestions> findByfeedbackType(String feedbackType);
}
