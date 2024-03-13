package com.merantory.emailsender.service.impl;

import com.merantory.emailsender.model.EmailMessage;
import com.merantory.emailsender.service.EmailSenderService;
import com.merantory.emailsender.service.KafkaEventConsumer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class KafkaEventConsumerImpl implements KafkaEventConsumer {

	private final EmailSenderService emailSenderService;

	@Override
	@KafkaListener(topics = "email_message",
			containerFactory = "emailMessageKafkaListenerContainerFactory")
	public void handle(EmailMessage emailMessage) {
		log.info("Message was received from kafka: {}", emailMessage);
		emailSenderService.sendMessage(emailMessage);
	}
}
