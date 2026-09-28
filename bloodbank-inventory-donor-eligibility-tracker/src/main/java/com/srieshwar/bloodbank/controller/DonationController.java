package com.srieshwar.bloodbank.controller;

import com.srieshwar.bloodbank.entity.Donation;
import com.srieshwar.bloodbank.service.DonationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public ResponseEntity<Donation> create(@RequestBody Map<String, String> request) {
        UUID donorId = UUID.fromString(request.get("donorId"));
        LocalDate collectionDate = LocalDate.parse(request.getOrDefault("collectionDate", LocalDate.now().toString()));
        return ResponseEntity.ok(donationService.processDonation(donorId, collectionDate));
    }
}
