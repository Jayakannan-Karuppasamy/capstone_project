package learn.springbatch.configuration;

import java.io.File;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import learn.springbatch.fileRelocation.VolunteerEnrollNotAttendFileMoved;
import learn.springbatch.fileproperties.VolunteerNotAttendXLFileProperties;
import learn.springbatch.model.Volunteer_Enrollment_Details_Not_Attend;
import learn.springbatch.rowmapper.VolunteerEnrollNotAttendRowMapper;


@Configuration
@EnableBatchProcessing
public class VolunteerEnrollDetailNotAttendJob {

	private static final Logger log = LoggerFactory.getLogger(VolunteerEnrollDetailNotAttendJob.class);
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private VolunteerNotAttendXLFileProperties enrollNotAttendFileProperties;
	
	//Start Volunteer enrollment Detail_notAttend
		@Bean
		ItemStreamReader<Volunteer_Enrollment_Details_Not_Attend> xlsxFileReader3(){
			File file = new File(enrollNotAttendFileProperties.getFileLocation());
			String absolutePath = file.getAbsolutePath();
			PoiItemReader<Volunteer_Enrollment_Details_Not_Attend> reader=new PoiItemReader<Volunteer_Enrollment_Details_Not_Attend>();
			UrlResource resource = (UrlResource) resourceLoader.getResource("file:\\"+absolutePath);
			log.info("Input file reading..."+resource.getFilename());
			reader.setResource(resource);
			reader.setRowMapper(new VolunteerEnrollNotAttendRowMapper());
			reader.setLinesToSkip(1);
			return reader;
		}
		
			
		@Bean
		Job job3(JobBuilderFactory jbf, StepBuilderFactory sbf,
				ItemStreamReader<Volunteer_Enrollment_Details_Not_Attend> ir,
				ItemWriter<Volunteer_Enrollment_Details_Not_Attend> iw) {

			Step s1 = (Step) sbf.get("file-3")
					.<Volunteer_Enrollment_Details_Not_Attend, Volunteer_Enrollment_Details_Not_Attend>chunk(100).reader(ir)
					.writer(iw).build();

			Step s2 = null;
			try {
				s2 = sbf.get("file3-renamed-moved").tasklet(getFileRenameAndMoveTasket3()).build();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			return (Job) jbf.get("file3-read").incrementer(new RunIdIncrementer()).start(s1).next(s2).build();
		}
		
		@Bean
		public VolunteerEnrollNotAttendFileMoved getFileRenameAndMoveTasket3() throws MalformedURLException {
			return new VolunteerEnrollNotAttendFileMoved();
		};
		
		//End Volunteer enrollment Detail_notAttend
}
