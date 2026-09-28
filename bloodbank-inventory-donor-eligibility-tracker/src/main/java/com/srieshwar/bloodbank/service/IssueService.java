package com.srieshwar.bloodbank.service;

import com.srieshwar.bloodbank.entity.*;
import com.srieshwar.bloodbank.repository.BloodUnitRepository;
import com.srieshwar.bloodbank.repository.IssueRecordRepository;
import com.srieshwar.bloodbank.repository.IssueUnitMapRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class IssueService {

    private final IssueRecordRepository issueRecordRepository;
    private final BloodUnitRepository bloodUnitRepository;
    private final IssueUnitMapRepository issueUnitMapRepository;

    public IssueService(IssueRecordRepository issueRecordRepository,
                        BloodUnitRepository bloodUnitRepository,
                        IssueUnitMapRepository issueUnitMapRepository) {
        this.issueRecordRepository = issueRecordRepository;
        this.bloodUnitRepository = bloodUnitRepository;
        this.issueUnitMapRepository = issueUnitMapRepository;
    }

    public IssueRecord issueBlood(UUID unitId, BloodGroup requestedGroup,
                                  String recipientDetails, LocalDate requestDate) {

        BloodUnit unit = bloodUnitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Blood unit not found: " + unitId));

        if (unit.isExpired()) {
            throw new IllegalStateException("Cannot issue an expired blood unit.");
        }
        System.out.println("DEBUG STATUS = [" + unit.getStatus() + "]");
        if (!"AVAILABLE".equalsIgnoreCase(unit.getStatus())) {

        }

        if (unit.getBloodGroup() != requestedGroup) {
            throw new IllegalArgumentException("Blood group mismatch.");
        }

        IssueRecord issue = new IssueRecord();
        issue.setBloodGroup(requestedGroup);
        issue.setRequestDate(requestDate);
        issue.setRecipientDetails(recipientDetails);

        IssueRecord savedIssue = issueRecordRepository.save(issue);

        unit.setStatus("ISSUED");
        bloodUnitRepository.save(unit);

        IssueUnitMap map = new IssueUnitMap(savedIssue, unit);
        issueUnitMapRepository.save(map);

        return savedIssue;
    }

    public List<BloodUnit> availableUnits(BloodGroup group) {
        return bloodUnitRepository.findByBloodGroupAndStatus(group, "AVAILABLE")
                .stream()
                .filter(unit -> !unit.isExpired())
                .toList();
    }
}
