package com.moneylogic.finance.service;

import com.moneylogic.finance.logging.LoggerSingleton;
import com.moneylogic.finance.model.CustomUserDetails;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.User.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmailOrUsername(login,login);
        LoggerSingleton.info(CustomUserDetails.class, "Loaded user: " + user);

        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
    }
}
