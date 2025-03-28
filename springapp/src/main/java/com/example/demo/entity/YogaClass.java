package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "yoga_classes")
public class YogaClass {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String instructor;
    private String schedule;

    @ManyToMany(mappedBy = "enrolledClasses")
    private Set<User> users;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
