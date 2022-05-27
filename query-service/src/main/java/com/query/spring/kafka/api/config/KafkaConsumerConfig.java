package com.query.spring.kafka.api.config;

import java.util.HashMap;
import java.util.Map;

import com.commons.dto.UserDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {


	@Bean
	public ConsumerFactory<String, UserDTO> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, "rahul-2");
		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(UserDTO.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserDTO> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, UserDTO> factory = new ConcurrentKafkaListenerContainerFactory<String, UserDTO>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
