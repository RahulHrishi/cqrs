package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Excption.CommandException;
import com.command.spring.kafka.api.Service.ServiceImpl;
import com.command.spring.kafka.api.validation.CommandValidation;
import com.commons.Excption.ValidationException;
import com.commons.dto.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import com.commons.dto.Constants;
@RestController
@RequestMapping("e-auction/api/v1/buyer")
public class BuyerController {

    @Autowired
    private ServiceImpl service;

    @Autowired
    private CommandValidation validation;


    @PostMapping("/place-bid")
    public String placeBid(@RequestBody Buyer buyer) throws CommandException, ValidationException {
        if (validation.validateBidProduct(buyer))
            service.saveBuyer(buyer);
        return Constants.BIDDER_PUBLISHED;
    }

    @PostMapping("/update-bid/{productId}/{emailId}/{newBidAmount}")
    public String updateBid(@RequestParam("productId") Integer productId, @RequestParam("emailId") String emailId, @RequestParam("newBidAmount") Integer newBidAmount) throws CommandException, ValidationException {
        service.updateBid(productId, emailId, newBidAmount);
        return Constants.BIDDER_UPDATED;
    }
}
