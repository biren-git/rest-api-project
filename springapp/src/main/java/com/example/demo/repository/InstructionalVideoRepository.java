package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.InstructionalVideo;
import org.springframework.lang.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InstructionalVideoRepository extends JpaRepository<InstructionalVideo, Long> {

    @NonNull
    Page<InstructionalVideo> findAll(@NonNull Pageable pageable);

   
    @Query("SELECT v FROM InstructionalVideo v WHERE LOWER(v.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<InstructionalVideo> findByTitle(@Param("title") String title);

    @Query("SELECT v FROM InstructionalVideo v WHERE LOWER(v.description) LIKE LOWER(CONCAT('%', :description, '%'))")
    List<InstructionalVideo> findByDescription(@Param("description") String description);
}
