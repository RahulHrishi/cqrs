package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Service.ServiceImpl;
import com.commons.Excption.AuctionException;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("do")
public class CommandController {

    @Autowired
    private ServiceImpl service;


    @PostMapping("/saveProduct")
    public String writeAndPublish(@RequestBody Seller sellerDTO) throws AuctionException {
        service.saveSeller(sellerDTO);
        return "seller data published";
    }

    @PostMapping("/bidProduct")
    public String bidAndPublish(@RequestBody Buyer buyerDTO) throws AuctionException {
        service.saveBuyer(buyerDTO);
        return "bidder data published";
    }
}
