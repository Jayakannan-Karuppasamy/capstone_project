package learn.springbatch.rowmapper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;
import org.springframework.stereotype.Component;

import learn.springbatch.model.OutReachEventInformation;


@Component
public class EventInformationRowMapper implements RowMapper<OutReachEventInformation> {
	Logger log=LoggerFactory.getLogger(EventInformationRowMapper.class);
	@Override
	public OutReachEventInformation mapRow(RowSet rs) throws Exception {
		if(rs == null && rs.getCurrentRow() == null)
		{
			return null;
		}
		// Excel record mapped into pojo.
		OutReachEventInformation orei=new OutReachEventInformation();		
		System.out.println("Read Column is "+rs.getColumnValue(0));
		orei.setEventID(rs.getColumnValue(0));//Council Name
		orei.setBaseLocation(rs.getColumnValue(1));
		orei.setBeneficiaryName(rs.getColumnValue(2));
		orei.setCouncilName(rs.getColumnValue(3));
		orei.setEventName(rs.getColumnValue(4));
		orei.setEventDescription(rs.getColumnValue(5));
		
		String eventDateStr=rs.getColumnValue(6);
		Date eventDate=null;
		eventDate=new SimpleDateFormat("dd-MM-yy").parse(eventDateStr);
		orei.setEventDate(eventDate);
		String eid=null;float eId=0;long empId=0;
		try {
			eid= rs.getColumnValue(7);
			eId=Float.parseFloat(eid);
			empId=(long) Math.round(eId);			 
		}catch(Exception e) {
			
		}
		orei.setEmployeeID(empId);
		orei.setEmployeeName(rs.getColumnValue(8));
		
		orei.setVolunteerHours(Double.parseDouble(rs.getColumnValue(9)));
		orei.setTravelHours(Double.parseDouble(rs.getColumnValue(10)));
		orei.setLivesImpacted(Double.parseDouble(rs.getColumnValue(11)));
		
		orei.setBusinessUnit(rs.getColumnValue(12));
		orei.setStatus(rs.getColumnValue(13));
		orei.setIiepCategory(rs.getColumnValue(14));
		log.info( "Row ID "+rs.getCurrentRowIndex() +"Object :::"+orei.getEventID() +"Employee ID "+ orei.getEmployeeID() + "Event Date ::"+orei.getEventDate() +  "Bussiness unit ::"+orei.getBusinessUnit());
		return orei;
	}

}