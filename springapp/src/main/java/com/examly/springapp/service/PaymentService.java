
package com.examly.springapp.service;

import com.examly.springapp.model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment addPayment(Payment payment);
    List<Payment> getAllPayments();
    Optional<Payment> getPaymentById(Long id);
    Payment updatePayment(Long id, Payment payment);
}

