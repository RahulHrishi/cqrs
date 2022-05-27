package com.command.spring.kafka.api.repository;

import com.commons.dto.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
