package com.examly.springapp.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.SpecialOffer;
import com.examly.springapp.service.SpecialOfferService;

@RestController
@RequestMapping("/specialoffers")
public class SpecialOfferController {

    private final SpecialOfferService service;

    public SpecialOfferController(SpecialOfferService service) {
        this.service = service;
    }

    @PostMapping
    public SpecialOffer addOffer(@RequestBody SpecialOffer offer) {
        return service.addOffer(offer);
    }

    @GetMapping
    public List<SpecialOffer> getAllOffers() {
        return service.getAllOffers();
    }
}
