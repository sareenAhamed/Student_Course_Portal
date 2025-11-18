package com.example.portal.course_service.controller;


import com.example.portal.course_service.model.Course;
import com.example.portal.course_service.repository.CourseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    // GET all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    // GET course by ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    // POST create course
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return repo.save(course);
    }
}

