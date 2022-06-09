package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Excption.CommandException;
import com.command.spring.kafka.api.Service.ServiceImpl;
import com.command.spring.kafka.api.validation.CommandValidation;
import com.commons.Excption.ValidationException;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
        return "bidder data published";
    }

    @PostMapping("/update-bid/{productId}/{emailId}/{newBidAmount}")
    public String updateBid(@PathParam("productId") Integer productId, @PathParam("emailId") String emailId, @PathParam("newBidAmount") Integer newBidAmount) throws CommandException, ValidationException {
        service.updateBuyer(productId, emailId, newBidAmount);
        return "bidder data published";
    }
}
