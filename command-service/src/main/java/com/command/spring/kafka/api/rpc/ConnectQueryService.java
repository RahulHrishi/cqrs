package com.command.spring.kafka.api.rpc;

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
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ConnectQueryService {

    @Value("${queryService.buyer.url}")
    private String buyerUrl;

    @Value("${queryService.seller.url}")
    private String sellerUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Seller findSellerByProductId(Integer productId) {

        String getProductUrl = sellerUrl+"getProduct/?productId="+productId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
       Seller seller = restTemplate.exchange(getProductUrl, HttpMethod.GET, entity, Seller.class).getBody();

        return seller;
    }

    public List<Buyer> findBuyerByProductId(Integer productId) {

        String getProductUrl = buyerUrl+"getProducts/?productId="+productId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        MappedProductModel model = restTemplate.exchange(getProductUrl, HttpMethod.GET, entity, MappedProductModel.class).getBody();
        List<Buyer> buyer = model.getBuyer();

        return buyer;
    }
}
