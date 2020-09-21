package learn.springemail.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import learn.springemail.constant.MailConstants;
import learn.springemail.request.EmailDetails;
import learn.springemail.request.MailRequest;
import learn.springemail.serviceImpl.FMSMailServiceImpl;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(MailConstants.MAIL_API_ENDPOINT)
public class FMSMailController {

	private static final Logger log=LoggerFactory.getLogger(FMSMailController.class);
	
	@Autowired
	private FMSMailServiceImpl mailService;
	
	@PostMapping("/participated")
	public ResponseEntity<String> mailProcessed(@RequestBody MailRequest request) {
		log.info("/participated --- >service called"+request.getEmailDetails().size());
		if(request !=null) {
			for(EmailDetails req:request.getEmailDetails()) {
				((FMSMailServiceImpl) mailService).sendMail(req.getEmpMailId(),req.getSubject(), req.getMessage(),req);
			}
		}	
		return ResponseEntity.ok("Mail Sent successfully");
	}
}
