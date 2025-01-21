package com.moneylogic.finance.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Transaction {
    private long id;
    private User user;
    private Account account;
    private Category catalog;
    private BigDecimal amount;
    private String description;

    private Transaction() {}

    public static Transaction createTransaction(User user, Account account, Category catalog, BigDecimal amount, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.catalog = catalog;
        transaction.amount = amount;
        transaction.description = description;
        return transaction;
    }

    public static Transaction createTransactionWithoutCategory(User user, Account account, BigDecimal amount, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.catalog = null;
        transaction.amount = amount;
        transaction.description = description;
        return transaction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(account, that.account) &&
                Objects.equals(catalog, that.catalog) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, account, catalog, amount, description);
    }
}
