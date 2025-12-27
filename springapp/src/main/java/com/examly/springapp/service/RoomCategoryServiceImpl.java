
package com.examly.springapp.service;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {

    @Autowired
    private RoomCategoryRepo repo;

    @Override
    public RoomCategory addRoomCategory(RoomCategory category) {
        return repo.save(category);
    }

    @Override
    public List<RoomCategory> getAllRoomCategories() {
        return repo.findAll();
    }

    @Override
    public Optional<RoomCategory> getRoomCategoryById(Long id) {
        return repo.findById(id);
    }

    @Override
    public RoomCategory updateRoomCategory(Long id, RoomCategory category) {
        Optional<RoomCategory> existing = repo.findById(id);
        if (existing.isPresent()) {
            RoomCategory cat = existing.get();
            cat.setCategoryName(category.getCategoryName());
            return repo.save(cat);
        }
        return null;
    }
}

