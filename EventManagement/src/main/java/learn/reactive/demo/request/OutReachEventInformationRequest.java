package learn.reactive.demo.request;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutReachEventInformationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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