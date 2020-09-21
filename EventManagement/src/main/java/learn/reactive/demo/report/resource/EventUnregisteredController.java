package learn.reactive.demo.report.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import learn.reactive.demo.constant.EventConstant;
import learn.reactive.demo.model.Volunteer_Enrollment_Details_Unregistered;
import learn.reactive.demo.repoistory.VolunteerEnrollDtlsUnregisteredRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*@CrossOrigin(value = {"http://localhost:3000"}, allowedHeaders = {"Baeldung-Allowed"}, maxAge = 1800)*/
@RestController
@RequestMapping(EventConstant.EVENT_EMP_REGISTERED)
public class EventUnregisteredController {

	@Autowired
	VolunteerEnrollDtlsUnregisteredRepo unRegisteredRepo;
	
	
	@GetMapping(value = "/all")
	public Flux<Volunteer_Enrollment_Details_Unregistered> getAllRegistered() {
		return unRegisteredRepo.findAll();
	}
	
	@GetMapping(value = "/{eid}")
	public Mono<ResponseEntity<Volunteer_Enrollment_Details_Unregistered>> getRegisteredEmployeeId(@PathVariable String eid) {
		return unRegisteredRepo.findByemployeeId(eid).map(fetchId -> ResponseEntity.ok(fetchId))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	//@RequestBody Volunteer_Enrollment_Details_Unregistered registered
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Volunteer_Enrollment_Details_Unregistered> addRegistered(@RequestBody Volunteer_Enrollment_Details_Unregistered registered) {
		 System.out.println(registered.getEmployeeId() + " " + registered.getEventDate() );
		return unRegisteredRepo.save(registered);
	}
	
	@PutMapping("/updateEvent/{id}")
	public Mono<ResponseEntity<Volunteer_Enrollment_Details_Unregistered>> updateEventByEmpId(@PathVariable String id,
			@RequestBody Volunteer_Enrollment_Details_Unregistered newEvent) {
		return unRegisteredRepo.findByemployeeId(id)
				.flatMap(existingEmpEvent -> {
			existingEmpEvent.setEventName(newEvent.getEventName());
			return unRegisteredRepo.save(existingEmpEvent);
		}).map(updateEvent -> new ResponseEntity<>(updateEvent, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<ResponseEntity<Void>> deleteEmpId(@PathVariable String id){
		return unRegisteredRepo.findByemployeeId(id)
				.flatMap(existingEmp->unRegisteredRepo.delete(existingEmp).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
						).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
}
