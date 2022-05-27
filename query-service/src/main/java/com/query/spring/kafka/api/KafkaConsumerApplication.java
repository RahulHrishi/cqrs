package com.query.spring.kafka.api;

import com.commons.dto.Book;
import com.query.spring.kafka.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {

	@Autowired
	private BookRepository repository;

/*
	@GetMapping("/consumeJsonMessage")
	public User consumeJsonMessage() {
		return userFromTopic;
	}*/

	@KafkaListener(groupId = "rahul-2", topics = "rahul", containerFactory = "kafkaListenerContainerFactory")
	public Book readDBBook(Book book) {
		repository.save(book);
		return book;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
}
