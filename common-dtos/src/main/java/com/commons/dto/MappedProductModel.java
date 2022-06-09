package com.commons.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MappedProductModel {
    protected Seller seller;
    protected List<Buyer> buyer;
}
