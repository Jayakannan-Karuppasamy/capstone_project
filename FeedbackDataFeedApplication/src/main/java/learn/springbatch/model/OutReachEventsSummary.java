package learn.springbatch.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "OutReachEventsSummary")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutReachEventsSummary implements Serializable{
	
	/**
	 * CREATE TABLE `outreacheventssummary` (
  `OutreachEventsSummaryId` int NOT NULL AUTO_INCREMENT,
  `EventID` varchar(50) NOT NULL,
  `Months` varchar(5) NOT NULL,
  `BaseLocation` varchar(50) NOT NULL,
  `BeneficiaryName` varchar(300) NOT NULL,
  `VenueAddress` varchar(300) NOT NULL,
  `CouncilName` varchar(50) NOT NULL,
  `Project` varchar(50) NOT NULL,
  `Category` varchar(50) NOT NULL,
  `EventName` varchar(300) NOT NULL,
  `EventDescription` varchar(300) NOT NULL,
  `EventDate` date NOT NULL,
  `Totalnoofvolunteers` double(10,2) DEFAULT NULL,
  `TotalVolunteerHours` double(10,2) DEFAULT NULL,
  `TotalTravelHours` double(10,2) DEFAULT NULL,
  `OverallVolunteeringHours` double(10,2) DEFAULT NULL,
  `LivesImpacted` double(10,2) DEFAULT NULL,
  `ActivityType` double(10,2) DEFAULT NULL,
  `summary_Status` varchar(50) NOT NULL,
  `POCID` varchar(50) NOT NULL,
  `POCName` varchar(50) NOT NULL,
  `POCContactNumber` varchar(50) NOT NULL,
  PRIMARY KEY (`OutreachEventsSummaryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private BigInteger OutreachEventsSummaryId;
	private String eventID;
	private String months;
	private String baseLocation;
	private String beneficiaryName;
	private String venueAddress;
	private String councilName;
	private String project;
	private String category;
	private String eventName;
	private String eventDescription;
	private Date eventDate; // (DD-MM-YY)
	private String totalNoOfVolunteers;
	private String totalVolunteerHours;
	private String totalTravelHours;
	private String overallVolunteeringHours;
	private String livesImpacted;
	private String activityType;
	private String status;
	private String pocId;
	private String pocName;
	private String pocContactNumber;


}
