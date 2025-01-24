package com.moneylogic.finance.service;

import com.moneylogic.finance.model.User;
import com.moneylogic.finance.repository.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        // Получение информации о пользователе из Google
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        // Проверка, есть ли пользователь в базе данных
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            // Сохраняем нового пользователя в базу данных
            User newUser = User.createUserWithCurrentDate(name, email, "");
            userRepository.save(newUser);
        }

        return oauth2User;
    }
}
