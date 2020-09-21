package learn.springbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchMongoDbApplication {

	public static void main(String[] args) {
	//	System.setProperty("input1", "D:\\856370\\Events\\OutReach Event Information.xlsx");//OutReach Event Information.xlsx;
		//System.setProperty("output","D:\\856370\\Events\\Processed");
		SpringApplication.run(SpringBatchMongoDbApplication.class, args);
	}

}
