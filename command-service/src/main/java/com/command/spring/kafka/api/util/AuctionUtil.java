package com.command.spring.kafka.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuctionUtil {

    public static boolean validateMailId (String mailId) {
        boolean valid = false;
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        try {
            Pattern pattern = Pattern.compile(regex);
            Matcher matches = pattern.matcher(mailId);
            valid = matches.matches();
        } catch (Exception e) {

        }
        return valid;
    }

    public static int getLongLength (Long number) {
        int length = 0;
        try {
           String strNum = ""+number;
           char[] charArr = strNum.toCharArray();
           length = charArr.length;
        } catch (Exception e) {

        }
        return length;
    }
}
