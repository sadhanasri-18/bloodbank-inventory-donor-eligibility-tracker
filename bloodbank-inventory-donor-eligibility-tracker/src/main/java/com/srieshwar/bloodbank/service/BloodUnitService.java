package com.srieshwar.bloodbank.service;

import com.srieshwar.bloodbank.entity.BloodGroup;
import com.srieshwar.bloodbank.entity.BloodUnit;
import com.srieshwar.bloodbank.entity.Donation;
import com.srieshwar.bloodbank.repository.BloodUnitRepository;
import com.srieshwar.bloodbank.repository.DonationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BloodUnitService {

    private final BloodUnitRepository bloodUnitRepository;
    private final DonationRepository donationRepository;

    public BloodUnitService(BloodUnitRepository bloodUnitRepository, DonationRepository donationRepository) {
        this.bloodUnitRepository = bloodUnitRepository;
        this.donationRepository = donationRepository;
    }

    public BloodUnit create(UUID donationId, BloodGroup bloodGroup, LocalDate expiryDate) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found: " + donationId));

        if (expiryDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiry date must be today or later.");
        }

        BloodUnit unit = new BloodUnit();
        unit.setDonation(donation);
        unit.setBloodGroup(bloodGroup);
        unit.setExpiryDate(expiryDate);
        unit.setStatus("AVAILABLE");

        return bloodUnitRepository.save(unit);
    }

    public List<BloodUnit> getAvailable(BloodGroup bloodGroup) {
        return bloodUnitRepository.findByBloodGroupAndStatus(bloodGroup, "AVAILABLE")
                .stream()
                .filter(unit -> !unit.isExpired())
                .toList();
    }
}
