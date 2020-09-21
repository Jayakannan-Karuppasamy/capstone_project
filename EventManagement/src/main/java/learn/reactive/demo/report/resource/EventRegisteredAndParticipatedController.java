package learn.reactive.demo.report.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.reactive.demo.constant.EventConstant;
import learn.reactive.demo.model.OutReachEventInformation;
import learn.reactive.demo.repoistory.OutReachEventInformationRepo;
import learn.reactive.demo.request.OutReachEventInformationRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(EventConstant.EVENT_REGISTERED)
public class EventRegisteredAndParticipatedController {

	@Autowired
	OutReachEventInformationRepo eventInformationRepo;
	
	//EventInformationReport
	@GetMapping("/eventCount")
	public Mono<Long> totalEventCount(){
		//"EVNT00047261"
		return eventInformationRepo.findByeventID();
	}
	
	@GetMapping("/all")
	public Flux<OutReachEventInformation> getAllEmployeeRole() {
		return eventInformationRepo.findAll();
	}
	
	@GetMapping("/viewEvent")
	public Mono<ResponseEntity<OutReachEventInformation>> getEmployeeId(@RequestBody OutReachEventInformationRequest request) {
		Mono<OutReachEventInformation> viewEventMono=null;
		if(request.getBusinessUnit() !=null && request.getEmployeeID() !=null) {
			viewEventMono= eventInformationRepo.findEventOne(request.getBusinessUnit(), request.getEmployeeID());
		}	
		return viewEventMono.map((event) -> ResponseEntity.ok(event))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
}
