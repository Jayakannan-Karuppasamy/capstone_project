package learn.springbatch.rowmapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import learn.springbatch.model.Volunteer_Enrollment_Details_Not_Attend;


public class VolunteerEnrollNotAttendRowMapper implements RowMapper<Volunteer_Enrollment_Details_Not_Attend> {
	Logger log=LoggerFactory.getLogger(VolunteerEnrollNotAttendRowMapper.class);
	@Override
	public Volunteer_Enrollment_Details_Not_Attend mapRow(RowSet rs) throws Exception {
		if(rs == null && rs.getCurrentRow() == null)
		{
			return null;
		}
		// Excel record mapped into pojo.
		Volunteer_Enrollment_Details_Not_Attend enrollNotAttnd=new Volunteer_Enrollment_Details_Not_Attend();		
		System.out.println("Read Column is "+rs.getColumnValue(0));
		
		enrollNotAttnd.setEventId(rs.getColumnValue(0)); // eventId
		enrollNotAttnd.setEventName(rs.getColumnValue(1));// eventName
		enrollNotAttnd.setBeneficiaryName(rs.getColumnValue(2));// beneficiaryName
		enrollNotAttnd.setBaseLocation(rs.getColumnValue(3)); //baseLocation
		
		String eventDateStr=rs.getColumnValue(4);//eventDate; // (DD-MM-YY)
		Date eventDate=new SimpleDateFormat("dd-MM-yy").parse(eventDateStr);
		enrollNotAttnd.setEventDate(eventDate); //eventDate
		
		enrollNotAttnd.setEmployeeId(rs.getColumnValue(5)); //EmployeeId
		
		
	//	log.info( "Row ID "+rs.getCurrentRowIndex() +"Object :::"+orei.getEventID() +"Employee ID "+ orei.getEmployeeID() + "Event Date ::"+orei.getEventDate() +  "Bussiness unit ::"+orei.getBusinessUnit());
		return enrollNotAttnd;
	}

}