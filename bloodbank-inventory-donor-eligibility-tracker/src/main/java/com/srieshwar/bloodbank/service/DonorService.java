package com.srieshwar.bloodbank.service;

import com.srieshwar.bloodbank.entity.Donor;
import com.srieshwar.bloodbank.repository.DonorRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class DonorService {

    private final DonorRepository donorRepository;

    public DonorService(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    public Donor create(Donor donor) {
        return donorRepository.save(donor);
    }

    public Donor get(UUID id) {
        return donorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donor not found: " + id));
    }

    public boolean isEligible(UUID donorId) {
        return get(donorId).isEligibleToday();
    }
}
