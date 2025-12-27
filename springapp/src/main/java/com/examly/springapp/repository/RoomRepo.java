
package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Room;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {

    // Find rooms by roomNumber
    List<Room> findByRoomNumber(String roomNumber);

    // Find rooms by category name (nested property)
    List<Room> findByRoomCategory_CategoryName(String categoryName);
}


