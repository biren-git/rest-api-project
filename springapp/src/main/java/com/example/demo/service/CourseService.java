package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> getAllCoursesWithoutPagination() {
        return courseRepository.findAll();
    }

    @NonNull
    public Page<Course> getAllCourses(@NonNull Pageable pageable) {
        return courseRepository.findAll(pageable);
    }


    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Course with ID " + id + " not found"));
    }


    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setCategory(updatedCourse.getCategory());
            return courseRepository.save(course);
        }).orElseThrow(() ->
                new EntityNotFoundException("Course with ID " + id + " not found"));
    }

   
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new EntityNotFoundException("Course with ID " + id + " not found");
        }
        courseRepository.deleteById(id);
    }


    public List<Course> findCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }
}
