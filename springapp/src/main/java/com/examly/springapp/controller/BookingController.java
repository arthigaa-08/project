package com.examly.springapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @GetMapping("/{id}")
    public String getBookingById(@PathVariable Long id) {
        return "Booking " + id;
    }
}

