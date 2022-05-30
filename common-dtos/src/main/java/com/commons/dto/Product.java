package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@AllArgsConstructor
public class Product {
    private Integer productId;
    private Integer startPrice;
    private String productName;
    private String shortDesc;
    private String detailDesc;
    private String category;
    private Date endDate;
}
