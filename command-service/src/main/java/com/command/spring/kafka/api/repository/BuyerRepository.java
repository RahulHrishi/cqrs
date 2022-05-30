package com.command.spring.kafka.api.repository;

import com.commons.dto.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuyerRepository extends MongoRepository<Buyer, Integer>{

}
