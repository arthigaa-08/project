
package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Room;

public interface RoomService {

    Room addRoom(Room room);

    List<Room> getAllRooms();

    Room getRoomById(Long id);

    Room updateRoom(Long id, Room room);

    // For Day12: Get rooms by category name
    List<Room> getRoomsByCategoryName(String categoryName);

    // For Day12: Get rooms by room number
    List<Room> getRoomsByNumber(String roomNumber);
}


