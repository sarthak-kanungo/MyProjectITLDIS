package com.modernapp.services.dto;

import java.time.LocalDate;

public class ViewInstallationDTO {
    private String insNo;
    private String insDate; // Formatted String
    private String vinNo;
    private String modelFamily;
    private String customerName;
    private String mobilePh;
    private String dealerName;
    private String dealerCode;
    private String location;
    private String deliveryDate; // Formatted String

    public ViewInstallationDTO() {}

    public ViewInstallationDTO(String insNo, LocalDate insDate, String vinNo, String modelFamily, 
                               String customerName, Long mobilePh, String dealerName, String dealerCode, 
                               String location, LocalDate deliveryDate) {
        this.insNo = insNo;
        this.insDate = insDate != null ? insDate.toString() : ""; 
        this.vinNo = vinNo;
        this.modelFamily = modelFamily;
        this.customerName = customerName;
        this.mobilePh = mobilePh != null ? String.valueOf(mobilePh) : "";
        this.dealerName = dealerName;
        this.dealerCode = dealerCode;
        this.location = location;
        this.deliveryDate = deliveryDate != null ? deliveryDate.toString() : "";
    }

    // Getters and Setters
    public String getInsNo() { return insNo; }
    public void setInsNo(String insNo) { this.insNo = insNo; }

    public String getInsDate() { return insDate; }
    public void setInsDate(String insDate) { this.insDate = insDate; }

    public String getVinNo() { return vinNo; }
    public void setVinNo(String vinNo) { this.vinNo = vinNo; }

    public String getModelFamily() { return modelFamily; }
    public void setModelFamily(String modelFamily) { this.modelFamily = modelFamily; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getMobilePh() { return mobilePh; }
    public void setMobilePh(String mobilePh) { this.mobilePh = mobilePh; }

    public String getDealerName() { return dealerName; }
    public void setDealerName(String dealerName) { this.dealerName = dealerName; }

    public String getDealerCode() { return dealerCode; }
    public void setDealerCode(String dealerCode) { this.dealerCode = dealerCode; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(String deliveryDate) { this.deliveryDate = deliveryDate; }
}
