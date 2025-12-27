
package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Room;
import com.examly.springapp.repository.RoomRepo;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepo roomRepo;

    public RoomController(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room saved = roomRepo.save(room);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomRepo.findById(id)
                .map(existing -> {
                    existing.setRoomNumber(room.getRoomNumber());
                    existing.setPricePerNight(room.getPricePerNight());
                    existing.setAvailable(room.isAvailable());
                    existing.setRoomCategory(room.getRoomCategory());
                    return ResponseEntity.ok(roomRepo.save(existing));
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

   

    @GetMapping("/category/{name}")
    public ResponseEntity<List<Room>> getRoomsByCategoryName(@PathVariable String name) {
        List<Room> rooms = roomRepo.findByRoomCategory_CategoryName(name);
        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<?> getRoomByNumber(@PathVariable String roomNumber) {
        List<Room> rooms = roomRepo.findByRoomNumber(roomNumber);
        if (rooms.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No room found with number: " + roomNumber);
        }
        return ResponseEntity.ok(rooms);
    }
}



