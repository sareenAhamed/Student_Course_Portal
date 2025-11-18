package com.example.portal.result_service.controller;


import com.example.portal.result_service.model.Result;
import com.example.portal.result_service.repository.ResultRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/results")
public class ResultController {

    private final ResultRepository repo;

    public ResultController(ResultRepository repo) {
        this.repo = repo;
    }

    // POST create result
    @PostMapping
    public Result createResult(@RequestBody Map<String, Object> body) {

        Long studentId = Long.valueOf(body.get("studentId").toString());
        Long courseId = Long.valueOf(body.get("courseId").toString());
        String grade = body.get("grade").toString();

        Result result = new Result(studentId, courseId, grade);

        return repo.save(result);
    }

    // GET results by studentId
    @GetMapping("/student/{id}")
    public List<Result> getResultsByStudent(@PathVariable Long id) {
        return repo.findByStudentId(id);
    }
}
