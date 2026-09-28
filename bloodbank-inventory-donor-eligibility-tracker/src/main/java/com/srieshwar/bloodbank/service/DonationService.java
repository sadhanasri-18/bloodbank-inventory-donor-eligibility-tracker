package com.srieshwar.bloodbank.service;

import com.srieshwar.bloodbank.entity.Donation;
import com.srieshwar.bloodbank.entity.Donor;
import com.srieshwar.bloodbank.repository.DonationRepository;
import com.srieshwar.bloodbank.repository.DonorRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final DonorRepository donorRepository;

    public DonationService(DonationRepository donationRepository, DonorRepository donorRepository) {
        this.donationRepository = donationRepository;
        this.donorRepository = donorRepository;
    }

    public Donation processDonation(UUID donorId, LocalDate collectionDate) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found: " + donorId));

        if (!donor.isEligibleToday()) {
            throw new IllegalStateException("Donor is not eligible. Minimum 90 days gap is required.");
        }

        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setCollectionDate(collectionDate);

        donor.setLastDonationDate(collectionDate);
        donorRepository.save(donor);

        return donationRepository.save(donation);
    }
}
