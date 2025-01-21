package com.moneylogic.finance.controller;

import com.moneylogic.finance.model.User;
import com.moneylogic.finance.service.MyUserDetailsService;
import com.moneylogic.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Страница логина
    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
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
        if (userService.findByUsername(username).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        if (userService.findByEmail(email).isPresent()) {
            model.addAttribute("error", "Email already registered");
            return "register";
        }

        User user = User.createUserWithCurrentDate(username, email, passwordEncoder.encode(password));
        userService.saveUser(user);
        return "redirect:/login?register=true";
    }

    // Метод для выхода (если нужно вручную управлять выходом)
    @GetMapping("/logout")
    public String logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/login?logout=true";
    }

    // Проверка, вошел ли пользователь в систему
    @GetMapping("/profile")
    public String userProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("user", authentication.getName());
            return "profile";
        }
        return "redirect:/login";
    }
}
