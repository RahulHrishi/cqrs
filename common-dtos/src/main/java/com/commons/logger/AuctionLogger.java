package com.commons.logger;

import java.util.logging.Logger;


public class AuctionLogger {

    static final String space = ": ";

    public static void infoLog(Class<?> obj, String method, String message) {
        Logger logger = Logger.getLogger(obj.getName().trim());
        logger.info(formatMessage(obj, method, message));
    }

    public static void errorLog(Class<?> obj, String method, String message, Throwable th) {
        Logger logger = Logger.getLogger(obj.getName().trim());
        logger.info(formatMessage(obj, method, message+th.getMessage()) );
    }

    private static String formatMessage (Class<?> obj, String method, String message) {

        StringBuffer sbuf = new StringBuffer(obj.getName())
                .append(space).append("method-").append(method).append(space).append(message);
        String msg =  sbuf.toString();

        return msg;
    }
}
