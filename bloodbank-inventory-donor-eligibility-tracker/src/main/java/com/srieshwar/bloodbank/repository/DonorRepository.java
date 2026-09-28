package com.srieshwar.bloodbank.repository;

import com.srieshwar.bloodbank.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
}
