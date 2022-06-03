package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Service.ServiceImpl;
import com.command.spring.kafka.api.validation.CommandValidation;
import com.commons.Excption.AuctionException;
import com.commons.Excption.ValidationException;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("do")
public class CommandController {

    @Autowired
    private ServiceImpl service;

    @Autowired
    private CommandValidation validation;

    @PostMapping("/saveProduct")
    public String writeAndPublish(@RequestBody Seller sellerDTO) throws AuctionException, ValidationException {
        if (validation.validateSaveProduct(sellerDTO))
        service.saveSeller(sellerDTO);
        return "seller data published";
    }

    @PostMapping("/bidProduct")
    public String bidAndPublish(@RequestBody Buyer buyerDTO) throws AuctionException, ValidationException {
        if (validation.validateBidProduct(buyerDTO))
        service.saveBuyer(buyerDTO);
        return "bidder data published";
    }
}
