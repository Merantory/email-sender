package com.merantory.emailsender.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class EmailMessage {

	@NonNull
	private String receiver;

	@NonNull
	private String subject;

	@NonNull
	private String text;
}
