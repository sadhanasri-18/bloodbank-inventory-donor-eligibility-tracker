package com.srieshwar.bloodbank.repository;

import com.srieshwar.bloodbank.entity.IssueRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, UUID> {
}
