package learn.springemail.request;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String empMailId;
	private String empName;
	private String subject;
	private String message; //title
	private String feedbackType;
	private String eventName;
	private String eventDate;
	private List<FeedbackQuestions> questions;
	
	
}
