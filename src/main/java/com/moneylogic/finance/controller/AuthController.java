package com.moneylogic.finance.controller;

import com.moneylogic.finance.model.User;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Страница логина
    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "logout", required = false) String logout, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
        }
        if (logout != null) {
            model.addAttribute("message", "You have successfully logged out.");
        }
        return "login";
    }

    // Страница регистрации
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Обработка формы регистрации
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        User user = User.createUserWithCurrentDate(username, email, password);
        userService.saveUser(user);
        return "redirect:/login?register=true";
    }

    // Проверка, вошел ли пользователь в систему
    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Проверяем, является ли аутентифицированный пользователь OAuth2User
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                String name = oauth2User.getAttribute("name");
                model.addAttribute("user", name);
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                model.addAttribute("user", username);
            }
            return "profile";
        }
        return "redirect:/login";
    }

}
