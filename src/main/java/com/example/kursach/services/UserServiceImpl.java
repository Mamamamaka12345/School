package com.example.kursach.services;

import com.example.kursach.Repositories.CoursesRepository;
import com.example.kursach.Repositories.UserRepository;
import com.example.kursach.entities.Course;
import com.example.kursach.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public boolean addCourse(Course course) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =  userRepository.findByUsername(auth.getName());
        if (user == null)
            return false;
        log.info("User add course "  + course.getName());
        user.addCourse(course);
        course.addUser(user);
        userRepository.flush();
        coursesRepository.flush();
        return true;
    }
    public boolean delCourse(String name)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user =  userRepository.findByUsername(auth.getName());
        Course course = coursesRepository.findByName(name).get(0);
        if (user == null || user.getCourseSet().size() == 0)
            return false;
        user.delCourse(course);
        course.delUser(user);
        userRepository.flush();
        coursesRepository.flush();
        return true;
    }
}
