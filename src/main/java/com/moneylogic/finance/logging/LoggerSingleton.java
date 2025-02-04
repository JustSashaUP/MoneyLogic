package com.moneylogic.finance.logging;

import org.apache.log4j.Logger;
import org.slf4j.event.Level;

import static org.apache.log4j.Level.ERROR;
import static org.apache.log4j.Level.WARN;

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
        getLogger(classLog).info("ℹ️" + message);
    }

    public static void error(Class<?> classLog, String message) {
        getLogger(classLog).log(ERROR, "❌" + message);
    }

    public static void warn(Class<?> classLog, String message) {
        getLogger(classLog).log(WARN, "⚠️" + message);
    }
}
