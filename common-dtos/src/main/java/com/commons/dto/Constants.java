package com.commons.dto;

import com.commons.Excption.ValidationException;
import org.apache.commons.lang3.StringUtils;

public class Constants {
    public static final String SELL_T = "AUCTION-SELL";

    public static final String BID_T = "AUCTION-BID";

    public static final String GRP_ID_SELL = "SELL";

    public static final String GRP_ID_BUY = "BUY";

    public static final String SELL_D = "SELL-DEL";

    public static final String BIDDER_PUBLISHED = "Bidder data published";

    public static final String BIDDER_UPDATED = "Bidder data updated";

    public static final String SELL_PUBLISHED = "Seller data published";

    public static final String PRODUCT_DEL = "Product deleted";

    public static final String PRODUCT_NA = "Product not Found";

    public static final String BID_EXIST = "BID was already placed by the user";

    public static final String BID_EXPIRED = "Bid end date was over, hence can't place bid";

    public static final String BUYER_NA = "No such Buyer with this Email Id";

    public static final String SELLER_NA = "Seller not found";

    public static final String AUCTION_EXPIRED_BID_UPDATE = "Bid end date was over, hence can't update Bid amount";

    public static final String Auction_EXPIRED_DEL = "Bid end date was over ,hence can't delete";

    public static final String BID_EXIST_DEL = "Bid was placed to this product,hence can't delete";

    public static final String BID_EXPIRED_DEL = "Bid was expired,hence can't delete";

    public static final String BID_END_DATE = "Bid end date should be a future date.";

    public static final String PRODUCT_NAME_BLANK = "Product Name can not be blank.";

    public static final String PRODUCT_NAME_MIN_LENGTH = "Product name length minimum 5 char required.";

    public static final String PRODUCT_NAME_MAX_LENGTH = "Product name length maximum 30 char allowed.";

    public static final String PRODUCT_CATEGORY_INVALID = "Product category in not valid one.";

    public static final String NAME_BLANK = "First Name can not be blank.";

    public static final String NAME_MIN_LENGTH = "First name length minimum 5 char required.";

    public static final String NAME_MAX_LENGTH = "First name length maximum 30 char allowed.";

    public static final String LAST_NAME_NULL = "Last Name can not be null.";

    public static final String LAST_NAME_MIN_LENGTH = "Last name length minimum 3 char required.";

    public static final String LAST_NAME_MAX_LENGTH = "Last name length maximum 25 char allowed.";

    public static final String INVALID_MAIL_ID = "Mail Id is not a valid one.";

    public static final String PHONE_MIN_LENGTH = "Phone number should be 10 digit.";

    public static final String PHONE_MAX_LENGTH = "Phone number should not be more than 10 digit.";

}
