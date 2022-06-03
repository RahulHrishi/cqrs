package com.command.spring.kafka.api.validation;

import org.apache.commons.lang3.StringUtils;

import com.command.spring.kafka.api.util.DateUtil;
import com.commons.Excption.ValidationException;
import com.commons.dto.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductValidation {

    public boolean validateProduct (Product product) throws ValidationException {

        try {
            if (!DateUtil.checkFutureDate(product.getEndDate()))
                throw new ValidationException("Bid end date should be a future date.");
            if(StringUtils.isBlank(product.getProductName()))
                throw new ValidationException("Product Name can not be null.");
            char[] productNameArr = (product.getProductName()).toCharArray();
            if (productNameArr.length<5) {
                throw new ValidationException("Product name length minimum 5 char required.");
            } else if(productNameArr.length>30) {
                throw new ValidationException("Product name length maximum 30 char allowed.");
            }
            return true;
        } catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
    }
}
