package com.moneylogic.finance.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    //@OneToOne(mappedBy = "category_id")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "type")
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    protected Transaction() {}

    public static Transaction createTransaction(User user, Account account, Category category, BigDecimal amount, TransactionType transactionType, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.category = category;
        transaction.amount = amount;
        transaction.transactionType = transactionType;
        transaction.description = description;
        return transaction;
    }

    public static Transaction createTransactionWithoutCategory(User user, Account account, BigDecimal amount, TransactionType transactionType, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.category = null;
        transaction.amount = amount;
        transaction.transactionType = transactionType;
        transaction.description = description;
        return transaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                Objects.equals(user, that.user) &&
                Objects.equals(account, that.account) &&
                Objects.equals(category, that.category) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, account, category, amount, description);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                ", category=" + category +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                '}';
    }
}
