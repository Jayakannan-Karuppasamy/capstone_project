package learn.reactive.demo.repoistory;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.Volunteer_Enrollment_Details_Unregistered;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VolunteerEnrollDtlsUnregisteredRepo
		extends ReactiveMongoRepository<Volunteer_Enrollment_Details_Unregistered, BigInteger> {

	Mono<Volunteer_Enrollment_Details_Unregistered> findByemployeeId(String eid);


}
