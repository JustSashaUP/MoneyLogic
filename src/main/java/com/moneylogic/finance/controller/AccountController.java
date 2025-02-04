package com.moneylogic.finance.controller;

import com.moneylogic.finance.logging.LoggerSingleton;
import com.moneylogic.finance.model.Account;
import com.moneylogic.finance.model.CustomUserDetails;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.service.AccountServiceImpl.AccountService;
import com.moneylogic.finance.service.CustomUserDetailsService;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profile/accounts")
public class AccountController {

    @Autowired
    public UserService userService;

    @Autowired
    public AccountService accountService;

    @GetMapping("/list")
    public ResponseEntity<?> getAccountsList(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Long userId = userDetails.getId();

        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                LoggerSingleton.warn(AccountController.class, "User not found with ID: " + userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
            }

            List<Account> accounts = accountService.getAllAccountsByUserId(userId);

            if (accounts == null || accounts.isEmpty()) {
                LoggerSingleton.info(AccountController.class, "Accounts not found for user ID: " + userId);
                return ResponseEntity.ok().body(Collections.emptyList());
            }

            user.setAccounts(accounts);
            LoggerSingleton.info(AccountController.class, "User's accounts count = " + accounts.size());
            return ResponseEntity.ok().body(accounts);
        } catch (Exception e) {
            LoggerSingleton.error(AccountController.class, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/switch-account/{accountId}")
    public ResponseEntity<String> switchAccount(@AuthenticationPrincipal CustomUserDetails userDetails,
                                           @PathVariable Long accountId) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!userDetails.getAccountIds().contains(accountId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied to this account");
        }

        userDetails.setActiveAccountId(accountId);
        LoggerSingleton.info(AccountController.class, "Switched to account ID " + userDetails.getActiveAccountId());
        return ResponseEntity.ok("Switched to account ID: " + accountId);
    }

//    @PostMapping("/create-account")
//    public ResponseEntity<?> addNewAccount() {
//
//    }
    //TODO complete this later
}