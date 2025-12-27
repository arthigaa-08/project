package com.examly.springapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.repository.FeedbackRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo repo;

    public FeedbackServiceImpl(FeedbackRepo repo) {
        this.repo = repo;
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return repo.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return repo.findAll();
    }
}
