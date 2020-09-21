package learn.reactive.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class UnRegisteredFeedback implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Integer unRegisteredId;
	private Integer employeeId;
	private Integer eventId;
	private Integer questionId;
	private Integer answerId;
}
