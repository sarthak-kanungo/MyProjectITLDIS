package com.modernapp.services.dto;

import java.time.LocalDate;

public class PendingInstallationDTO {
    private String vinNo;
    private String modelFamily;
    private String modelCode;
    private String customerName;
    private Long mobilePh;
    private String dealerName;
    private String dealerCode;
    private String location;
    private String locationCode;
    private LocalDate deliveryDate;

    public PendingInstallationDTO(String vinNo, String modelFamily, String modelCode, String customerName,
                                  Long mobilePh, String dealerName, String dealerCode, String location,
                                  String locationCode, LocalDate deliveryDate) {
        this.vinNo = vinNo;
        this.modelFamily = modelFamily;
        this.modelCode = modelCode;
        this.customerName = customerName;
        this.mobilePh = mobilePh;
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
        this.location = location;
        this.locationCode = locationCode;
        this.deliveryDate = deliveryDate;
    }

    public PendingInstallationDTO() {}

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(Long mobilePh) {
        this.mobilePh = mobilePh;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
