package com.query.spring.kafka.api.repository;

import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BuyerRepository extends MongoRepository<Buyer, Integer>{
    List<Buyer> findByProductIdOrderByBidAmountDesc(Integer productId);

}

