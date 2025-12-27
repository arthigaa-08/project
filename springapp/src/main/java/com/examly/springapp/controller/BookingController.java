package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Booking;
import com.examly.springapp.model.Guest;
import com.examly.springapp.model.Room;
import com.examly.springapp.repository.BookingRepo;
import com.examly.springapp.repository.GuestRepo;
import com.examly.springapp.repository.RoomRepo;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingRepo bookingRepo;
    private final GuestRepo guestRepo;
    private final RoomRepo roomRepo;

    // Constructor-based injection for all three repositories
    public BookingController(BookingRepo bookingRepo, GuestRepo guestRepo, RoomRepo roomRepo) {
        this.bookingRepo = bookingRepo;
        this.guestRepo = guestRepo;
        this.roomRepo = roomRepo;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {

        Guest guest = guestRepo.findById(booking.getGuest().getGuestId())
                .orElseThrow(() -> new RuntimeException("Guest not found with ID " + booking.getGuest().getGuestId()));

        Room room = roomRepo.findById(booking.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with ID " + booking.getRoom().getRoomId()));

        booking.setGuest(guest);
        booking.setRoom(room);

        Booking saved = bookingRepo.save(booking);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepo.findAll();
        if (bookings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingRepo.findById(id).map(existing -> {

            if (booking.getGuest() != null && booking.getGuest().getGuestId() != null) {
                Guest guest = guestRepo.findById(booking.getGuest().getGuestId())
                        .orElseThrow(() -> new RuntimeException("Guest not found with ID " + booking.getGuest().getGuestId()));
                existing.setGuest(guest);
            }

            if (booking.getRoom() != null && booking.getRoom().getRoomId() != null) {
                Room room = roomRepo.findById(booking.getRoom().getRoomId())
                        .orElseThrow(() -> new RuntimeException("Room not found with ID " + booking.getRoom().getRoomId()));
                existing.setRoom(room);
            }

            existing.setCheckInDate(booking.getCheckInDate());
            existing.setCheckOutDate(booking.getCheckOutDate());

            Booking updated = bookingRepo.save(existing);
            return ResponseEntity.ok(updated);

        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        if (!bookingRepo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookingRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
