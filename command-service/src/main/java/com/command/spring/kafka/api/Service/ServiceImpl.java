package com.command.spring.kafka.api.Service;

import com.command.spring.kafka.api.Excption.CommandException;
import com.command.spring.kafka.api.config.Actuator;
import com.command.spring.kafka.api.config.SequenceGeneratorService;
import com.command.spring.kafka.api.repository.BuyerRepository;
import com.command.spring.kafka.api.repository.SellerRepository;
import com.command.spring.kafka.api.validation.CommandValidation;
import com.commons.Excption.ValidationException;
import com.commons.dto.Buyer;
import com.commons.dto.Constants;
import com.commons.dto.Seller;
import com.commons.dto.*;
import com.commons.logger.AuctionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServiceImpl {
    @Autowired
    private KafkaTemplate<String, Object> template;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ConnectQueryService connectionService;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private CommandValidation validation;

    @Autowired
    private Actuator actuator;

    @Transactional
    public void saveSeller (Seller seller) throws CommandException {

        String method = "saveSeller";
        actuator.checkHealth();

        seller.setId((int) sequenceGeneratorService.generateSequence(Seller.SEQUENCE_NAME));
        seller.getProduct().setProductId(seller.getId());
        sellerRepository.save(seller);
        AuctionLogger.infoLog(this.getClass(), method, "Created Seller: "+seller.getId());
        template.send(Constants.SELL_T, seller);
    }
    @Transactional
    public void saveBuyer (Buyer buyer) throws CommandException {

        String method = "saveBuyer";
        actuator.checkHealth();

        Optional<Seller> seller=connectionService.findSellerByProductId(buyer.getProductId());
        Seller optSeller=seller.orElseThrow(()-> new ValidationException(Constants.PRODUCT_NA));
        if(optSeller.getProduct().getEndDate().compareTo(new Date())>0){
            if(!connectionService.findBuyerByProductId(buyer.getProductId(),buyer.getInfo().getEmail()).isPresent()) {
                buyer.setId((int) sequenceGeneratorService.generateSequence(Buyer.SEQUENCE_NAME));
                buyerRepository.save(buyer);
                AuctionLogger.infoLog(this.getClass(), method, "Created Buyer: "+buyer.getId());
                template.send(Constants.BID_T, buyer);
            }else{
                throw new ValidationException(Constants.BID_EXIST);
            }
        }else{
            throw new ValidationException(Constants.BID_EXPIRED);
        }
    }

    @Transactional
    public void updateBid (Integer productId, String emailId, Integer newBidAmount) throws CommandException, ValidationException {

        String method = "updateBid";
        actuator.checkHealth();

        Optional<Buyer> optBuyer = connectionService.findBuyerByProductId(productId, emailId);
        Buyer buyer=optBuyer.orElseThrow(() -> new ValidationException(Constants.BUYER_NA));
        Optional<Seller> optSeller = connectionService.findSellerByProductId(buyer.getProductId());
        Product product = optSeller.get().getProduct();
        if (product.getEndDate().compareTo(new Date())<0) {
            buyer.setBidAmount(newBidAmount);
            buyerRepository.save(buyer);
            AuctionLogger.infoLog(this.getClass(), method, "Updated Bid Amount: "+newBidAmount);
        } else {
            throw new ValidationException(Constants.AUCTION_EXPIRED_BID_UPDATE);
        }
        template.send(Constants.BID_T, buyer);
    }

    @Transactional
    public String deleteProduct (Integer productId) throws CommandException {

        String method = "deleteProduct";
        actuator.checkHealth();

        Optional<MappedProductModel> optSeller = connectionService.findSellerWithBids(productId);
        if(null==optSeller.get().getSeller())
            throw new ValidationException(Constants.SELLER_NA);
        MappedProductModel mappedModel = optSeller.get();
        if(mappedModel.getSeller().getProduct().getEndDate().compareTo(new Date())>0){
            throw new ValidationException(Constants.Auction_EXPIRED_DEL);
        }
        if (mappedModel.getBuyer().isEmpty()) {
            sellerRepository.delete(mappedModel.getSeller());
            AuctionLogger.infoLog(this.getClass(), method, "deleted Product: "+productId);
            template.send(Constants.SELL_D, mappedModel.getSeller());
            return Constants.PRODUCT_DEL;
        } else {
            throw new ValidationException(Constants.BID_EXIST_DEL);
        }
    }

    public Set<Index> getAllProduct() throws CommandException {

        String method = "getAllProduct";
        actuator.checkHealth();

        Set<Index> indexList = new HashSet<>();
        sellerRepository.findAll().stream().map(bean -> bean.getProduct()).collect(Collectors.toList())
                .forEach(prod -> { indexList.add(new Index(prod.getProductId(), prod.getProductName()));
        });
        AuctionLogger.infoLog(this.getClass(), method, "Fetching total: "+indexList.size()+ " Products.");
        return indexList;
    }
}
