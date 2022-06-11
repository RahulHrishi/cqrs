package com.command.spring.kafka.api.Service;

import com.commons.dto.Buyer;
import com.commons.dto.MappedProductModel;
import com.commons.dto.Seller;
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
        String getProductUrl = sellerUrl + "getSellerByProductId/?productId=" + productId;
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), Seller.class).getBody());
    }

    public Optional<Buyer> findBuyerByProductId(Integer productId, String email) {
        String getProductUrl = buyerUrl + "getBuyerByIdAndEmail/?productId=" + productId +"&email="+email;
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), Buyer.class).getBody());
    }

    private HttpEntity<String> getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return new HttpEntity<>(headers);
    }

    @GetMapping("/findSellerWithBids")
    public Optional<MappedProductModel> findSellerWithBids(Integer productId){
        String getProductUrl = buyerUrl + "findSellerWithBids/?productId=" + productId;
        return Optional.ofNullable(restTemplate.exchange(getProductUrl, HttpMethod.GET, getHttpHeaders(), MappedProductModel.class).getBody());
    }
}
