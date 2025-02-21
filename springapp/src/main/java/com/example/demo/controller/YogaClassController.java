package com.example.demo.controller;

import com.example.demo.entity.YogaClass;
import com.example.demo.service.YogaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yoga-classes")
public class YogaClassController {

    @Autowired
    private YogaClassService yogaClassService;

    @PostMapping
    public YogaClass createYogaClass(@RequestBody YogaClass yogaClass) {
        return yogaClassService.createYogaClass(yogaClass);
    }

    @GetMapping("/{id}")
    public YogaClass getYogaClassById(@PathVariable Long id) {
        return yogaClassService.getYogaClassById(id);
    }

    @PutMapping("/{id}")
    public YogaClass updateYogaClass(@PathVariable Long id, @RequestBody YogaClass updatedClass) {
        return yogaClassService.updateYogaClass(id, updatedClass);
    }

    @DeleteMapping("/{id}")
    public void deleteYogaClass(@PathVariable Long id) {
        yogaClassService.deleteYogaClass(id);
    }

    @GetMapping
    public List<YogaClass> getAllYogaClasses() {
        return yogaClassService.getAllYogaClassesWithoutPagination();
    }

    
    @GetMapping("/paginate")
    public Page<YogaClass> getAllYogaClasses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, 
            sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        return yogaClassService.getAllYogaClasses(pageable);
    }

    
    @GetMapping("/search/instructor")
    public List<YogaClass> getByInstructor(@RequestParam String instructor) {
        return yogaClassService.findByInstructor(instructor);
    }

    @GetMapping("/search/name")
    public List<YogaClass> getByClassName(@RequestParam String name) {
        return yogaClassService.findByClassName(name);
    }
}
