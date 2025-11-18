package com.example.portal.notification_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @PostMapping("/enrollment")
    public ResponseEntity<String> notifyEnrollment(@RequestBody Map<String, Object> body) {

        Object studentId = body.get("studentId");
        Object courseId = body.get("courseId");

        String message = "Student " + studentId + " enrolled into Course " + courseId;

        // Print message (as required in the lab)
        System.out.println(message);

        // Return the same message to the caller
        return ResponseEntity.ok(message);
    }
}

