package com.moneylogic.finance.controller;

import com.moneylogic.finance.logging.LoggerSingleton;
import com.moneylogic.finance.model.MyUserDetails;
import com.moneylogic.finance.model.User;
import com.moneylogic.finance.service.UserServiceImpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "error", required = false) String error) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Проверяем, является ли аутентифицированный пользователь OAuth2User
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
                String name = oauth2User.getAttribute("name");
                String email = oauth2User.getAttribute("email");
                model.addAttribute("email", email);
                model.addAttribute("user", name);
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                // Предположим, что вы можете получить email через метод getEmail()
                String email = ((MyUserDetails) userDetails).getEmail(); // если у вас кастомная реализация UserDetails
                model.addAttribute("user", username);
                model.addAttribute("email", email);
            }
        }
        if (error != null) {
            model.addAttribute("error", "Invalid credentials");
        }
        if (logout != null) {
            model.addAttribute("message", "You have successfully logged out.");
        }
        return "index"; // Вернуть index.html из папки templates
    }

    // Обработка формы регистрации
    @PostMapping("/user/register")
    public String register(@RequestParam("loginRegister") String username, @RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        try{

            User user = User.createUserWithCurrentDate(username, email, password);
            String comment = userService.saveUser(user);
            LoggerSingleton.info(AuthController.class, comment);
            return "redirect:/?register=true";
        }catch (Exception e){
            LoggerSingleton.error(AuthController.class, e.getMessage());
        }
        return "redirect:/?register=false";
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
                String email = oauth2User.getAttribute("email");
                model.addAttribute("email", email);
                model.addAttribute("user", name);
            } else if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String username = userDetails.getUsername();
                // Предположим, что вы можете получить email через метод getEmail()
                String email = ((MyUserDetails) userDetails).getEmail(); // если у вас кастомная реализация UserDetails
                model.addAttribute("user", username);
                model.addAttribute("email", email);
            }
            return "main-page";
        }
        return "redirect:/";
    }
}
