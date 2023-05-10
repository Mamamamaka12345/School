package com.example.kursach.Repositories;

import com.example.kursach.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursesRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
}
