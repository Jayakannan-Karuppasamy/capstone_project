package learn.springemail.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

import learn.springemail.property.FMSMailConfigProperties;
@Configuration
public class FMSMailConfiguration {
	
	@Autowired
	private FMSMailConfigProperties properties;

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		mailSender.setHost(properties.getHost());
		mailSender.setPort(properties.getPort());
		mailSender.setUsername(properties.getFromMailId());
		mailSender.setPassword(properties.getFromMailIdPassword());
		
		Properties javaMailProp=new Properties();
		javaMailProp.put(properties.getSmtpStarttls(),true);
		javaMailProp.put(properties.getSmtpAuth(), true);
		javaMailProp.put(properties.getSmtpTransProtocal(),"smtp");
		javaMailProp.put(properties.getDebug(), true );
		mailSender.setJavaMailProperties(javaMailProp);
		return mailSender;
	}
	
	@Bean
	public FreeMarkerConfigurer getfreeMarkerConfig() {
		FreeMarkerConfigurer freemakerBean=new FreeMarkerConfigurer();
		freemakerBean.setTemplateLoaderPath("classpath:/fmsMailTemplate");
		freemakerBean.setDefaultEncoding("UTF-8");
		return freemakerBean;
	}
	
	
}
