package com.modernapp.services.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "PdiDetails")
public class PdiDetails {
    
    @Id
    @Column(name = "PDI_No")
    private String pdiNo;
    
    @Column(name = "Dealercode")
    private String dealercode;
    
    @Column(name = "vinNo")
    private String vinNo;
    
    @Column(name = "PDI_Date")
    private LocalDate pdiDate;
    
    @Column(name = "Ref_PDI_No")
    private String refPDINo;
    
    @Column(name = "EngineNo")
    private String engineNo;
    
    @Column(name = "ITL_InvoiceNo")
    private String itlInvoiceNo;
    
    @Column(name = "ITL_InvoiceDate")
    private LocalDate invoiceDate;
    
    @Column(name = "Remarks")
    private String remarks;
    
    @Column(name = "CreatedBy")
    private String createdBy;
    
    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;
    
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    
    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    // Constructors
    public PdiDetails() {
    }

    public PdiDetails(String pdiNo, String dealercode, String vinNo, LocalDate pdiDate) {
        this.pdiNo = pdiNo;
        this.dealercode = dealercode;
        this.vinNo = vinNo;
        this.pdiDate = pdiDate;
    }

    // Getters and Setters
    public String getPdiNo() {
        return pdiNo;
    }

    public void setPdiNo(String pdiNo) {
        this.pdiNo = pdiNo;
    }

    public String getDealercode() {
        return dealercode;
    }

    public void setDealercode(String dealercode) {
        this.dealercode = dealercode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public LocalDate getPdiDate() {
        return pdiDate;
    }

    public void setPdiDate(LocalDate pdiDate) {
        this.pdiDate = pdiDate;
    }

    public String getRefPDINo() {
        return refPDINo;
    }

    public void setRefPDINo(String refPDINo) {
        this.refPDINo = refPDINo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getItlInvoiceNo() {
        return itlInvoiceNo;
    }

    public void setItlInvoiceNo(String itlInvoiceNo) {
        this.itlInvoiceNo = itlInvoiceNo;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}

