package  learn.springbatch.rowmapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

import learn.springbatch.model.Volunteer_Enrollment_Details_Unregistered;


public class VolunteerEnrollUnregisteredRowMapper implements RowMapper<Volunteer_Enrollment_Details_Unregistered> {
	Logger log=LoggerFactory.getLogger(VolunteerEnrollUnregisteredRowMapper.class);
	@Override
	public Volunteer_Enrollment_Details_Unregistered mapRow(RowSet rs) throws Exception {
		if(rs == null && rs.getCurrentRow() == null)
		{
			return null;
		}
		// Excel record mapped into pojo.
		Volunteer_Enrollment_Details_Unregistered enrollUnReg=new Volunteer_Enrollment_Details_Unregistered();		
		System.out.println("Read Column is "+rs.getColumnValue(0));
		
	
		enrollUnReg.setEventId(rs.getColumnValue(0)); // eventId
		enrollUnReg.setEventName(rs.getColumnValue(1));// eventName
		enrollUnReg.setBeneficiaryName(rs.getColumnValue(2));// beneficiaryName
		enrollUnReg.setBaseLocation(rs.getColumnValue(3)); //baseLocation
		
		String eventDateStr=rs.getColumnValue(4);//eventDate; // (DD-MM-YY)
		Date eventDate=new SimpleDateFormat("dd-MM-yy").parse(eventDateStr);
		enrollUnReg.setEventDate(eventDate); //eventDate
		
		enrollUnReg.setEmployeeId(rs.getColumnValue(5)); //EmployeeId
		
	//	log.info( "Row ID "+rs.getCurrentRowIndex() +"Object :::"+orei.getEventID() +"Employee ID "+ orei.getEmployeeID() + "Event Date ::"+orei.getEventDate() +  "Bussiness unit ::"+orei.getBusinessUnit());
		return enrollUnReg;
	}

}