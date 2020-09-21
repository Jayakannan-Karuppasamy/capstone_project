package learn.springbatch.rowmapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import learn.springbatch.model.OutReachEventsSummary;


public class EventsSummaryRowMapper implements RowMapper<OutReachEventsSummary> {
	Logger log=LoggerFactory.getLogger(EventsSummaryRowMapper.class);
	@Override
	public OutReachEventsSummary mapRow(RowSet rs) throws Exception {
		if(rs == null && rs.getCurrentRow() == null)
		{
			return null;
		}
		// Excel record mapped into pojo.
		OutReachEventsSummary ores=new OutReachEventsSummary();		
		System.out.println("Read Column is "+rs.getColumnValue(0));
		
		ores.setEventID(rs.getColumnValue(0)); //eventID;
		ores.setMonths(rs.getColumnValue(1)); //month;
		ores.setBaseLocation(rs.getColumnValue(2)); //baseLocation;
		ores.setBeneficiaryName(rs.getColumnValue(3)); //beneficiaryName;
		ores.setVenueAddress(rs.getColumnValue(4)); //venueAddress;
		ores.setCouncilName(rs.getColumnValue(5)); //councilName;
		ores.setProject(rs.getColumnValue(6)); //project;
		ores.setCategory(rs.getColumnValue(7)); //category;
		ores.setEventName(rs.getColumnValue(8)); //eventName;
		ores.setEventDescription(rs.getColumnValue(9)); //eventDescription;
		
		String eventDateStr=rs.getColumnValue(10);//eventDate; // (DD-MM-YY)
		Date eventDate=new SimpleDateFormat("dd-MM-yy").parse(eventDateStr);
		ores.setEventDate(eventDate);
		
		ores.setTotalNoOfVolunteers(rs.getColumnValue(11)); //totalNoOfVolunteers;
		ores.setTotalVolunteerHours(rs.getColumnValue(12)); //totalVolunteerHours;
		ores.setTotalTravelHours(rs.getColumnValue(13)); //totalTravelHours;
		ores.setOverallVolunteeringHours(rs.getColumnValue(14)); //overallVolunteeringHours;
		ores.setLivesImpacted(rs.getColumnValue(15)); //livesImpacted;
		ores.setActivityType(rs.getColumnValue(16)); //activityType;
		ores.setStatus(rs.getColumnValue(17)); //status;
		ores.setPocId(rs.getColumnValue(18)); //pocId;
		ores.setPocName(rs.getColumnValue(19)); //pocName;
		ores.setPocContactNumber(rs.getColumnValue(20)); //pocContactNumber;
		
	//	log.info( "Row ID "+rs.getCurrentRowIndex() +"Object :::"+orei.getEventID() +"Employee ID "+ orei.getEmployeeID() + "Event Date ::"+orei.getEventDate() +  "Bussiness unit ::"+orei.getBusinessUnit());
		return ores;
	}

}