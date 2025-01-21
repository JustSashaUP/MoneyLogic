package com.moneylogic.finance.service;

import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // Загружаем информацию о пользователе из провайдера
        OAuth2User oAuth2User = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")), // Задаем роль по умолчанию
                userRequest.getAdditionalParameters(),
                "email" // Основной идентификатор
        );

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        // Проверяем, существует ли пользователь
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> createUserFromOAuth2(attributes));

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                oAuth2User.getAttributes(),
                "email"
        );
    }

    private User createUserFromOAuth2(Map<String, Object> attributes) {
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(name != null ? name : email);
        newUser.setPassword("oauth2-user-password");
        return userRepository.save(newUser);
    }
}