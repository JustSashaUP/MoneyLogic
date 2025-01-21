package com.moneylogic.finance.service;

import com.moneylogic.finance.model.MyUserDetails;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(login);
        System.out.println("Loaded user: " + user);

        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login: " + login));
    }
}
