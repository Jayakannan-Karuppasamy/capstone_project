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

import learn.springbatch.fileRelocation.OutReachEventInfoFileMoved;
import learn.springbatch.fileproperties.OutReachEventInfoXLFileProperties;
import learn.springbatch.model.OutReachEventInformation;
import learn.springbatch.rowmapper.EventInformationRowMapper;

@Configuration
@EnableBatchProcessing
public class OutReachEventInformationJob {

	private static final Logger log = LoggerFactory.getLogger(OutReachEventInformationJob.class);

	@Autowired
	ResourceLoader resourceLoader;

	/*
	 * @Value("${input1}") private String srcFile1;
	 * 
	 * @Value("${output}") private String dest;
	 */

	@Autowired
	private OutReachEventInfoXLFileProperties eventInfoFileProperties;

	@Bean
	ItemStreamReader<OutReachEventInformation> xlsxFileReader() {
		File file = new File(eventInfoFileProperties.getFileLocation());
		String absolutePath = file.getAbsolutePath();
		PoiItemReader<OutReachEventInformation> reader = new PoiItemReader<OutReachEventInformation>();
		UrlResource resource = (UrlResource) resourceLoader.getResource("file:\\" + absolutePath);
		log.info("Input file reading..." + resource.getFilename());
		reader.setLinesToSkip(1);
		reader.setResource(resource);
		reader.setRowMapper(new EventInformationRowMapper());
		
		return reader;
	}

	@Bean
	Job job1(JobBuilderFactory jbf, StepBuilderFactory sbf, ItemStreamReader<OutReachEventInformation> ir,
			ItemWriter<OutReachEventInformation> iw) {

		Step s1 = (Step) sbf.get("file-1").<OutReachEventInformation, OutReachEventInformation>chunk(100).reader(ir)
				.writer(iw).build();

		Step s2 = null;
		try {
			s2 = sbf.get("file1-renamed-moved").tasklet(getFileRenameAndMoveTasket()).build();
		} catch (MalformedURLException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (Job) jbf.get("file1-read").incrementer(new RunIdIncrementer()).start(s1).next(s2).build();
	}

	@Bean
	public OutReachEventInfoFileMoved getFileRenameAndMoveTasket() throws MalformedURLException {
		return new OutReachEventInfoFileMoved();
	}

}
