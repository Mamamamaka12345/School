package com.example.kursach.services;

import com.example.kursach.Repositories.CoursesRepository;
import com.example.kursach.entities.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public void addCourse(int cost, String name, String teacher_name)
    {
        Course course = new Course();
        course.setCost(cost);
        course.setName(name);
        course.setTeacher_name(teacher_name);
        coursesRepository.save(course);
        log.info("Курс " + name + " был добавлен");
    };
    @Override
    public boolean deleteCourse(String name) {
        if (coursesRepository.findByName(name).size() == 0)
            return false;
        coursesRepository.deleteAll(coursesRepository.findByName(name));
        log.info("Курс " + name + " удален");
        return true;
    }
    @Override
    public List<Course> getCourses() {
        log.info("Get all");
        return coursesRepository.findAll();
    }

    @Override
    public List<Course> getByName(String name) {
        log.info("Get all by name " + name);
        return coursesRepository.findByName(name);
    }
}
