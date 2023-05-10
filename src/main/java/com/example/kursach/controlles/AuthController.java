package com.example.kursach.controlles;

import com.example.kursach.entities.User;
import com.example.kursach.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthController {
    @Autowired
    private UserDetailsServiceImpl userService;

    // 4 вкладка - Регистрация/авторизация

    @GetMapping("/loginM")
    public String loginPage(Model model)
    {
        log.info("Authentication");
        model.addAttribute("activePage", "loginM");
        return "loginM";
    }
    @PostMapping("/loginM")
    public String loginUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("Login error excepted");
            return "loginM";
        }
        if (!userService.findUser(userForm.getUsername()))
        {
            model.addAttribute("userError", "Неверное имя пользователя или пароль");
            return "loginM";
        }
        return "redirect:/personalArea";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
