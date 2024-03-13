package com.merantory.emailsender.service.impl;

import com.merantory.emailsender.model.EmailMessage;
import com.merantory.emailsender.service.EmailSenderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

	private final JavaMailSender mailSender;

	public void sendMessage(String receiver, String subject, String text) {
		if (isValidEmail(receiver)) {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(receiver);
			mailMessage.setSubject(subject);
			mailMessage.setText(text);

			mailSender.send(mailMessage);
			log.info("Email message was sent to address \"{}\" with message subject: \"{}\".",
					receiver, subject);
		} else {
			log.info("Received email \"{}\" not valid", receiver);
		}
	}

	@Override
	public void sendMessage(@NonNull EmailMessage emailMessage) {
		sendMessage(emailMessage.getReceiver(), emailMessage.getSubject(), emailMessage.getText());
	}

	private boolean isValidEmail(String email) {
		if (email == null || email.isBlank()) {
			return false;
		}

		String regexpEmailValidPattern = "^(\\S+)@(\\S+\\.\\S+)$";
		return email.matches(regexpEmailValidPattern);
	}
}
