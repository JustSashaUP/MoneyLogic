package com.moneylogic.finance.service.UserServiceImpl;

import com.moneylogic.finance.model.User;

import java.util.List;

public interface UserService {
    String saveUser(User user);

    List<User> getAllUser();

    User getUserByEmail(String email);

    User getUserById(Long id);

    void deleteUserById(Long id);
}
