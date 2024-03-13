package com.merantory.emailsender.service;

import com.merantory.emailsender.model.EmailMessage;

public interface EmailSenderService {

	void sendMessage(String receiver, String subject, String text);
	void sendMessage(EmailMessage emailMessage);
}
