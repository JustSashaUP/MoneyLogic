package com.moneylogic.finance.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CommonUtils {
    public static LocalDate parseToLocalDate(String date) {
        if (isValidFormat(date)) {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return LocalDate.of(0, 1, 1);
    }

    private static boolean isValidFormat(String date) {
        if (date == null || date.isEmpty()) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            e.getMessage(); //TODO Fix me
            return false;
        }
        return true;
    }
}
