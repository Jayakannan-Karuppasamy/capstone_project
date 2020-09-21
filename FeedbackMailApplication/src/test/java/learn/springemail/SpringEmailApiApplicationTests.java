package learn.springemail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import learn.springemail.constant.MailConstants;
import learn.springemail.request.EmailDetails;
import learn.springemail.request.FeedbackQuestions;
import learn.springemail.request.MailRequest;
import reactor.core.publisher.Mono;

@SpringBootTest
@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient(timeout = "60000")
class SpringEmailApiApplicationTests {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	public void sendMail() {
		List<EmailDetails> listOfParticipated=new ArrayList<EmailDetails>();
		List<FeedbackQuestions> questions=new ArrayList<FeedbackQuestions>();
		
		MailRequest mailReq=new MailRequest();
		
		FeedbackQuestions question=new FeedbackQuestions(1,"First Question","Participated","Allow Mutiple Question",null);
		questions.add(question);
		
		EmailDetails mailData=new EmailDetails();
			mailData.setEmpMailId("ramyasaminathan2@gmail.com");
			mailData.setEmpName("Ramya Jayakannan");
			mailData.setSubject("Sending Email with Freemarker HTML Template Example");
			mailData.setMessage("Feedback Request for");
			mailData.setFeedbackType("Participated");
			mailData.setEventName("Cognizant Event");
			mailData.setEventDate("02/04/2020");
			mailData.setQuestions(questions);
			
		listOfParticipated.add(mailData);	
		
		mailReq.setEmailDetails(listOfParticipated);;
		
		webTestClient.post().uri(MailConstants.MAIL_API_ENDPOINT.concat("/participated"))
			.contentType(MediaType.APPLICATION_JSON)
			.body(Mono.just(mailReq),MailRequest.class)
			.exchange()
			.expectStatus().isOk();
	}
}
