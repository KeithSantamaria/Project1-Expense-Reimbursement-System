package com.ex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static final Logger rootLogger = LogManager.getRootLogger();
    public static void main(String[] args) {
        logger.debug("Hello, DEBUG");
    }
}
