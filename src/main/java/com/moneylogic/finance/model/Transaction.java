package com.moneylogic.finance.model;

import com.moneylogic.finance.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "amount", precision = 38, scale = 2)
    private BigDecimal amount;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    protected Transaction() {}

    public static Transaction createTransaction(User user, Account account, Category category,
                                                TransactionType transactionType, BigDecimal amount,
                                                String transactionDate, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.category = category;
        transaction.amount = amount;
        transaction.transactionType = transactionType;
        transaction.transactionDate = CommonUtils.parseToLocalDate(transactionDate);
        transaction.description = description;
        return transaction;
    }

    public static Transaction createTransactionWithoutCategory(User user, Account account, BigDecimal amount,
                                                               TransactionType transactionType,
                                                               String transactionDate, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.category = null;
        transaction.amount = amount;
        transaction.transactionType = transactionType;
        transaction.transactionDate = CommonUtils.parseToLocalDate(transactionDate);
        transaction.description = description;
        return transaction;
    }

    public static Transaction createTransactionWithCurrentDate(User user, Account account, Category category,
                                                TransactionType transactionType, BigDecimal amount, String description) {
        Transaction transaction = new Transaction();
        transaction.user = user;
        transaction.account = account;
        transaction.category = category;
        transaction.amount = amount;
        transaction.transactionType = transactionType;
        transaction.transactionDate = LocalDate.now();
        transaction.description = description;
        return transaction;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
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
                ", dto=" + category +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                '}';
    }
}
