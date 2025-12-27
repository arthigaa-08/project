package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Room;
import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomRepo;
import com.examly.springapp.repository.RoomCategoryRepo;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepo roomRepo;
    private final RoomCategoryRepo roomCategoryRepo;

    // Constructor Injection
    public RoomController(RoomRepo roomRepo, RoomCategoryRepo roomCategoryRepo) {
        this.roomRepo = roomRepo;
        this.roomCategoryRepo = roomCategoryRepo;
    }

    // ===================== POST : Add Room =====================
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {

        // Validate category presence
        if (room.getRoomCategory() == null || room.getRoomCategory().getCategoryId() == null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        Long categoryId = room.getRoomCategory().getCategoryId();

        // Fetch full RoomCategory from DB
        RoomCategory category = roomCategoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("RoomCategory not found"));

        // Attach managed entity
        room.setRoomCategory(category);

        Room saved = roomRepo.save(room);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ===================== GET : All Rooms =====================
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomRepo.findAll();
        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    // ===================== GET : Room By ID =====================
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return roomRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ===================== PUT : Update Room =====================
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {

        return roomRepo.findById(id).map(existing -> {

            existing.setRoomNumber(room.getRoomNumber());
            existing.setPricePerNight(room.getPricePerNight());
            existing.setAvailable(room.isAvailable());

            if (room.getRoomCategory() != null && room.getRoomCategory().getCategoryId() != null) {
                RoomCategory category = roomCategoryRepo
                        .findById(room.getRoomCategory().getCategoryId())
                        .orElseThrow(() -> new RuntimeException("RoomCategory not found"));
                existing.setRoomCategory(category);
            }

            Room updated = roomRepo.save(existing);
            return ResponseEntity.ok(updated);

        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // ===================== GET : Rooms By Category Name =====================
    @GetMapping("/category/{name}")
    public ResponseEntity<List<Room>> getRoomsByCategoryName(@PathVariable String name) {
        List<Room> rooms = roomRepo.findByRoomCategory_CategoryName(name);
        if (rooms.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(rooms);
    }

    // ===================== GET : Room By Room Number =====================
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
