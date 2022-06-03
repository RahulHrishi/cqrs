package com.command.spring.kafka.api.validation;

import com.commons.Excption.ValidationException;
import com.commons.dto.Buyer;
import com.commons.dto.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommandValidation {

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private UserValidation userValidation;

    public boolean validateSaveProduct (Seller sellerDTO) throws ValidationException {
        boolean flag = false;
        try {
            boolean validProduct = productValidation.validateProduct(sellerDTO.getProduct());
            boolean validSeller = userValidation.validateUser(sellerDTO.getInfo());
            if (validProduct && validSeller) {
                flag = true;
            }
        }  catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
        return flag;
    }

    public boolean validateBidProduct (Buyer buyerDTO) throws ValidationException {
        boolean flag = false;
        try {
            boolean validBuyer = userValidation.validateUser(buyerDTO.getInfo());
            if (validBuyer) {
                flag = true;
            }
        }  catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
        return flag;
    }
}
