
package com.examly.springapp.service;

import com.examly.springapp.model.Payment;
import com.examly.springapp.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo repo;

    @Override
    public Payment addPayment(Payment payment) {
        return repo.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> existing = repo.findById(id);
        if (existing.isPresent()) {
            Payment p = existing.get();
            p.setBooking(payment.getBooking());
            p.setAmount(payment.getAmount());
            return repo.save(p);
        }
        return null;
    }
}


