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

import learn.springbatch.fileRelocation.VolunteerEnrollUnregisteredFileMoved;
import learn.springbatch.fileproperties.VolunteerUnregisteredXLFileProperties;
import learn.springbatch.model.Volunteer_Enrollment_Details_Unregistered;
import learn.springbatch.rowmapper.VolunteerEnrollUnregisteredRowMapper;


@Configuration
@EnableBatchProcessing
public class VolunteerEnrollDetailUnregisteredJob {
	private static final Logger log = LoggerFactory.getLogger(VolunteerEnrollDetailUnregisteredJob.class);
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private VolunteerUnregisteredXLFileProperties enrollUnRegisteredFileProperties;

	// Start Volunteer enrollment Detail_unregistered
	@Bean
	ItemStreamReader<Volunteer_Enrollment_Details_Unregistered> xlsxFileReader4() {
		File file = new File(enrollUnRegisteredFileProperties.getFileLocation());
		String absolutePath = file.getAbsolutePath();
		PoiItemReader<Volunteer_Enrollment_Details_Unregistered> reader = new PoiItemReader<Volunteer_Enrollment_Details_Unregistered>();
		UrlResource resource = (UrlResource) resourceLoader.getResource("file:\\" + absolutePath);
		log.info("Input file reading..." + resource.getFilename());
		reader.setResource(resource);
		reader.setRowMapper(new VolunteerEnrollUnregisteredRowMapper());
		reader.setLinesToSkip(1);
		return reader;
	}


	@Bean("job4")
	Job job4(JobBuilderFactory jbf, StepBuilderFactory sbf,
			ItemStreamReader<Volunteer_Enrollment_Details_Unregistered> ir,
			ItemWriter<Volunteer_Enrollment_Details_Unregistered> iw) {

		Step s1 = (Step) sbf.get("file-4")
				.<Volunteer_Enrollment_Details_Unregistered, Volunteer_Enrollment_Details_Unregistered>chunk(100)
				.reader(ir).writer(iw).build();

		Step s2 = null;
		try {
			s2 = sbf.get("file4-renamed-moved").tasklet(getFileRenameAndMoveTasket4()).build();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return (Job) jbf.get("file4-read").incrementer(new RunIdIncrementer()).start(s1).next(s2).build();
	}

	@Bean
	public VolunteerEnrollUnregisteredFileMoved getFileRenameAndMoveTasket4() throws MalformedURLException {
		return new VolunteerEnrollUnregisteredFileMoved();
	};

	// End Volunteer enrollment Detail_unregistered
}
