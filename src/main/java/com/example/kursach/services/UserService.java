package com.example.kursach.services;

import com.example.kursach.entities.Course;

public interface UserService {
    boolean addCourse(Course course);
    boolean delCourse(String name);
}
