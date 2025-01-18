package com.moneylogic.finance.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@ToString
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    /**
     * Date when the user registered
     * */
    private LocalDate userRegistrationDate;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userRegistrationDate, user.userRegistrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, userRegistrationDate);
    }
}
