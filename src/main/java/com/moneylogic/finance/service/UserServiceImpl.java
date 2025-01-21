package com.moneylogic.finance.service;


import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public String saveUser(User user) {
        // Проверка на уникальность имени пользователя
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username " + user.getUsername() + " already exists.";
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email" + user.getEmail() + " already exists.";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "User " + user.getUsername() + " successfully registered.";
    }

}