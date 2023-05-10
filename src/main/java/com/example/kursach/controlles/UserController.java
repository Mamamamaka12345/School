package com.example.kursach.controlles;

import com.example.kursach.Repositories.UserRepository;
import com.example.kursach.entities.Course;
import com.example.kursach.entities.User;
import com.example.kursach.services.CoursesServiceImpl;
import com.example.kursach.services.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@Slf4j
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CoursesServiceImpl coursesService;
    @Autowired
    UserRepository userRepository;
    // Действия пользователя
    @PostMapping("/subjects/userCourse")
    public String searchCourse(@RequestParam(value = "name") String name, Model model) {
        List<Course> courses = coursesService.getByName(name);
        if (courses.size() == 0)
        {
            model.addAttribute("courses", coursesService.getCourses());
            model.addAttribute("activePage", "subjects");
            model.addAttribute("courseError", "Такого курса нет");
            return "redirect:/subjects";
        }
        if (!userService.addCourse(courses.get(0)))
        {
            return "redirect:/loginM";
        }
        return "redirect:/personalArea";
    }

    @PostMapping("/personalArea/delCourse")
    public String userDelCourse(@RequestParam(value = "name") String name, Model model) {
        if (!userService.delCourse(name))
            return "redirect:/perosnalArea";
        model.addAttribute("activePage", "personalArea");
        return "redirect:/personalArea";
    }
}
