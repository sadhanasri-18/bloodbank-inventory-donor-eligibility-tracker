package com.srieshwar.bloodbank.controller;

import com.srieshwar.bloodbank.entity.BloodGroup;
import com.srieshwar.bloodbank.entity.BloodUnit;
import com.srieshwar.bloodbank.service.BloodUnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/blood-units")
public class BloodUnitController {

    private final BloodUnitService bloodUnitService;

    public BloodUnitController(BloodUnitService bloodUnitService) {
        this.bloodUnitService = bloodUnitService;
    }

    @PostMapping
    public ResponseEntity<BloodUnit> create(@RequestBody Map<String, String> request) {
        UUID donationId = UUID.fromString(request.get("donationId"));
        BloodGroup group = BloodGroup.valueOf(request.get("bloodGroup"));
        LocalDate expiryDate = LocalDate.parse(request.get("expiryDate"));

        return ResponseEntity.ok(bloodUnitService.create(donationId, group, expiryDate));
    }

    @GetMapping("/available/{bloodGroup}")
    public ResponseEntity<List<BloodUnit>> available(@PathVariable BloodGroup bloodGroup) {
        return ResponseEntity.ok(bloodUnitService.getAvailable(bloodGroup));
    }
}
