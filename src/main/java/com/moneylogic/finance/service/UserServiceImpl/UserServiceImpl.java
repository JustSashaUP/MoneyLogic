package com.moneylogic.finance.service.UserServiceImpl;


import com.moneylogic.finance.model.Category;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}