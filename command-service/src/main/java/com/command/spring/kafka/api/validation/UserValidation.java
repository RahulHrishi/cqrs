package com.command.spring.kafka.api.validation;

import com.command.spring.kafka.api.util.AuctionUtil;
import com.commons.Excption.ValidationException;
import com.commons.dto.Constants;
import com.commons.dto.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class UserValidation {

    public boolean validateUser(UserInfo info) throws ValidationException {

        try {
            if(StringUtils.isBlank(info.getFirst_name()))
                throw new ValidationException(Constants.NAME_BLANK);
            char[] firstNameArr = (info.getFirst_name()).toCharArray();
            if (firstNameArr.length<5) {
                throw new ValidationException(Constants.NAME_MIN_LENGTH);
            } else if(firstNameArr.length>30) {
                throw new ValidationException(Constants.NAME_MAX_LENGTH);
            }
            if(StringUtils.isBlank(info.getLast_name()))
                throw new ValidationException(Constants.LAST_NAME_NULL);
            char[] lastNameArr = (info.getLast_name()).toCharArray();
            if (lastNameArr.length<3) {
                throw new ValidationException(Constants.LAST_NAME_MIN_LENGTH);
            } else if(lastNameArr.length>25) {
                throw new ValidationException(Constants.LAST_NAME_MAX_LENGTH);
            }
            if(!AuctionUtil.validateMailId(info.getEmail()))
                throw new ValidationException(Constants.INVALID_MAIL_ID);
            if (AuctionUtil.getLongLength(info.getPhone())<10) {
                throw new ValidationException(Constants.PHONE_MIN_LENGTH);
            } else if (AuctionUtil.getLongLength(info.getPhone())>10) {
                throw new ValidationException(Constants.PHONE_MAX_LENGTH);
            }
            return true;
        } catch (ValidationException ve ) {
            throw new ValidationException(ve.getMessage());
        }
    }
}
