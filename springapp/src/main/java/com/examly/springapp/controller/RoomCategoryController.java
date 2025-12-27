package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomCategoryRepo;

@RestController
@RequestMapping("/api/room-categories")
public class RoomCategoryController {

private final RoomCategoryRepo roomCategoryRepo;
public RoomCategoryController(RoomCategoryRepo roomCategoryRepo) {
this.roomCategoryRepo = roomCategoryRepo;
 }

 @PostMapping
public ResponseEntity<?> addCategory(@RequestBody(required = false) RoomCategory category) {
if (category == null) {
return ResponseEntity.badRequest().build();
 }
RoomCategory saved = roomCategoryRepo.save(category);
return new ResponseEntity<>(saved, HttpStatus.CREATED);
 }
@GetMapping
public ResponseEntity<?> getAllCategories() {
 List<RoomCategory> list = roomCategoryRepo.findAll();
if (list.isEmpty()) {
    return ResponseEntity.noContent().build();
}
return ResponseEntity.ok(list);
}
@GetMapping("/{id}")
 public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
    Optional<RoomCategory> category = roomCategoryRepo.findById(id);
if (category.isPresent()) {
return ResponseEntity.ok(category.get());
}
return ResponseEntity.status(HttpStatus.NOT_FOUND)
.body("Room category not found");
}
@PutMapping("/{id}")
                public ResponseEntity<?> updateCategory(@PathVariable Long id,
                                            @RequestBody RoomCategory category) {
        Optional<RoomCategory> existing = roomCategoryRepo.findById(id);
        if (existing.isPresent()) {
            RoomCategory rc = existing.get();
            rc.setCategoryName(category.getCategoryName());
            return ResponseEntity.ok(roomCategoryRepo.save(rc));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Room category not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        roomCategoryRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page/{page}/{size}")
    public Page<RoomCategory> paginate(@PathVariable int page,
                                       @PathVariable int size) {
        return roomCategoryRepo.findAll(
                PageRequest.of(page, size, Sort.by("categoryId"))
        );
    }
}                                                                                                                                                                                                   