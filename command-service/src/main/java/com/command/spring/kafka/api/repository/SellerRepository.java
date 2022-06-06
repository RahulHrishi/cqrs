package com.command.spring.kafka.api.repository;

import com.commons.dto.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SellerRepository extends MongoRepository<Seller, Integer> {
    @Query("{'product.productId':?0}")
    Optional<Seller> findByProductId(Integer productId);
}
