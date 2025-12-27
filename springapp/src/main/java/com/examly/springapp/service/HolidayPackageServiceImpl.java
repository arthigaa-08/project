package com.examly.springapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.HolidayPackage;
import com.examly.springapp.repository.HolidayPackageRepo;

@Service
public class HolidayPackageServiceImpl implements HolidayPackageService {

    private final HolidayPackageRepo repo;

    public HolidayPackageServiceImpl(HolidayPackageRepo repo) {
        this.repo = repo;
    }

    @Override
    public HolidayPackage addPackage(HolidayPackage holidayPackage) {
        return repo.save(holidayPackage);
    }

    @Override
    public List<HolidayPackage> getAllPackages() {
        return repo.findAll();
    }
}
