package com.command.spring.kafka.api.Service;

import com.commons.dto.Buyer;
import com.commons.dto.MappedProductModel;
import com.commons.dto.Seller;
import com.commons.logger.AuctionLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ConnectQueryService {

    @Value("${queryService.buyer.url}")
    private String buyerUrl;

    @Value("${queryService.seller.url}")
    private String sellerUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Optional<Seller> findSellerByProductId(Integer productId) {

        String method = "findSellerByProductId";
        String getProductUrl = sellerUrl + "getSellerByProductId/?productId=" + productId;
        AuctionLogger.infoLog(this.getClass(), method, "Created Url: "+sellerUrl);
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), Seller.class).getBody());
    }

    public Optional<Buyer> findBuyerByProductId(Integer productId, String email) {

        String method = "findBuyerByProductId";
        String getProductUrl = buyerUrl + "getBuyerByIdAndEmail/?productId=" + productId +"&email="+email;
        AuctionLogger.infoLog(this.getClass(), method, "Created Url: "+buyerUrl);
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), Buyer.class).getBody());
    }

    private HttpEntity<String> getHttpHeaders() {

        String method = "getHttpHeaders";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        AuctionLogger.infoLog(this.getClass(), method, "Headers created successfully.");
        return new HttpEntity<>(headers);
    }

    @GetMapping("/findSellerWithBids")
    public Optional<MappedProductModel> findSellerWithBids(Integer productId){

        String method = "findSellerWithBids";
        String getProductUrl = buyerUrl + "findSellerWithBids/?productId=" + productId;
        AuctionLogger.infoLog(this.getClass(), method, "Created Url: "+getProductUrl);
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), MappedProductModel.class).getBody());
    }
}
