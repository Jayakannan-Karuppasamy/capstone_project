package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.Volunteer_Enrollment_Details_Not_Attend;

public interface EnrollDetailsNotAttendRepo extends ReactiveMongoRepository<Volunteer_Enrollment_Details_Not_Attend, String>{

}
