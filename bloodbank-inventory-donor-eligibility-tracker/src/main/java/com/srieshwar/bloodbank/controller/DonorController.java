package com.srieshwar.bloodbank.controller;

import com.srieshwar.bloodbank.entity.Donor;
import com.srieshwar.bloodbank.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<Donor> create(@RequestBody Donor donor) {
        return ResponseEntity.ok(donorService.create(donor));
    }

    @GetMapping("/{donorId}")
    public ResponseEntity<Donor> get(@PathVariable UUID donorId) {
        return ResponseEntity.ok(donorService.get(donorId));
    }

    @GetMapping("/{donorId}/eligibility")
    public ResponseEntity<Map<String, Object>> eligibility(@PathVariable UUID donorId) {
        boolean eligible = donorService.isEligible(donorId);
        return ResponseEntity.ok(Map.of(
                "donorId", donorId,
                "eligible", eligible,
                "rule", "Minimum 90 days gap since last donation"
        ));
    }
}
