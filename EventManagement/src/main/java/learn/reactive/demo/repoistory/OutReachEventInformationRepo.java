package learn.reactive.demo.repoistory;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import learn.reactive.demo.model.EventInformationReport;
import learn.reactive.demo.model.OutReachEventInformation;
import learn.reactive.demo.request.OutReachEventInformationRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OutReachEventInformationRepo extends ReactiveMongoRepository<OutReachEventInformation, Integer> {

	@Query(value = "{ 'employeeID' : ?0 }", fields = "{ 'eventID' : 1, 'eventName' : 1,'eventDate' : 1, 'employeeID' : 1, 'employeeName' : 1}")
	public Mono<OutReachEventInformation> findByemployeeID(Long employeeID);

	/*
	 * @Query(
	 * fields="{ 'eventID' : 1, 'eventName' : 1,'eventDate' : 1, 'employeeID' : 1, 'employeeName' : 1}"
	 * ) public Flux<OutReachEventInformation> findByAll();
	 */

	@Query(value = "{eventID : ?0}", count = true)
	public Mono<Long> findByeventCount(String eventID);

	@Query(value = "{eventID: {$ne: null }}", count=true)
	public Mono<Long> findByeventID();

	@Query(value = "{ 'businessUnit' : ?0, 'employeeID': ?1 }", fields = "{'outReachEventInformId':1,'eventID':1,'baseLocation':1,'beneficiaryName':1,'councilName':1,'eventName':1,'eventDescription':1,'eventDate':1,'employeeID':1,'employeeName':1,'volunteerHours':1,'travelHours':1,'livesImpacted':1,'businessUnit':1,'status':1,'iiepCategory':1,}")
	public Mono<OutReachEventInformation> findEventOne(String businessUnit, Long employeeId);
}
