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

import java.util.Optional;

@RestController
@RequestMapping("e-auction/api/v1/buyer")
public class BuyerController {
    @Autowired
    private ServiceImpl serviceImpl;

    @KafkaListener(groupId = Constants.GRP_ID_SELL, topics = Constants.SELL_T, containerFactory = "kafkaListenerContainerFactory")
    public void consumeSeller(Seller seller) throws QueryException {
        serviceImpl.consumeSeller(seller);
    }

    @KafkaListener(groupId = Constants.GRP_ID_BUY, topics = Constants.BID_T, containerFactory = "kafkaListenerContainerFactoryBuyer")
    public void consumeBidder(Buyer buyer) throws QueryException {
        serviceImpl.consumeBidder(buyer);
    }

    @GetMapping("/findSellerWithBids")
    public Optional<MappedProductModel> findSellerWithBids(@RequestParam Integer productId) throws QueryException {
        return serviceImpl.findSellerWithBids(productId);
    }

    @GetMapping("/getBuyerByIdAndEmail")
    public Optional<Buyer> getBuyerByIdAndEmail(@RequestParam Integer productId, @RequestParam String email) throws QueryException {
        return serviceImpl.getBuyerByIdAndEmail(productId, email);
    }

}
