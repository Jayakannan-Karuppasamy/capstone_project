package learn.reactive.demo.model;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "OutReachEventInformation")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutReachEventInformation {

	@Id
	private BigInteger outReachEventInformId;
	private String eventID;
	private String baseLocation;
	private String beneficiaryName;
	private String councilName;
	private String eventName;
	private String eventDescription;
	private Date eventDate;
	private Long employeeID;
	private String employeeName;
	private Double volunteerHours;
	private Double travelHours;
	private Double livesImpacted;
	private String businessUnit;
	private String status;
	private String iiepCategory;
}