package com.example.kursach.controlles;

import com.example.kursach.services.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    // Панель администратора
    @Autowired
    CoursesServiceImpl coursesService;

    // Добавление очередного курса
    @PostMapping(value = "/subjects/addCourse")
    public @ResponseBody String addCourse(int cost, String name, String teacher_name)
    {
        coursesService.addCourse(cost, name, teacher_name);
        return "status: ok";
    }
    @PostMapping(value = "/subjects/delCourse")
    public @ResponseBody String addCourse(String name)
    {
        coursesService.deleteCourse(name);
        return "status: ok";
    }
}
