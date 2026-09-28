package com.srieshwar.bloodbank.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "DONATION")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "donation_id", columnDefinition = "char(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID donationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donor_id", nullable = false)
    private Donor donor;

    @Column(name = "collection_date", nullable = false)
    private LocalDate collectionDate;

    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL)
    private List<BloodUnit> bloodUnits = new ArrayList<>();

    public Donation() {}

    public UUID getDonationId() { return donationId; }
    public void setDonationId(UUID donationId) { this.donationId = donationId; }

    public Donor getDonor() { return donor; }
    public void setDonor(Donor donor) { this.donor = donor; }

    public LocalDate getCollectionDate() { return collectionDate; }
    public void setCollectionDate(LocalDate collectionDate) { this.collectionDate = collectionDate; }

    public List<BloodUnit> getBloodUnits() { return bloodUnits; }
    public void setBloodUnits(List<BloodUnit> bloodUnits) { this.bloodUnits = bloodUnits; }
}
