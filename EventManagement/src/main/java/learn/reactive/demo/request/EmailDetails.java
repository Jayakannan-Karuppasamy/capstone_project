package learn.reactive.demo.request;

import java.util.List;

import learn.reactive.demo.model.FeedbackQuestions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDetails {
	private String empMailId;
	private String empName;
	private String subject;
	private String message; //title
	private String rating;
	private String feedbackType;
	private String eventName;
	private String eventDate;
	private List<FeedbackQuestions> questions;
}
