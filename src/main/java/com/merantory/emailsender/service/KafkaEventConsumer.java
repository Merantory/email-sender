package com.merantory.emailsender.service;

import com.merantory.emailsender.model.EmailMessage;

public interface KafkaEventConsumer {

	void handle(EmailMessage emailMessage);
}
