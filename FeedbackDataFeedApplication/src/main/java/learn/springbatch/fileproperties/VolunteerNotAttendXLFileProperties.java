package learn.springbatch.fileproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("volunteer.enroll.notattend")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VolunteerNotAttendXLFileProperties {

	private String fileLocation;
	private String outputFileLocation;
	private Boolean shouldMoveFile;

	/*
	 * public VolunteerNotAttendXLFileProperties() { }
	 * 
	 * public String getFileLocation() { return fileLocation; }
	 * 
	 * public void setFileLocation(String fileLocation) { this.fileLocation =
	 * fileLocation; }
	 * 
	 * public String getOutputFileLocation() { return outputFileLocation; }
	 * 
	 * public void setOutputFileLocation(String outputFileLocation) {
	 * this.outputFileLocation = outputFileLocation; }
	 * 
	 * public Boolean getShouldMoveFile() { return shouldMoveFile; }
	 * 
	 * public void setShouldMoveFile(Boolean shouldMoveFile) { this.shouldMoveFile =
	 * shouldMoveFile; }
	 */
}
