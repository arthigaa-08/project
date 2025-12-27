package com.examly.springapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.HolidayPackage;
import com.examly.springapp.service.HolidayPackageService;

@RestController
@RequestMapping("/holidaypackages")
public class HolidayPackageController {

    private final HolidayPackageService service;

    public HolidayPackageController(HolidayPackageService service) {
        this.service = service;
    }

    @PostMapping
    public HolidayPackage addPackage(@RequestBody HolidayPackage holidayPackage) {
        return service.addPackage(holidayPackage);
    }

    @GetMapping
    public List<HolidayPackage> getAllPackages() {
        return service.getAllPackages();
    }
}
