package com.srieshwar.bloodbank.repository;

import com.srieshwar.bloodbank.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DonationRepository extends JpaRepository<Donation, UUID> {
}
