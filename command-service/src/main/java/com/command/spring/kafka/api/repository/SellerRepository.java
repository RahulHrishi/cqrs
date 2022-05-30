package com.command.spring.kafka.api.repository;

import com.commons.dto.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, Integer> {
}
