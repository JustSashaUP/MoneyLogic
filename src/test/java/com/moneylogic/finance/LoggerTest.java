package com.moneylogic.finance;

import com.moneylogic.finance.logging.LoggerSingleton;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoggerTest {

    @Test
    public void writingLogToConsoleAndFile() {
        LoggerSingleton.info(LoggerTest.class, "Test Console/File Output!");
        LoggerSingleton.info(LoggerTest.class, "Done!");
    }
}
