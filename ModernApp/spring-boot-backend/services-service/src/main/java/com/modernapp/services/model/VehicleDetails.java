package com.modernapp.services.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vehicledetails")
public class VehicleDetails {
    
    @Id
    @Column(name = "VINID")
    private String vinid;
    
    @Column(name = "DealerCode")
    private String dealerCode;
    
    @Column(name = "vinNo")
    private String vinNo;
    
    @Column(name = "ModelFamily")
    private String modelFamily;
    
    @Column(name = "ModelCode")
    private String modelCode;
    
    @Column(name = "PDI_STATUS")
    private Character pdiStatus;
    
    @Column(name = "PDI_PENDING_DATE")
    private LocalDate pdiPendingDate;
    
    @Column(name = "CustomerName")
    private String customerName;
    
    @Column(name = "EngineNo")
    private String engineNo;
    
    @Column(name = "RegNo")
    private String regNo;

    // Constructors
    public VehicleDetails() {
    }

    public VehicleDetails(String vinid, String dealerCode, String vinNo, 
                         String modelFamily, String modelCode, Character pdiStatus) {
        this.vinid = vinid;
        this.dealerCode = dealerCode;
        this.vinNo = vinNo;
        this.modelFamily = modelFamily;
        this.modelCode = modelCode;
        this.pdiStatus = pdiStatus;
    }

    // Getters and Setters
    public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getModelFamily() {
        return modelFamily;
    }

    public void setModelFamily(String modelFamily) {
        this.modelFamily = modelFamily;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public Character getPdiStatus() {
        return pdiStatus;
    }

    public void setPdiStatus(Character pdiStatus) {
        this.pdiStatus = pdiStatus;
    }

    public LocalDate getPdiPendingDate() {
        return pdiPendingDate;
    }

    public void setPdiPendingDate(LocalDate pdiPendingDate) {
        this.pdiPendingDate = pdiPendingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }
}

