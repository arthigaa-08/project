package com.examly.springapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @PostMapping
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return service.addFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getAllFeedback() {
        return service.getAllFeedback();
    }
}
