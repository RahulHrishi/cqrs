package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.Excption.CommandException;
import com.command.spring.kafka.api.Service.ServiceImpl;
import com.command.spring.kafka.api.validation.CommandValidation;
import com.commons.Excption.ValidationException;
import com.commons.dto.Constants;
import com.commons.dto.Index;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("e-auction/api/v1/seller")
public class SellerController {

    @Autowired
    private ServiceImpl service;

    @Autowired
    private CommandValidation validation;

    @PostMapping("/add-product")
    public String addProduct(@RequestBody Seller seller) throws CommandException, ValidationException {
        if (validation.validateSaveProduct(seller))
            service.saveSeller(seller);
        return Constants.SELL_PUBLISHED;
    }

    @DeleteMapping("/delete")
    public String deleteProduct (@RequestParam Integer productId) throws CommandException {
        service.deleteProduct(productId);
        return  Constants.PRODUCT_DEL;
    }

    @GetMapping("/getAllProduct")
    public Set<Index> getAllProduct() throws CommandException {
        return service.getAllProduct();
    }
}
