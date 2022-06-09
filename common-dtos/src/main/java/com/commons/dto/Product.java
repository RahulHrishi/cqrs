package com.commons.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private Integer startPrice;
    private String productName;
    private String shortDesc;
    private String detailDesc;
    private String category;
    private Date endDate;
}
