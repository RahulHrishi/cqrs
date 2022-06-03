package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Excption.AuctionException;
import com.command.spring.kafka.api.Service.ServiceImpl;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("do")
public class CommandController {

    @Autowired
    private ServiceImpl service;


    @PostMapping("/saveProduct")
    public ResponseEntity<String> writeAndPublish(@RequestBody Seller sellerDTO) throws AuctionException {
        service.saveSeller(sellerDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bidProduct")
    public String bidAndPublish(@RequestBody Buyer buyerDTO) throws AuctionException {
        service.saveBuyer(buyerDTO);
        return "bidder data published";
    }
}
