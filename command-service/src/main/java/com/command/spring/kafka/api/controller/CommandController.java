package com.command.spring.kafka.api.controller;

import com.command.spring.kafka.api.repository.BuyerRepository;
import com.command.spring.kafka.api.repository.SellerRepository;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("do")
public class CommandController {

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private KafkaTemplate<String, Object> template;

    private String topic = "rahul";

    @PostMapping("/saveProduct")
    public String writeAndPublish(@RequestBody Seller sellerDTO) {
        try {
            sellerRepository.save(sellerDTO);
            template.send(topic, sellerDTO);
            return "seller data published";
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/bidProduct")
    public String bidAndPublish(@RequestBody Buyer buyerDTO) {
        buyerRepository.save(buyerDTO);
        template.send(topic, buyerDTO);
        return "bidder data published";
    }

    @GetMapping ("/getSellerByProduct")
    //public SellerDTO getProduct(@PathVariable String productName) {
    public List<Seller> getSellerByProduct() {
        return sellerRepository.findByProductName("ring");
    }
}
