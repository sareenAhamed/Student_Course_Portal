package com.example.portal.enrollment_service.controller;

import com.example.portal.enrollment_service.model.Enrollment;
import com.example.portal.enrollment_service.repository.EnrollmentRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    private final EnrollmentRepository repo;
    private final RestTemplate restTemplate;

    public EnrollmentController(EnrollmentRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody Map<String, Long> body) {

        Long studentId = body.get("studentId");
        Long courseId = body.get("courseId");

        // Validate student exists
        try {
            restTemplate.getForObject(
                    "http://localhost:8081/students/" + studentId,
                    Object.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().body("Invalid studentId");
        }

        // Validate course exists
        try {
            restTemplate.getForObject(
                    "http://localhost:8082/courses/" + courseId,
                    Object.class
            );
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.badRequest().body("Invalid courseId");
        }

        // Save enrollment
        Enrollment enrollment = new Enrollment(
                studentId,
                courseId,
                LocalDate.now()
        );

        Enrollment saved = repo.save(enrollment);

        // Send notification
        Map<String, Long> notifyData = new HashMap<>();
        notifyData.put("studentId", studentId);
        notifyData.put("courseId", courseId);

        try {
            restTemplate.postForObject(
                    "http://localhost:8085/notify/enrollment",
                    notifyData,
                    String.class
            );
        } catch (Exception ex) {
            System.out.println("Notification service unreachable: " + ex.getMessage());
        }

        return ResponseEntity.ok(saved);
    }

    // Get enrollments by studentId
    @GetMapping("/student/{id}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }
}

