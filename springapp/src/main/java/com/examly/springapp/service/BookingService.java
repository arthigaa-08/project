
package com.examly.springapp.service;

import com.examly.springapp.model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking addBooking(Booking booking);
    List<Booking> getAllBookings();
    Optional<Booking> getBookingById(Long id);
    Booking updateBooking(Long id, Booking booking);
}

