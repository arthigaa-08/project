
package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Guest;
import com.examly.springapp.repository.GuestRepo;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    @Autowired
    private GuestRepo guestRepo;

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        if (guest.getName() == null || guest.getPhone() == null || guest.getEmail() == null) {
            return ResponseEntity.badRequest().build();
        }
        Guest savedGuest = guestRepo.save(guest);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        List<Guest> guests = guestRepo.findAll();
        if (guests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Long id) {
        return guestRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(@PathVariable Long id, @RequestBody Guest guest) {
        return guestRepo.findById(id)
                .map(existing -> {
                    existing.setName(guest.getName());
                    existing.setPhone(guest.getPhone());
                    existing.setEmail(guest.getEmail());
                    Guest updated = guestRepo.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Guest>> getGuestByEmail(@PathVariable String email) {
        List<Guest> guests = guestRepo.findByEmail(email);
        if (guests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(guests);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getGuestByPhone(@PathVariable String phone) {
        List<Guest> guests = guestRepo.findByPhone(phone);
        if (guests.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("No guest found with phone: " + phone);
        }
        return ResponseEntity.ok(guests);
    }
}




