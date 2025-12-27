package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.SpecialOffer;

public interface SpecialOfferService {
    SpecialOffer addOffer(SpecialOffer offer);
    List<SpecialOffer> getAllOffers();
}
