package com.command.spring.kafka.api;

import com.commons.dto.Book;
import com.command.spring.kafka.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("do")
public class KafkaPublisherApplication {

	@Autowired
	private BookRepository repository;

	@Autowired
	private KafkaTemplate<String, Object> template;

	private String topic = "rahul";

	@PostMapping("/saveBook")
	public String writeAndPublish(@RequestBody Book book) {
		repository.save(book);
		template.send(topic, book);
		return "Book data published";
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}
}
