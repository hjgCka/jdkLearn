package com.hjg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @Description
 * @Author hjg
 * @Date 2025-07-01 1:11
 */
public class SimpleMDC {

    public static void main(String[] args) {
        MDC.put("first", "Dorothy");

        Logger logger = LoggerFactory.getLogger(SimpleMDC.class);

        MDC.put("last", "Parker");

        logger.info("Check enclosed.");
        logger.info("The most beautiful two words in English");

        MDC.put("first", "Richard");
        MDC.put("last", "Nixon");
        logger.info("I am not a crook.");
        logger.info("Attributed to the former US president. 17 Nov 1973.");

        MDC.clear();
    }
}
