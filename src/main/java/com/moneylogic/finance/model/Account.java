package com.moneylogic.finance.model;

import com.moneylogic.finance.util.CommonUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "balance", precision = 38, scale = 2)
    private BigDecimal balance = BigDecimal.valueOf(0.0);
    /**
     * Date when the user created the current account
     * */
    @Column(name = "created_date")
    private LocalDate accountCreatedDate;
    /**
     * Need for correctly cascade removing
     * */
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactionList = new ArrayList<>();

    /**
     * Transactions related to the user's account
     * */
    @Transient
    private Optional<List<Transaction>> transactions;
    /**
     * Category templates related to the user's account
     * */
    @Transient
    private Optional<List<Category>> categories;

    protected Account() {}

    public static Account createAccount(User user, String name, BigDecimal balance,
                                        List<Transaction> transactions, List<Category> categories, String accountCreatedDate) {
        Account account = new Account();
        account.user = user;
        account.name = name;
        account.balance = balance;
        account.transactions = Optional.ofNullable(transactions);
        account.categories = Optional.ofNullable(categories);
        account.accountCreatedDate = CommonUtils.parseToLocalDate(accountCreatedDate);
        return account;
    }

    public static Account createAccountWithCurrentDate(User user, String name, BigDecimal balance,
                                                       List<Transaction> transactions, List<Category> categories) {
        Account account = new Account();
        account.user = user;
        account.name = name;
        account.balance = balance;
        account.transactions = Optional.ofNullable(transactions);
        account.categories = Optional.ofNullable(categories);
        account.accountCreatedDate = LocalDate.now();
        return account;
    }

    public static Account createEmptyAccountWithCurrentDate(User user, String name, BigDecimal balance) {
        Account account = new Account();
        account.user = user;
        account.name = name;
        account.balance = balance;
        account.accountCreatedDate = LocalDate.now();
        return account;
    }

    public void setTransactions(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            this.balance = transaction.getTransactionType().apply(transaction.getAmount(), balance);
        }
        this.transactions = Optional.of(transactions);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(user, account.user) &&
                Objects.equals(name, account.name) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(accountCreatedDate, account.accountCreatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, balance, accountCreatedDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", user=" + user.getEmail() +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", accountCreatedDate=" + accountCreatedDate +
                '}';
    }
}
