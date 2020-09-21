package learn.springbatch.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springbatch.model.OutReachEventsSummary;

public interface OutReachEventsSummaryRepo extends MongoRepository<OutReachEventsSummary, BigInteger> {

}
