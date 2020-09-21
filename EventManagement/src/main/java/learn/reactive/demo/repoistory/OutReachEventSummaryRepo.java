package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.OutReachEventsSummary;

public interface OutReachEventSummaryRepo extends ReactiveMongoRepository<OutReachEventsSummary, String>{

}
