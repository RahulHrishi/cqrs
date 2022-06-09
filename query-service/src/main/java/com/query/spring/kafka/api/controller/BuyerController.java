package com.query.spring.kafka.api.controller;

import com.commons.dto.Buyer;
import com.commons.dto.Constants;
import com.commons.dto.MappedProductModel;
import com.commons.dto.Seller;
import com.query.spring.kafka.api.Excption.QueryException;
import com.query.spring.kafka.api.Service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("e-auction/api/v1/buyer")
public class BuyerController {
    @Autowired
    private ServiceImpl serviceImpl;

    @KafkaListener(groupId = Constants.GRP_ID_SELL, topics = Constants.SELL_T, containerFactory = "kafkaListenerContainerFactory")
    public Seller consumeSeller(Seller seller) throws QueryException {
        serviceImpl.consumeSeller(seller);
        return seller;
    }

    @KafkaListener(groupId = Constants.GRP_ID_BUY, topics = Constants.BID_T, containerFactory = "kafkaListenerContainerFactoryBuyer")
    public Buyer consumeBidder(Buyer buyer) throws QueryException {
        serviceImpl.consumeBidder(buyer);
        return buyer;
    }

    @GetMapping("/getProducts")
    public MappedProductModel getProducts(@RequestParam Integer productId) throws QueryException {
        return serviceImpl.findByProduct(productId);
    }

}
