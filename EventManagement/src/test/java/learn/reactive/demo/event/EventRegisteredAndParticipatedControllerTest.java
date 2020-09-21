package learn.reactive.demo.event;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import learn.reactive.demo.constant.EventConstant;
import learn.reactive.demo.model.OutReachEventInformation;
import learn.reactive.demo.repoistory.OutReachEventInformationRepo;
import learn.reactive.demo.request.OutReachEventInformationRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
class EventRegisteredAndParticipatedControllerTest {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	OutReachEventInformationRepo eventInformationRepo;
	
	public String convertJsonObject(Object Input) throws JsonProcessingException {
		ObjectMapper Obj = new ObjectMapper(); 
			String jsonString=Obj.writeValueAsString(Input);
			
			//JSONObject jsonObj = new JSONObject(jsonString);
		return jsonString;
	}

	@Test
	public void getAllEvents() {
		webTestClient.get().uri("/reports/Registered".concat("/all")).exchange().expectStatus().isOk()
				.expectBodyList(OutReachEventInformation.class).consumeWith((response) -> {
					List<OutReachEventInformation> events = response.getResponseBody();
					events.forEach((e) -> {
						assertTrue(e.getEmployeeID() != null);
					});
				});
	}

	
	@Test
	public void getTotalEventCount() {
		webTestClient.get()
				.uri(EventConstant.EVENT_REGISTERED.concat("/eventCount"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(Long.class)
				.consumeWith((respone)->{ 
					Long i=respone.getResponseBody();
					assertEquals(7, i);					
				});

	}

	
	@Test
	public void getViewEvent() throws JsonProcessingException {
		OutReachEventInformationRequest request = new OutReachEventInformationRequest();
		request.setBusinessUnit("Banking & Financial Services");
		request.setEmployeeID(new Long(1));
		
		
		request=new ObjectMapper().readValue(convertJsonObject(request), OutReachEventInformationRequest.class);
		
		webTestClient.get().uri(EventConstant.EVENT_REGISTERED.concat("/viewEvent"),request)		
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON)
			.expectBody().jsonPath("$.eventName", "Bags of Joy Distribution");
		
		/*.contentType(MediaType.APPLICATION_JSON)
		
		.expectStatus().isOk()
		.expectBody().jsonPath("$.eventName", "Bags of Joy Distribution");*/
	}
	 
}
