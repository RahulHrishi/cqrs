package com.query.spring.kafka.api.Service;

import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import com.commons.dto.MappedProductModel;
import com.commons.logger.AuctionLogger;
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

        String method = "findSellerWithBids";
        MappedProductModel obj = new MappedProductModel();
        obj.setSeller(sellerRepository.findByProductId(productId));
        obj.setBuyer(buyerRepository.findByProductIdOrderByBidAmountDesc(productId));
        AuctionLogger.infoLog(this.getClass(), method, "Fetch Bid successfully for product: "+productId);
        return Optional.of(obj);
    }

    public Optional<Seller> getSellerByProductId(Integer productId) throws QueryException {

        String method = "getSellerByProductId";
        AuctionLogger.infoLog(this.getClass(), method, "Fetching product owner: "+productId);
        return Optional.ofNullable(sellerRepository.findByProductId(productId));
    }

    public void delete(Seller seller) throws QueryException {

        String method = "delete";
        sellerRepository.delete(seller);
        AuctionLogger.infoLog(this.getClass(), method, "Seller deleted successfully: ");
    }

    public Optional<Buyer> getBuyerByIdAndEmail(Integer productId, String email) throws QueryException {

        String method = "getBuyerByIdAndEmail";
        AuctionLogger.infoLog(this.getClass(), method, "Fetching Buyer for product: " +productId+ " with Email Id: "+email);
        return buyerRepository.findByProductIdOrderByBidAmountDesc(productId).stream().filter(bean -> bean.getInfo().getEmail().equals(email)).findFirst();
    }

}
