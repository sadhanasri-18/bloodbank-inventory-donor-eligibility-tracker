package com.srieshwar.bloodbank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "DONOR")
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "donor_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID donorId;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false, length = 20)
    private BloodGroup bloodGroup;

    @Column(name = "last_donation_date")
    private LocalDate lastDonationDate;

    public Donor() {}

    public UUID getDonorId() { return donorId; }
    public void setDonorId(UUID donorId) { this.donorId = donorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BloodGroup getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(BloodGroup bloodGroup) { this.bloodGroup = bloodGroup; }

    public LocalDate getLastDonationDate() { return lastDonationDate; }
    public void setLastDonationDate(LocalDate lastDonationDate) { this.lastDonationDate = lastDonationDate; }

    public boolean isEligibleToday() {
        return lastDonationDate == null || !lastDonationDate.plusDays(90).isAfter(LocalDate.now());
    }
}
