package learn.springemail.serviceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import javax.mail.Address;
//import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import learn.springemail.config.FMSMailConfiguration;
import learn.springemail.property.FMSMailConfigProperties;
import learn.springemail.request.EmailDetails;
import learn.springemail.service.FMSMailService;

@Service("mailService")
public class FMSMailServiceImpl implements FMSMailService{

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
 
	@Autowired
	private FMSMailConfigProperties properties;
	
	@Override
	public void sendMail(String receiverEmailId, String subject, String message,EmailDetails email) {
		//String[] receiverEmailId,
		MimeMessagePreparator preparator=new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true);
				helper.setSubject(subject);
				/*
				 * InternetAddress[] recipientAddress = new
				 * InternetAddress[receiverEmailId.length]; int counter = 0; for (String
				 * recipient : receiverEmailId) { recipientAddress[counter] = new
				 * InternetAddress(recipient.trim()); counter++; }
				 */
				helper.setFrom(properties.getFromMailId());
				helper.setTo(receiverEmailId);
				Map<String,Object> fm_model=new HashMap<String,Object>();
				fm_model.put("message",email.getMessage());
				fm_model.put("eventName", email.getEventName());
				fm_model.put("eventDate", email.getEventDate());
				fm_model.put("empName", email.getEmpName());
				String text=getFreeMarkerTemplateContent(fm_model);
				helper.setText(text,true);
			}
			
		};
		
		try {
			mailSender.send(preparator);
			System.out.println("Mail send successfully....!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected String getFreeMarkerTemplateContent(Map<String, Object> fm_model) {
		StringBuffer content=new StringBuffer();
		try {
			Template template=freeMarkerConfigurer.getConfiguration().getTemplate("participatedMailTemplate.ftl");
			String html=FreeMarkerTemplateUtils.processTemplateIntoString(template, fm_model);
			content.append(html);
		} catch (IOException | TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content.toString();
	}

}
