package com.query.spring.kafka.api.Service;

import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import com.commons.dto.MappedProductModel;
import com.query.spring.kafka.api.Excption.QueryException;
import com.query.spring.kafka.api.repository.BuyerRepository;
import com.query.spring.kafka.api.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceImpl {
    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;


    public void consumeSeller(Seller seller) throws QueryException {
        sellerRepository.save(seller);
    }

    public void consumeBidder(Buyer buyer) throws QueryException {
        buyerRepository.save(buyer);
    }

    public Optional<MappedProductModel> findSellerWithBids(Integer productId) throws QueryException {
        MappedProductModel obj = new MappedProductModel();
        obj.setSeller(sellerRepository.findByProductId(productId));
        obj.setBuyer(buyerRepository.findByProductId(productId));
        return Optional.of(obj);
    }

    public Optional<Seller> getSellerByProductId(Integer productId) throws QueryException {
        return Optional.ofNullable(sellerRepository.findByProductId(productId));
    }

    public void delete(Seller seller) throws QueryException {
        sellerRepository.delete(seller);
    }

    public Optional<Buyer> getBuyerByIdAndEmail(Integer productId, String email) throws QueryException {
        return buyerRepository.findByProductId(productId).stream().filter(bean -> bean.getInfo().getEmail().equals(email)).findFirst();
    }

}
