package com.moneylogic.finance.controller;

import com.moneylogic.finance.logging.LoggerSingleton;
import com.moneylogic.finance.model.Account;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.service.AccountServiceImpl.AccountService;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/accounts")
public class AccountController {

    @Autowired
    public UserService userService;

    @Autowired
    public AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllAccounts(@PathVariable("id") Long id) {
        if (id <= 0) {
            LoggerSingleton.error(AccountController.class,
                    "user with id = " + id  + "not found!");
            return ResponseEntity.badRequest().body("Invalid user ID.");
        }
        Optional<User> user;
        Optional<List<Account>> accounts = Optional.empty();
        try {
            user = Optional.ofNullable(userService.getUserById(id));
            if (user.isPresent()) {
                accounts = Optional.ofNullable(accountService.getAllAccountsByUserId(id));
                accounts.ifPresent(accountList -> user.get().setAccounts(accountList));
                LoggerSingleton.info(AccountController.class,
                        "get user's accounts = " + user.get().getAccounts().size());
            }
            LoggerSingleton.info(AccountController.class, accounts.get().toString());
        } catch (Exception e) {
            LoggerSingleton.error(AccountController.class, e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(accounts.get());
    }
}