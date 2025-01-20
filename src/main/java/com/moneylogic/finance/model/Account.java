package com.moneylogic.finance.model;

import com.moneylogic.finance.util.CommonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Account {
    private long id;
    private User user;
    private String name;
    private BigDecimal balance = BigDecimal.valueOf(0.0);
    /**
     * Date when the user created the current account
     * */
    private LocalDate accountCreatedDate;
    /**
     * Transactions related to the user's account
     * */
    private List<Transaction> transactions;
    /**
     * Catalog templates related to the user's account
     * */
    private List<Catalog> catalogs;

    private Account() {}

    public static Account createAccount(User user, String name, BigDecimal balance,
                                        List<Transaction> transactions, List<Catalog> catalogs, String accountCreatedDate) {
        Account account = new Account();
        account.user = user;
        account.name = name;
        account.balance = balance;
        account.transactions = transactions;
        account.catalogs = catalogs;
        account.accountCreatedDate = CommonUtils.parseToLocalDate(accountCreatedDate);
        return account;
    }

    public static Account createAccountWithCurrentDate(User user, String name, BigDecimal balance,
                                                       List<Transaction> transactions, List<Catalog> catalogs) {
        Account account = new Account();
        account.user = user;
        account.name = name;
        account.balance = balance;
        account.transactions = transactions;
        account.catalogs = catalogs;
        account.accountCreatedDate = LocalDate.now();
        return account;
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
                ", user=" + user +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", accountCreatedDate=" + accountCreatedDate +
                ", transactions=" + transactions +
                ", catalogs=" + catalogs +
                '}';
    }
}
