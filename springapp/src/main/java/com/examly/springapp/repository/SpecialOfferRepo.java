package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.SpecialOffer;

public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Long> {
}
