package com.query.spring.kafka.api.config;

import java.util.HashMap;
import java.util.Map;

import com.commons.dto.Buyer;
import com.commons.dto.Constants;
import com.commons.dto.Seller;
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
	public ConsumerFactory<String, Seller> consumerFactory() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GRP_ID_SELL);
		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(Seller.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Seller> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Seller> factory = new ConcurrentKafkaListenerContainerFactory<String, Seller>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, Buyer> consumerFactoryBuyer() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		configs.put(ConsumerConfig.GROUP_ID_CONFIG, Constants.GRP_ID_BUY);
		return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new JsonDeserializer<>(Buyer.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Buyer> kafkaListenerContainerFactoryBuyer() {
		ConcurrentKafkaListenerContainerFactory<String, Buyer> factory = new ConcurrentKafkaListenerContainerFactory<String, Buyer>();
		factory.setConsumerFactory(consumerFactoryBuyer());
		return factory;
	}

}
