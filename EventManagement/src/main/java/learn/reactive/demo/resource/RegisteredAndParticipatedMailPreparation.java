package learn.reactive.demo.resource;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import learn.reactive.demo.model.FeedbackQuestions;
import learn.reactive.demo.model.OutReachEventInformation;
import learn.reactive.demo.repoistory.FeedbackQuestionAndAnswerRepo;
import learn.reactive.demo.repoistory.OutReachEventInformationRepo;
import learn.reactive.demo.request.EmailDetails;
import learn.reactive.demo.request.MailRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/email/preparation")
public class RegisteredAndParticipatedMailPreparation {

	@Autowired
	private OutReachEventInformationRepo repo;
	
	@Autowired
	private FeedbackQuestionAndAnswerRepo questionRepo;
	
	WebClient client;
	
	@PostConstruct
	public void init() {
		client = WebClient.builder().baseUrl("http://localhost:8085/fms/mail")
					.defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE).build();
	}
	
	//@GetMapping("/all")
	@RequestMapping(value = "/email/preparation/all", method = RequestMethod.GET)
	public Flux<OutReachEventInformation> getAllRegisteredAndParticipated() {
		return repo.findAll();
	}
	
	//@GetMapping("/{eid}")
	@RequestMapping(value = "/email/preparation/{eid}", method = RequestMethod.GET)
	public Mono<ResponseEntity<OutReachEventInformation>> getRegAndPartiEmployeeId(@PathVariable Long eid) {
		return repo.findByemployeeID(eid).map(fetchId -> ResponseEntity.ok(fetchId)).defaultIfEmpty(ResponseEntity.notFound().build());
		//return repo.findByemployeeId(eid).map(fetchId -> ResponseEntity.ok(fetchId))	.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	
	//@PostMapping("/RegisteredEvent/save")
	@RequestMapping(value = "/registeredEvent/save", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<OutReachEventInformation> addRole(@RequestBody OutReachEventInformation eventInformation) {
		return repo.save(eventInformation);
	}
	
	//@PutMapping("/updateEvent/{id}")
	@RequestMapping(value = "/registeredEvent/update/{id}", method = RequestMethod.PUT)
	public Mono<ResponseEntity<OutReachEventInformation>> updateEventByEmpId(@PathVariable Long id,
			@RequestBody OutReachEventInformation newEvent) {
		return repo.findByemployeeID(id).flatMap(existingEmpEvent -> {
			existingEmpEvent.setEventName(newEvent.getEventName());
			return repo.save(existingEmpEvent);
		}).map(updateEvent -> new ResponseEntity<>(updateEvent, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	//@DeleteMapping("/delete/{id}")
	@RequestMapping(value = "/registeredEvent/delete/{id}", method = RequestMethod.DELETE)
	public Mono<ResponseEntity<Void>> deleteEmpId(@PathVariable Long id){
		return repo.findByemployeeID(id)
				.flatMap(existingEmp->repo.delete(existingEmp).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
						).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	
	@PostMapping("/callMailApi")
	public ResponseEntity<Mono<String>> callMailApi(@RequestBody MailRequest request){
		/*	private String empMailId;
		private String empName;
		private String subject;
		private String message; //title
		private String feedbackType;
		private String eventName;
		private String eventDate;
		private List<FeedbackQuestions> questions;*/
		List<MailRequest> requestObject=new ArrayList<MailRequest>();
		MailRequest mails=new MailRequest();
		List<EmailDetails> eDetails=new ArrayList<EmailDetails>();
		Flux<OutReachEventInformation>	fluxEventInfo= repo.findAll();
		//questionRepo.findByfeedbackType("participated");

		Mono<List<OutReachEventInformation>> i=fluxEventInfo.collectList();
	
		//RestTemplate restTemplate=new RestTemplate();
		String URI="/participated";
		Mono<String> result=Mono.just("Testing...!");
		//postForEntity(URI, listReq, String.class);
		//String result = restTemplate.exchange(URI, HttpMethod.GET, null, String.class).getBody();
		/*
		 * Mono<String>
		 * result=client.post().uri(URI).body(Mono.just(request),MailRequest.class)
		 * .retrieve() .bodyToMono(String.class);
		 */
		return ResponseEntity.ok(result);
	}
}
