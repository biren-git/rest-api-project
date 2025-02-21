package com.example.demo.repository;

import com.example.demo.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @NonNull
    Page<Course> findAll(@NonNull Pageable pageable);  

    @Query("SELECT c FROM Course c WHERE c.category = :category")
    List<Course> findByCategory(@Param("category") String category);
}
