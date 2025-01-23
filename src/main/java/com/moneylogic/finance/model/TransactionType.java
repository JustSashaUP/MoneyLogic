package com.moneylogic.finance.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum TransactionType {
    INCOME("income") {
        public BigDecimal apply(BigDecimal amount, BigDecimal total) {
            return total.add(amount);
        }
    },
    EXPENSE("expense") {
        public BigDecimal apply(BigDecimal amount, BigDecimal total) {
            return total.subtract(amount);
        }
    };

    private final String type;

    TransactionType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction Type = " + type;
    }

    public abstract BigDecimal apply(BigDecimal amount, BigDecimal total);
}
