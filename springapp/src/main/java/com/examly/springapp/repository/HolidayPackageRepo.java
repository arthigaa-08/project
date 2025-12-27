package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.HolidayPackage;

public interface HolidayPackageRepo extends JpaRepository<HolidayPackage, Long> {
}
