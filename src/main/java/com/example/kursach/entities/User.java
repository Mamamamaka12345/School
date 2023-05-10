package com.example.kursach.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String username;
    private String password;
    private String role;
    private boolean enabled;
    @Transient
    private String passwordConfirm;

    @ManyToMany(mappedBy = "userSet")
    Set<Course> courseSet = new HashSet<>();

    public void addCourse(Course course)
    {
        this.courseSet.add(course);
    }
    public void delCourse(Course course)
    {
        this.courseSet.remove(course);
    }
}
