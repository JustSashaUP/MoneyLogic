package com.moneylogic.finance.model.dto;

import java.math.BigDecimal;

public class CategoryMaxAmount {
    private String category;
    private BigDecimal maxAmount;

    public CategoryMaxAmount(String category, BigDecimal maxAmount) {
        this.category = category;
        this.maxAmount = maxAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "CategoryMaxAmount{" +
                "category='" + category + '\'' +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
