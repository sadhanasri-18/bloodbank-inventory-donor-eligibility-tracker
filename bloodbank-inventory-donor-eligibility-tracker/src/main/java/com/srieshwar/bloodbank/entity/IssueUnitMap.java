package com.srieshwar.bloodbank.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ISSUE_UNIT_MAP")
public class IssueUnitMap {

    @EmbeddedId
    private IssueUnitMapId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("issueId")
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueRecord issueRecord;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("unitId")
    @JoinColumn(name = "unit_id", nullable = false)
    private BloodUnit bloodUnit;

    public IssueUnitMap() {}

    public IssueUnitMap(IssueRecord issueRecord, BloodUnit bloodUnit) {
        this.issueRecord = issueRecord;
        this.bloodUnit = bloodUnit;
        this.id = new IssueUnitMapId(issueRecord.getIssueId(), bloodUnit.getUnitId());
    }

    public IssueUnitMapId getId() { return id; }
    public void setId(IssueUnitMapId id) { this.id = id; }

    public IssueRecord getIssueRecord() { return issueRecord; }
    public void setIssueRecord(IssueRecord issueRecord) { this.issueRecord = issueRecord; }

    public BloodUnit getBloodUnit() { return bloodUnit; }
    public void setBloodUnit(BloodUnit bloodUnit) { this.bloodUnit = bloodUnit; }
}
