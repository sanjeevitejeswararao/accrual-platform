package com.example.dealservice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
        name = "deals",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_deal_external_reference",
                        columnNames = "external_reference"
                )
        },
        indexes = {
                @Index(
                        name = "idx_deal_status",
                        columnList = "deal_status"
                ),
                @Index(
                        name = "idx_deal_start_date",
                        columnList = "start_date"
                )
        }
)
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "external_reference",
            nullable = false,
            length = 64
    )
    private String externalReference;

    @Column(
            name = "principal_amount",
            nullable = false,
            precision = 19,
            scale = 4
    )
    private BigDecimal principalAmount;

    @Column(nullable = false, length = 3)
    private String currency;

    @Column(
            name = "annual_rate",
            nullable = false,
            precision = 12,
            scale = 8
    )
    private BigDecimal annualRate;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "maturity_date", nullable = false)
    private LocalDate maturityDate;

    @Column(name = "day_count_basis", nullable = false)
    private Integer dayCountBasis;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "deal_status",
            nullable = false,
            length = 20
    )
    private DealStatus status;

    @Version
    private Long version;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Deal() {
    }

    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;

        if (status == null) {
            status = DealStatus.RECEIVED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public String getExternalReference() {
        return externalReference;
    }

    public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

    public BigDecimal getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(BigDecimal principalAmount) {
        this.principalAmount = principalAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal annualRate) {
        this.annualRate = annualRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Integer getDayCountBasis() {
        return dayCountBasis;
    }

    public void setDayCountBasis(Integer dayCountBasis) {
        this.dayCountBasis = dayCountBasis;
    }

    public DealStatus getStatus() {
        return status;
    }

    public void setStatus(DealStatus status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}