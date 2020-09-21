package learn.reactive.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class FeedbackQuestions {
	@Id
	private Integer questionId;
	private String question;
	private String feedbackType;
	private String questionType;
	private List<FeedbackAnswer> feedbackAnswer;
}
