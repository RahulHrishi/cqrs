package com.query.spring.kafka.api.Service;

import com.commons.Excption.AuctionException;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import com.commons.dto.MappedProductModel;
import com.query.spring.kafka.api.repository.BuyerRepository;
import com.query.spring.kafka.api.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceImpl {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;


    public void consumeSeller(Seller seller) throws AuctionException {
        sellerRepository.save(seller);
    }

    public void consumeBidder(Buyer buyer) throws AuctionException {
        buyerRepository.save(buyer);
    }

    public MappedProductModel findByProduct(Integer productId) throws AuctionException {
        MappedProductModel obj = new MappedProductModel();
        obj.setSeller(sellerRepository.findByProductId(productId));
        obj.setBuyer(buyerRepository.findByProductId(productId));
        return obj;
    }
}
