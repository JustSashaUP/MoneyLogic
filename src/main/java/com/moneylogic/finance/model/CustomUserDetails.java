package com.moneylogic.finance.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;
    private List<Long> accountIds;
    private Long  activeAccountId;


    public CustomUserDetails(User user){
        this.user = user;
        this.accountIds = user.getAccounts().stream()
                .map(Account::getId)
                .collect(Collectors.toList());
        this.activeAccountId = accountIds.isEmpty() ? null : accountIds.get(0);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Возвращаем пустой список, если не нужно делить пользователей по ролям
        return Collections.singletonList(new SimpleGrantedAuthority(user.getUsername()));
    }

    public Long getId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public Long getActiveAccountId() {
        return activeAccountId;
    }

    public void setActiveAccountId(Long activeAccountId) {
        if (accountIds.contains(activeAccountId)) {
            this.activeAccountId = activeAccountId;
        }
    }

    @Override
    public boolean isAccountNonExpired() {
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