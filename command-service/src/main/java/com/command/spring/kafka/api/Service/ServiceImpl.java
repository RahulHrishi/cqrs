package com.command.spring.kafka.api.Service;

import com.command.spring.kafka.api.Excption.CommandException;
import com.command.spring.kafka.api.config.Actuator;
import com.command.spring.kafka.api.config.SequenceGeneratorService;
import com.command.spring.kafka.api.repository.BuyerRepository;
import com.command.spring.kafka.api.repository.SellerRepository;
import com.commons.dto.Buyer;
import com.commons.dto.Constants;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceImpl {
    @Autowired
    private KafkaTemplate<String, Object> template;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private Actuator actuator;



    @Transactional
    public void saveSeller(Seller seller) throws CommandException {
        actuator.checkHealth();
        seller.setId((int) sequenceGeneratorService.generateSequence(Seller.SEQUENCE_NAME));
        seller.getProduct().setProductId(seller.getId());
        seller.getInfo().setId(seller.getId());
        sellerRepository.save(seller);
        template.send(Constants.SELL_T, seller);
    }
    @Transactional
    public void saveBuyer(Buyer buyer) throws CommandException {
        actuator.checkHealth();
        buyer.setId((int) sequenceGeneratorService.generateSequence(Buyer.SEQUENCE_NAME));
        buyer.getInfo().setId((int) sequenceGeneratorService.generateSequence(Buyer.SEQUENCE_NAME));
        buyerRepository.save(buyer);
        template.send(Constants.BID_T, buyer);
    }
    @Transactional
    public void delete(Integer productId) throws CommandException {
        actuator.checkHealth();
        Optional<Seller> seller = sellerRepository.findByProductId(productId);
        sellerRepository.delete(seller.get());
        template.send(Constants.SELL_D, seller.get());
    }
}
