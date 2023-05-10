package com.example.kursach;

import com.example.kursach.Repositories.CoursesRepository;
import com.example.kursach.Repositories.UserRepository;
import com.example.kursach.entities.Course;
import com.example.kursach.entities.User;
import com.example.kursach.services.CoursesService;
import com.example.kursach.services.CoursesServiceImpl;
import com.example.kursach.services.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ServicesTest {
    @MockBean
    UserRepository userRepository;
    @MockBean
    CoursesRepository coursesRepository;

    @InjectMocks
    CoursesServiceImpl coursesService;

    @Test
    public void getCourses()
    {
        Course course = new Course();
        course.setName("Name");
        Course course1 = new Course();
        coursesRepository.save(course);
        coursesRepository.save(course1);
        Mockito.when(coursesRepository.findAll()).thenReturn(List.of(course, course1));

        Assertions.assertEquals(2, coursesService.getCourses().size());
        Assertions.assertEquals("Name", coursesService.getCourses().get(0).getName());
    }
    @Test
    public void removeCourseTest() {
        String name = "Name";
        boolean result = coursesService.deleteCourse(name);
        Assertions.assertFalse(result);
    }
}
