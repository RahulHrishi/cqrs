package com.query.spring.kafka.api.repository;

import com.commons.dto.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SellerRepository extends MongoRepository<Seller, Integer>{

  /*  @Query("{'Product.productName':?0}")
    List<Seller> findByProductName(String name);*/

    @Query("{'product.productId':?0}")
    Seller findByProductId(Integer productId);
}
