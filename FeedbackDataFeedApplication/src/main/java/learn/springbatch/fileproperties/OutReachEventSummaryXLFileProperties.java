package learn.springbatch.fileproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties("event.summary")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutReachEventSummaryXLFileProperties {

	private String fileLocation;
	private String outputFileLocation;
	private Boolean shouldMoveFile;

	/*
	 * public OutReachEventSummaryXLFileProperties() { } public String
	 * getFileLocation() { return fileLocation; } public void setFileLocation(String
	 * fileLocation) { this.fileLocation = fileLocation; } public String
	 * getOutputFileLocation() { return outputFileLocation; } public void
	 * setOutputFileLocation(String outputFileLocation) { this.outputFileLocation =
	 * outputFileLocation; } public Boolean getShouldMoveFile() { return
	 * shouldMoveFile; } public void setShouldMoveFile(Boolean shouldMoveFile) {
	 * this.shouldMoveFile = shouldMoveFile; }
	 */
}
