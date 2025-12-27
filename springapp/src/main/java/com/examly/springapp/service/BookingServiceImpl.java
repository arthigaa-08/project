
package com.examly.springapp.service;

import com.examly.springapp.model.Booking;
import com.examly.springapp.repository.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo repo;

    @Override
    public Booking addBooking(Booking booking) {
        return repo.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        Optional<Booking> existing = repo.findById(id);
        if (existing.isPresent()) {
            Booking b = existing.get();
            b.setRoom(booking.getRoom());
            b.setGuest(booking.getGuest());
            b.setCheckInDate(booking.getCheckInDate());
            b.setCheckOutDate(booking.getCheckOutDate());
            return repo.save(b);
        }
        return null;
    }
}

