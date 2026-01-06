package com.modernapp.services.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "InstallationDetails")
public class InstallationDetails {
    
    @Id
    @Column(name = "ins_no")
    private String insNo;
    
    @Column(name = "vin_no")
    private String vinNo;
    
    @Column(name = "ins_date")
    private LocalDate insDate;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "photograph_file_name")
    private String photographFileName;
    
    // Add other fields as necessary, matching legacy schema if known
    // For now, these are sufficient for the View List
    
    public InstallationDetails() {}

    public InstallationDetails(String insNo, String vinNo, LocalDate insDate, String dealerCode) {
        this.insNo = insNo;
        this.vinNo = vinNo;
        this.insDate = insDate;
        this.dealerCode = dealerCode;
    }

    public String getInsNo() { return insNo; }
    public void setInsNo(String insNo) { this.insNo = insNo; }

    public String getVinNo() { return vinNo; }
    public void setVinNo(String vinNo) { this.vinNo = vinNo; }

    public LocalDate getInsDate() { return insDate; }
    public void setInsDate(LocalDate insDate) { this.insDate = insDate; }

    public String getDealerCode() { return dealerCode; }
    public void setDealerCode(String dealerCode) { this.dealerCode = dealerCode; }

    public String getPhotographFileName() { return photographFileName; }
    public void setPhotographFileName(String photographFileName) { this.photographFileName = photographFileName; }
}
