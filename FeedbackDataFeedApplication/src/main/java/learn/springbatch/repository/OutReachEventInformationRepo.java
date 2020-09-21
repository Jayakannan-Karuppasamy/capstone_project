package learn.springbatch.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springbatch.model.OutReachEventInformation;

public interface OutReachEventInformationRepo extends MongoRepository<OutReachEventInformation, BigInteger> {

}
