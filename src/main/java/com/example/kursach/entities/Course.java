package com.example.kursach.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "courses")
@Getter
@Setter
public class Course {
    @Id
    @SequenceGenerator(name = "courses_seq", sequenceName = "courses_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "courses_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private int cost;
    private String name;
    private String teacher_name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Set<User> userSet = new HashSet<>();

    public void addUser(User user)
    {
        this.userSet.add(user);
    }
    public void delUser(User user)
    {
        this.userSet.remove(user);
    }
}
