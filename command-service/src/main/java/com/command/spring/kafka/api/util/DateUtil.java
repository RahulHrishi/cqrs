package com.command.spring.kafka.api.util;


import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static boolean checkFutureDate(Date date) {
        boolean future = false;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date today = new Date();
            Date input = format.parse(format.format(date));
            Date withoutTime = format.parse(format.format(today));
            if(input.after(withoutTime))
                future = true;
            else
                future = false;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return future;
    }
}
