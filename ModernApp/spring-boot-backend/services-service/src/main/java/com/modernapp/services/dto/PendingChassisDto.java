package com.modernapp.services.dto;

import java.time.LocalDate;

public class PendingChassisDto {
    private String vinNo;
    private String modelFamily;
    private String modelCode;
    private String dealerCode;
    private String dealerName;
    private String locationName;
    private String locationCode;
    private Integer pdiPendingDays;
    private LocalDate pdiPendingDate;

    public PendingChassisDto() {
    }

    public PendingChassisDto(String vinNo, String modelFamily, String modelCode, 
                            String dealerCode, String dealerName, String locationName, 
                            String locationCode, Integer pdiPendingDays, LocalDate pdiPendingDate) {
        this.vinNo = vinNo;
        this.modelFamily = modelFamily;
        this.modelCode = modelCode;
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
        this.locationName = locationName;
        this.locationCode = locationCode;
        this.pdiPendingDays = pdiPendingDays;
        this.pdiPendingDate = pdiPendingDate;
    }

    // Getters and Setters
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

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
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

    public Integer getPdiPendingDays() {
        return pdiPendingDays;
    }

    public void setPdiPendingDays(Integer pdiPendingDays) {
        this.pdiPendingDays = pdiPendingDays;
    }

    public LocalDate getPdiPendingDate() {
        return pdiPendingDate;
    }

    public void setPdiPendingDate(LocalDate pdiPendingDate) {
        this.pdiPendingDate = pdiPendingDate;
    }
}

