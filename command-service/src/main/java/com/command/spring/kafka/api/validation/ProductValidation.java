package com.command.spring.kafka.api.validation;

import com.commons.dto.Constants;
import com.commons.dto.ProductCategory;
import org.apache.commons.lang3.EnumUtils;
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
                throw new ValidationException(Constants.BID_END_DATE);
            if(StringUtils.isBlank(product.getProductName()))
                throw new ValidationException(Constants.PRODUCT_NAME_BLANK);
            char[] productNameArr = (product.getProductName()).toCharArray();
            if (productNameArr.length<5) {
                throw new ValidationException(Constants.PRODUCT_NAME_MIN_LENGTH);
            } else if(productNameArr.length>30) {
                throw new ValidationException(Constants.PRODUCT_NAME_MAX_LENGTH);
            }
            boolean flag = EnumUtils.isValidEnum(ProductCategory.class, product.getCategory());
            if(!flag)  {
                throw new ValidationException(Constants.PRODUCT_CATEGORY_INVALID);
            }
            return true;
        } catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
    }
}
