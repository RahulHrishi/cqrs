package com.commons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappedProductModel {
    protected Seller seller;
    protected List<Buyer> buyer;
}
