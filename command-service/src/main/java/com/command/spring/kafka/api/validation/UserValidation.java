package com.command.spring.kafka.api.validation;

import com.command.spring.kafka.api.util.AuctionUtil;
import com.commons.Excption.ValidationException;
import com.commons.dto.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserValidation {

    public boolean validateUser(UserInfo info) throws ValidationException {

        try {
            if(StringUtils.isBlank(info.getFirst_name()))
                throw new ValidationException("First Name can not be null.");
            char[] firstNameArr = (info.getFirst_name()).toCharArray();
            if (firstNameArr.length<5) {
                throw new ValidationException("First name length minimum 5 char required.");
            } else if(firstNameArr.length>30) {
                throw new ValidationException("First name length maximum 30 char allowed.");
            }
            if(StringUtils.isBlank(info.getLast_name()))
                throw new ValidationException("Last Name can not be null.");
            char[] lastNameArr = (info.getLast_name()).toCharArray();
            if (lastNameArr.length<3) {
                throw new ValidationException("Last name length minimum 3 char required.");
            } else if(lastNameArr.length>25) {
                throw new ValidationException("Last name length maximum 25 char allowed.");
            }
            if(!AuctionUtil.validateMailId(info.getEmail()))
                throw new ValidationException("Mail Id is not a valid one.");
            if (AuctionUtil.getLongLength(info.getPhone())<10) {
                throw new ValidationException("Phone number should be 10 digit.");
            } else if (AuctionUtil.getLongLength(info.getPhone())>10) {
                throw new ValidationException("Phone number should not be more than 10 digit.");
            }
            return true;
        } catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
    }
}
