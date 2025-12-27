package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.HolidayPackage;

public interface HolidayPackageService {
    HolidayPackage addPackage(HolidayPackage holidayPackage);
    List<HolidayPackage> getAllPackages();
}
