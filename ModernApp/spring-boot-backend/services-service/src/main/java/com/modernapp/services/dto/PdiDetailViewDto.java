package com.modernapp.services.dto;

import java.time.LocalDate;

public class PdiDetailViewDto {
    private String vinNo;
    private String engineNo;
    private String modelFamily;
    private String modelCode;
    private String modelFamilyDesc;
    private String modelCodeDesc;
    private String pdiNo;
    private String invoiceDate;
    private String tractorReceivedDate;
    private String pdiDate;
    private String invoiceNo;
    private String dealerName;
    private String dealerCode;
    private String locationName;
    private String locationCode;
    private String remarks;
    private String pdiDoneBy;
    private String jobCardNo;
    private boolean createJobCard;
    private java.util.List<TravelCardPartDto> travelCardParts;
    private java.util.List<ChecklistGroupDto> checklist;

    public PdiDetailViewDto() {
    }

    // Getters and Setters
    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
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

    public String getModelFamilyDesc() {
        return modelFamilyDesc;
    }

    public void setModelFamilyDesc(String modelFamilyDesc) {
        this.modelFamilyDesc = modelFamilyDesc;
    }

    public String getModelCodeDesc() {
        return modelCodeDesc;
    }

    public void setModelCodeDesc(String modelCodeDesc) {
        this.modelCodeDesc = modelCodeDesc;
    }

    public String getPdiNo() {
        return pdiNo;
    }

    public void setPdiNo(String pdiNo) {
        this.pdiNo = pdiNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getTractorReceivedDate() {
        return tractorReceivedDate;
    }

    public void setTractorReceivedDate(String tractorReceivedDate) {
        this.tractorReceivedDate = tractorReceivedDate;
    }

    public String getPdiDate() {
        return pdiDate;
    }

    public void setPdiDate(String pdiDate) {
        this.pdiDate = pdiDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPdiDoneBy() {
        return pdiDoneBy;
    }

    public void setPdiDoneBy(String pdiDoneBy) {
        this.pdiDoneBy = pdiDoneBy;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public boolean isCreateJobCard() {
        return createJobCard;
    }

    public void setCreateJobCard(boolean createJobCard) {
        this.createJobCard = createJobCard;
    }

    public java.util.List<TravelCardPartDto> getTravelCardParts() {
        return travelCardParts;
    }

    public void setTravelCardParts(java.util.List<TravelCardPartDto> travelCardParts) {
        this.travelCardParts = travelCardParts;
    }

    public java.util.List<ChecklistGroupDto> getChecklist() {
        return checklist;
    }

    public void setChecklist(java.util.List<ChecklistGroupDto> checklist) {
        this.checklist = checklist;
    }
}

