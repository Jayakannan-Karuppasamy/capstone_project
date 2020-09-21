package learn.springbatch.dbwriter;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import learn.springbatch.model.Volunteer_Enrollment_Details_Not_Attend;
import learn.springbatch.repository.VolunteerEnrollDtlsNotAttendRepo;

@Component
public class VolunteerEnrollDetailsNotAttendWriter implements ItemWriter<Volunteer_Enrollment_Details_Not_Attend> {

	@Autowired
	VolunteerEnrollDtlsNotAttendRepo volunteerEnrollNotAttendRepo;

	@Override
	public void write(List<? extends Volunteer_Enrollment_Details_Not_Attend> notAttend) throws Exception {
		volunteerEnrollNotAttendRepo.saveAll(notAttend);
	}

}
