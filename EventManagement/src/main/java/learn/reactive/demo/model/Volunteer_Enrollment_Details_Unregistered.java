package learn.reactive.demo.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document( value="volunteerenrollmentdetails_unregistered")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer_Enrollment_Details_Unregistered implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private BigInteger enrollUnregisteredId;
	private String eventId;
	private String eventName;
	private String beneficiaryName;
	private String baseLocation;
	private Date eventDate; //(DD-MM-YY)
	private String employeeId;
}
