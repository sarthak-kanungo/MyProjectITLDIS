package com.modernapp.services.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Vehicledetails")
public class VehicleDetails {
    
    @Id
    @Column(name = "vinid")
    private String vinid;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "vin_no")
    private String vinNo;
    
    @Column(name = "model_family")
    private String modelFamily;
    
    @Column(name = "model_code")
    private String modelCode;
    
    @Column(name = "pdi_status")
    private Character pdiStatus;
    
    @Column(name = "pdi_pending_date")
    private LocalDate pdiPendingDate;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "engine_no")
    private String engineNo;
    
    @Column(name = "reg_no")
    private String regNo;

    @Column(name = "ins_status")
    private Character insStatus;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "mobile_ph")
    private Long mobilePh;

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


    public Character getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Character insStatus) {
        this.insStatus = insStatus;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(Long mobilePh) {
        this.mobilePh = mobilePh;
    }
}

