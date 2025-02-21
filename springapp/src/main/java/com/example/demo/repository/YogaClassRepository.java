package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.YogaClass;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface YogaClassRepository extends JpaRepository<YogaClass, Long> {

    @NonNull 
    Page<YogaClass> findAll(@NonNull Pageable pageable);

    
    @Query("SELECT y FROM YogaClass y WHERE LOWER(y.instructor) LIKE LOWER(CONCAT('%', :instructor, '%'))")
    List<YogaClass> findByInstructor(@Param("instructor") String instructor);

    @Query("SELECT y FROM YogaClass y WHERE LOWER(y.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<YogaClass> findByClassName(@Param("name") String name);
}
