package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String category;

    @OneToMany(mappedBy = "course")
    private Set<YogaClass> yogaClasses;

    @OneToMany(mappedBy = "course")
    private Set<InstructionalVideo> instructionalVideos;
}
