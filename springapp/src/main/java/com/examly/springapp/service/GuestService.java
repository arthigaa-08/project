
package com.examly.springapp.service;

import com.examly.springapp.model.Guest;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    Guest addGuest(Guest guest);
    List<Guest> getAllGuests();
    Optional<Guest> getGuestById(Long id);
    Guest updateGuest(Long id, Guest guest);
    List<Guest> getGuestByPhone(String phone);
    List<Guest> getGuestByEmail(String email);
}

