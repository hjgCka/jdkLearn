package com.hjg.foo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BarCaller {

    private static final Logger logger = LogManager.getLogger(BarCaller.class);

    public static void callBar() {
        logger.trace("entering application");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            logger.error("Didn't do it");
        }
        logger.trace("exiting application");
    }
}
