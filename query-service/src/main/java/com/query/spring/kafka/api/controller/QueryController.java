package com.query.spring.kafka.api.controller;

import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import com.query.spring.kafka.api.repository.BuyerRepository;
import com.query.spring.kafka.api.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;


    @KafkaListener(groupId = "rahul-2", topics = "rahul", containerFactory = "kafkaListenerContainerFactory")
    public Seller consumeSeller(Seller sellerDTO) {
        sellerRepository.save(sellerDTO);
        return sellerDTO;
    }

    @KafkaListener(groupId = "rahul-2", topics = "rahul", containerFactory = "kafkaListenerContainerFactoryBuyer")
    public Buyer consumeBidder(Buyer buyer) {
        buyerRepository.save(buyer);
        return buyer;
    }
}
