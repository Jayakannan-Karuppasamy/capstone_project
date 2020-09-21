package learn.springemail.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("fms.mail")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FMSMailConfigProperties {

		private String host;
		private Integer port;
		private String fromMailId;
		private String fromMailIdPassword;
		private String smtpStarttls;
		private String smtpAuth;
		private String smtpTransProtocal;
		private String debug;
	
}
