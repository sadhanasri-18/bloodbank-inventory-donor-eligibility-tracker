package com.srieshwar.bloodbank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ISSUE_RECORD")
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "issue_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID issueId;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false, length = 20)
    private BloodGroup bloodGroup;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Column(name = "recipient_details", length = 500)
    private String recipientDetails;

    @OneToMany(mappedBy = "issueRecord", cascade = CascadeType.ALL)
    private List<IssueUnitMap> issuedUnits = new ArrayList<>();

    public IssueRecord() {}

    public UUID getIssueId() { return issueId; }
    public void setIssueId(UUID issueId) { this.issueId = issueId; }

    public BloodGroup getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(BloodGroup bloodGroup) { this.bloodGroup = bloodGroup; }

    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }

    public String getRecipientDetails() { return recipientDetails; }
    public void setRecipientDetails(String recipientDetails) { this.recipientDetails = recipientDetails; }

    public List<IssueUnitMap> getIssuedUnits() { return issuedUnits; }
    public void setIssuedUnits(List<IssueUnitMap> issuedUnits) { this.issuedUnits = issuedUnits; }
}
