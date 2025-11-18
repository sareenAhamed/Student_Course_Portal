package com.example.portal.student_service.controller;

import com.example.portal.student_service.model.Student;
import com.example.portal.student_service.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // GET student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // POST create student
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repo.save(student);
    }
}
