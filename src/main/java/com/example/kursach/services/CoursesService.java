package com.example.kursach.services;

import com.example.kursach.entities.Course;

import java.util.List;

public interface CoursesService {
    // Добавить курс
    void addCourse(int cost, String name, String teacher_name);
    // Удалить курс
    boolean deleteCourse(String name);
    // Найти курс (*) -> добавить фильтрацию?

    // Получить все курсы
    List<Course> getCourses();
    // все курсы по имени
    List<Course> getByName(String name);
}
