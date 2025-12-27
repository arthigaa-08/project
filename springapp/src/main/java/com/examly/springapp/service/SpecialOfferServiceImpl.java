package com.examly.springapp.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.SpecialOffer;
import com.examly.springapp.repository.SpecialOfferRepo;

@Service
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private final SpecialOfferRepo repo;

    public SpecialOfferServiceImpl(SpecialOfferRepo repo) {
        this.repo = repo;
    }

    @Override
    public SpecialOffer addOffer(SpecialOffer offer) {
        return repo.save(offer);
    }

    @Override
    public List<SpecialOffer> getAllOffers() {
        return repo.findAll();
    }
}
