
package com.examly.springapp.service;

import com.examly.springapp.model.RoomCategory;
import java.util.List;
import java.util.Optional;

public interface RoomCategoryService {
    RoomCategory addRoomCategory(RoomCategory category);
    List<RoomCategory> getAllRoomCategories();
    Optional<RoomCategory> getRoomCategoryById(Long id);
    RoomCategory updateRoomCategory(Long id, RoomCategory category);
}

