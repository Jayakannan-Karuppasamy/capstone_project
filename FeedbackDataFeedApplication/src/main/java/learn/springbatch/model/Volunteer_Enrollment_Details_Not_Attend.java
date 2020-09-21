package learn.springbatch.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document( value = "volunteerenrollmentdetail_notattend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer_Enrollment_Details_Not_Attend implements Serializable{
	/**
	 *CREATE TABLE `volunteerenrollmentdetail_notattend` (
  `enrollnotattendId` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(50) DEFAULT NULL,
  `eventName` varchar(50) DEFAULT NULL,
  `beneficiaryName` varchar(50) DEFAULT NULL,
  `baseLocation` varchar(50) DEFAULT NULL,
  `eventDate` date DEFAULT NULL,
  `EmployeeId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`enrollnotattendId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private BigInteger enrollnotattendId;
	private String eventId;
	private String eventName;
	private String beneficiaryName;
	private String baseLocation;
	private Date eventDate; //(DD-MM-YY)
	private String employeeId;
}
