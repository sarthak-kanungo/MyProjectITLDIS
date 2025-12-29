package com.modernapp.services.dto;

import java.time.LocalDate;

public class PdiDetailDto {
    private String vinNo;
    private String pdiNo;
    private String pdiDate;
    private String modelFamily;
    private String modelCode;
    private String dealerCode;
    private String dealerName;
    private String locationName;
    private String locationCode;
    private String refPDIno;

    public PdiDetailDto() {
    }

    public PdiDetailDto(String vinNo, String pdiNo, String pdiDate, String modelFamily, 
                        String modelCode, String dealerCode, String dealerName, 
                        String locationName, String locationCode, String refPDIno) {
        this.vinNo = vinNo;
        this.pdiNo = pdiNo;
        this.pdiDate = pdiDate;
        this.modelFamily = modelFamily;
        this.modelCode = modelCode;
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
        this.locationName = locationName;
        this.locationCode = locationCode;
        this.refPDIno = refPDIno;
    }

    // Getters and Setters
    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getPdiNo() {
        return pdiNo;
    }

    public void setPdiNo(String pdiNo) {
        this.pdiNo = pdiNo;
    }

    public String getPdiDate() {
        return pdiDate;
    }

    public void setPdiDate(String pdiDate) {
        this.pdiDate = pdiDate;
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

    public String getRefPDIno() {
        return refPDIno;
    }

    public void setRefPDIno(String refPDIno) {
        this.refPDIno = refPDIno;
    }
}

