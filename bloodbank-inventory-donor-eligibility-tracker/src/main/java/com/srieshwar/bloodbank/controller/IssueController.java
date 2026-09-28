package com.srieshwar.bloodbank.controller;

import com.srieshwar.bloodbank.entity.BloodGroup;
import com.srieshwar.bloodbank.entity.BloodUnit;
import com.srieshwar.bloodbank.entity.IssueRecord;
import com.srieshwar.bloodbank.service.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<IssueRecord> issue(@RequestBody Map<String, String> request) {
        UUID unitId = UUID.fromString(request.get("unitId"));
        BloodGroup group = BloodGroup.valueOf(request.get("bloodGroup"));
        String recipient = request.getOrDefault("recipientDetails", "");
        LocalDate requestDate = LocalDate.parse(request.getOrDefault("requestDate", LocalDate.now().toString()));

        return ResponseEntity.ok(
                issueService.issueBlood(unitId, group, recipient, requestDate)
        );
    }

    @GetMapping("/available/{bloodGroup}")
    public ResponseEntity<List<BloodUnit>> available(@PathVariable BloodGroup bloodGroup) {
        return ResponseEntity.ok(issueService.availableUnits(bloodGroup));
    }
}
