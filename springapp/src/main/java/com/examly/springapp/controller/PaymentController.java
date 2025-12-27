package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Payment;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.repository.PaymentRepo;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepo paymentRepo;
    @Autowired
private BookingRepo bookingRepo; // ✅ correct


    public PaymentController(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    @PostMapping
public Payment createPayment(@RequestBody Payment payment) {
    if (payment.getBooking() == null || payment.getBooking().getBookingId() == null) {
        throw new RuntimeException("Booking ID is required for creating a payment.");
    }

    Long bookingId = payment.getBooking().getBookingId();

    Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found with ID " + bookingId));

    payment.setBooking(booking);

    return paymentRepo.save(payment);
}


    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepo.findAll();
        if (payments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return paymentRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {

        if (!paymentRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        paymentRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
