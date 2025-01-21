package com.moneylogic.finance.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public MyUserDetails(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращаем пустой список, если не нужно делить пользователей по ролям
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;  // Или user.getUsername() в зависимости от требований
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;  // Предполагается, что пользователи всегда активны
    }
}