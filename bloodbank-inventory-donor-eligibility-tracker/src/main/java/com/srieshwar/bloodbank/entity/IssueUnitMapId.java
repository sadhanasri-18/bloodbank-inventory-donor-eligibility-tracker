package com.srieshwar.bloodbank.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class IssueUnitMapId implements Serializable {

    @Column(name = "issue_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID issueId;

    @Column(name = "unit_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID unitId;

    public IssueUnitMapId() {}

    public IssueUnitMapId(UUID issueId, UUID unitId) {
        this.issueId = issueId;
        this.unitId = unitId;
    }

    public UUID getIssueId() { return issueId; }
    public void setIssueId(UUID issueId) { this.issueId = issueId; }

    public UUID getUnitId() { return unitId; }
    public void setUnitId(UUID unitId) { this.unitId = unitId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IssueUnitMapId other)) return false;
        return java.util.Objects.equals(issueId, other.issueId)
                && java.util.Objects.equals(unitId, other.unitId);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(issueId, unitId);
    }
}
