package com.example.kursach.controlles;

import com.example.kursach.Repositories.UserRepository;
import com.example.kursach.entities.Course;
import com.example.kursach.entities.User;
import com.example.kursach.services.CoursesServiceImpl;
import com.example.kursach.services.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MainController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CoursesServiceImpl coursesService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;


    // 1 вкладка - Главная

    @GetMapping("/")
    public String mainPage(Model model) {
        return "index";
    }

    // 2 вкладка - Курсы

    @GetMapping("/subjects")
    public String coursesPage(Model model) {
        model.addAttribute("courses", coursesService.getCourses());
        model.addAttribute("activePage", "subjects");
        return "subjects";
    }

    // 3 вкладка - Личный кабинет

    @GetMapping("/personalArea")
    public String personalAreaPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("courses", userRepository.findByUsername(authentication.getName()).getCourseSet());
        model.addAttribute("activePage", "personalArea");
        return "personalArea";
    }

    // 5 вкладка - О нас
    @GetMapping("/info")
    public String getInfo(Model model) {
        model.addAttribute("activePage", "info");
        return "info";
    }
}
