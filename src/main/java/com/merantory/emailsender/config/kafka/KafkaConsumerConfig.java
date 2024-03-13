package com.merantory.emailsender.config.kafka;

import com.merantory.emailsender.model.EmailMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

	@Value("${kafka.server}")
	private String bootstrapAddress;

	@Value("${kafka.group-id}")
	private String groupId;

	@Bean
	public ConsumerFactory<String, EmailMessage> emailMessageConsumerFactory() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				bootstrapAddress);
		properties.put(
				ConsumerConfig.GROUP_ID_CONFIG,
				groupId);
		properties.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class);
		properties.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				JsonDeserializer.class);
		properties.put(
				JsonDeserializer.TRUSTED_PACKAGES,
				"com.merantory.*"
		);
		properties.put(
				JsonDeserializer.USE_TYPE_INFO_HEADERS,
				"false"
		);
		return new DefaultKafkaConsumerFactory<>(properties,
				new StringDeserializer(), new JsonDeserializer<>(EmailMessage.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, EmailMessage> emailMessageKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, EmailMessage> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(emailMessageConsumerFactory());
		return factory;
	}

}
