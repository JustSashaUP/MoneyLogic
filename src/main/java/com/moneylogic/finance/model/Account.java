package com.moneylogic.finance.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@ToString
public class Account {
    private long id;
    private User user;
    private String name;
    private BigDecimal balance = BigDecimal.valueOf(0.0);
    /**
     * Date when the user created the current account
     * */
    private LocalDate accountCreatedDate;

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
}
