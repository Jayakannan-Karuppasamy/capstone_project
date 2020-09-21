package learn.springbatch.model;

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
	/**
	 * CREATE TABLE `volunteerenrollmentdetails_unregistered` (
  `enrollUnregisteredId` int NOT NULL AUTO_INCREMENT,
  `eventId` varchar(50) DEFAULT NULL,
  `eventName` varchar(50) DEFAULT NULL,
  `beneficiaryName` varchar(50) DEFAULT NULL,
  `baseLocation` varchar(50) DEFAULT NULL,
  `eventDate` date DEFAULT NULL,
  `EmployeeId` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`enrollUnregisteredId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

	 */
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
