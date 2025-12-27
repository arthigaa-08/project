
package com.examly.springapp.service;

import com.examly.springapp.model.Guest;
import com.examly.springapp.repository.GuestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepo repo;

    @Override
    public Guest addGuest(Guest guest) {
        return repo.save(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return repo.findAll();
    }

    @Override
    public Optional<Guest> getGuestById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Optional<Guest> existing = repo.findById(id);
        if (existing.isPresent()) {
            Guest g = existing.get();
            g.setName(guest.getName());
            g.setPhone(guest.getPhone());
            g.setEmail(guest.getEmail());
            return repo.save(g);
        }
        return null;
    }

    @Override
    public List<Guest> getGuestByPhone(String phone) {
        return repo.findByPhone(phone);
    }

    @Override
    public List<Guest> getGuestByEmail(String email) {
        return repo.findByEmail(email);
    }
}

