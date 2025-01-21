package com.moneylogic.finance.service;

import com.moneylogic.finance.model.User;

import java.util.Optional;

public interface UserService {
    String saveUser(User user);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
