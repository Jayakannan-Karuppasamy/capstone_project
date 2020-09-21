package learn.springemail.service;

import learn.springemail.request.EmailDetails;

public interface FMSMailService {
	public void sendMail( final String receiverEmailId, final String Subject,
			final String message,EmailDetails mail);
}
