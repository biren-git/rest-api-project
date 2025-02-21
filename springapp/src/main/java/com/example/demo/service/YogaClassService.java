package com.example.demo.service;

import com.example.demo.entity.YogaClass;
import com.example.demo.repository.YogaClassRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import java.util.List;

@Service
public class YogaClassService {

    @Autowired
    private YogaClassRepository yogaClassRepository;

    public YogaClass createYogaClass(YogaClass yogaClass) {
        return yogaClassRepository.save(yogaClass);
    }

    @NonNull
    public Page<YogaClass> getAllYogaClasses(@NonNull Pageable pageable) {
        return yogaClassRepository.findAll(pageable);
    }

    public YogaClass getYogaClassById(Long id) {
        return yogaClassRepository.findById(id).orElse(null);
    }

    public List<YogaClass> getAllYogaClassesWithoutPagination() {
        return yogaClassRepository.findAll();
    }
    

    public YogaClass updateYogaClass(Long id, YogaClass updatedClass) {
        return yogaClassRepository.findById(id).map(yogaClass -> {
            yogaClass.setName(updatedClass.getName());
            yogaClass.setInstructor(updatedClass.getInstructor());
            yogaClass.setSchedule(updatedClass.getSchedule());
            return yogaClassRepository.save(yogaClass);
        }).orElse(null);
    }

    public void deleteYogaClass(Long id) {
    if (!yogaClassRepository.existsById(id)) {
        throw new EntityNotFoundException("YogaClass with ID " + id + " not found");  // ✅ Proper 404 Handling
    }
    yogaClassRepository.deleteById(id);
}

    public List<YogaClass> findByInstructor(String instructor) {
        return yogaClassRepository.findByInstructor(instructor);
    }
    public List<YogaClass> findByClassName(String name) {
        return yogaClassRepository.findByClassName(name);
    }
}
