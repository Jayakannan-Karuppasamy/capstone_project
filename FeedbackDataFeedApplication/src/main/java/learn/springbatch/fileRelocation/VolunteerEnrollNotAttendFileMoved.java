package learn.springbatch.fileRelocation; 

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import learn.springbatch.fileproperties.VolunteerNotAttendXLFileProperties;


@Component
public class VolunteerEnrollNotAttendFileMoved implements Tasklet{

	Logger log = LoggerFactory.getLogger(VolunteerEnrollNotAttendFileMoved.class);
	
	@Autowired
	private VolunteerNotAttendXLFileProperties voluteerNotAttendFileProperties;

	private boolean moveProcessedFile() {
		FileSystemResource source = new FileSystemResource(voluteerNotAttendFileProperties.getFileLocation());
		FileSystemResource destination = new FileSystemResource(voluteerNotAttendFileProperties.getOutputFileLocation());
		String destFilePath = destination.getFile().getAbsolutePath();
		String srcFileName = source.getFilename();
		String fileExt = srcFileName.split("\\.")[1];
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String formatDateTime = now.format(format);
		String renamedFile = srcFileName.split("\\.")[0].concat("-").concat(formatDateTime).concat("." + fileExt);
		File mvFile = new File(destFilePath + "\\" + renamedFile);
		return  source.getFile().renameTo(mvFile);
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("VolunteerEnrollNotAttendFileMoved file moved..?");
		boolean moved =false;
		if (voluteerNotAttendFileProperties.getShouldMoveFile())
			moved =	moveProcessedFile();
		log.info("VolunteerEnrollNotAttendFileMoved file moved..?"+moved);
		return RepeatStatus.FINISHED;
	}
}
