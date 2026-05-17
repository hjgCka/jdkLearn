package com.hjg.foo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bar {

    private static final Logger logger = LogManager.getLogger(Bar.class);

    public boolean doIt() {
        logger.entry();
        logger.error("Did it again");
        return logger.exit(false);
    }
}
