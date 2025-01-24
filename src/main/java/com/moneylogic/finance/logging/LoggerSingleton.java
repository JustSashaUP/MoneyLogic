package com.moneylogic.finance.logging;

import org.apache.log4j.Logger;

public class LoggerSingleton {
    private static Logger logger;

    private LoggerSingleton() {}

    public static Logger getLogger(Class<?> classLog) {
        if (logger == null) {
            synchronized (LoggerSingleton.class) {
                if (logger == null) {
                    logger = Logger.getLogger(classLog);
                }
            }
        }
        return logger;
    }

    public static void info(Class<?> classLog, String message) {
        getLogger(classLog).info(message);
    }

    public static void error(Class<?> classLog, String message) {
        getLogger(classLog).error(message);
    }

    public static void warn(Class<?> classLog, String message) {
        getLogger(classLog).warn(message);
    }
}
