package com.moneylogic.finance.model;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

@Setter
@Getter
public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    /**
     * Date when the user registered
     * */
    private LocalDate userRegistrationDate;

    private User() {}

    public static User createUser(String username, String email, String password, String userRegistrationDate) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.userRegistrationDate = parseToLocalDate(userRegistrationDate);
        return user;
    }

    public static User createUserWithCurrentDate(String username, String email, String password) {
        User user = new User();
        user.username = username;
        user.email = email;
        user.password = password;
        user.userRegistrationDate = LocalDate.now();
        return user;
    }

    private static LocalDate parseToLocalDate(String date) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRegistrationDate=" + userRegistrationDate +
                '}';
    }
}
