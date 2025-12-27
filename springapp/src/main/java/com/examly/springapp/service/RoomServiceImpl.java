
package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.examly.springapp.model.Room;
import com.examly.springapp.repository.RoomRepo;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepo roomRepo;

    @Override
    public Room addRoom(Room room) {
        return roomRepo.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomRepo.findById(id).orElse(null);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        return roomRepo.findById(id).map(existing -> {
            existing.setRoomNumber(room.getRoomNumber());
            existing.setPricePerNight(room.getPricePerNight());
            existing.setAvailable(room.isAvailable());
            existing.setRoomCategory(room.getRoomCategory());
            return roomRepo.save(existing);
        }).orElse(null);
    }

    @Override
    public List<Room> getRoomsByCategoryName(String categoryName) {
        return roomRepo.findByRoomCategory_CategoryName(categoryName);
    }

    @Override
    public List<Room> getRoomsByNumber(String roomNumber) {
        return roomRepo.findByRoomNumber(roomNumber);
    }
}


