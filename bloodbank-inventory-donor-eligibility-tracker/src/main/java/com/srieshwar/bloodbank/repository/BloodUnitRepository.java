package com.srieshwar.bloodbank.repository;

import com.srieshwar.bloodbank.entity.BloodGroup;
import com.srieshwar.bloodbank.entity.BloodUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BloodUnitRepository extends JpaRepository<BloodUnit, UUID> {
    List<BloodUnit> findByBloodGroupAndStatus(BloodGroup bloodGroup, String status);
}
