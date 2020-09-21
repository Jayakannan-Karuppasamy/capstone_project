package learn.springbatch.repository;

import java.math.BigInteger;

import org.springframework.data.mongodb.repository.MongoRepository;

import learn.springbatch.model.Volunteer_Enrollment_Details_Not_Attend;

public interface VolunteerEnrollDtlsNotAttendRepo extends MongoRepository<Volunteer_Enrollment_Details_Not_Attend, BigInteger>{

}
