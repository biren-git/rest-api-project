package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;

    @ManyToMany
    @JoinTable(
        name = "user_yoga_classes",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "yoga_class_id")
    )
    private Set<YogaClass> enrolledClasses;

    @ManyToMany
    @JoinTable(
        name = "user_videos",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "video_id")
    )
    private Set<InstructionalVideo> instructionalVideos;
}
