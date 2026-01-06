package com.modernapp.services.dto;

public class VehicleDetailsDTO {
    private String vinNo;
    private String engineNo;
    private String saleDate;
    private String modelFamily;
    private String sellingDealerCode;
    private String sellingDealerName;
    private String plateNo;
    private String modelCode;
    private String modelDescription;
    private String customerName; // From vehicle owner details
    private String customerId;

    public String getVinNo() { return vinNo; }
    public void setVinNo(String vinNo) { this.vinNo = vinNo; }

    public String getEngineNo() { return engineNo; }
    public void setEngineNo(String engineNo) { this.engineNo = engineNo; }

    public String getSaleDate() { return saleDate; }
    public void setSaleDate(String saleDate) { this.saleDate = saleDate; }

    public String getModelFamily() { return modelFamily; }
    public void setModelFamily(String modelFamily) { this.modelFamily = modelFamily; }

    public String getSellingDealerCode() { return sellingDealerCode; }
    public void setSellingDealerCode(String sellingDealerCode) { this.sellingDealerCode = sellingDealerCode; }

    public String getSellingDealerName() { return sellingDealerName; }
    public void setSellingDealerName(String sellingDealerName) { this.sellingDealerName = sellingDealerName; }

    public String getPlateNo() { return plateNo; }
    public void setPlateNo(String plateNo) { this.plateNo = plateNo; }

    public String getModelCode() { return modelCode; }
    public void setModelCode(String modelCode) { this.modelCode = modelCode; }

    public String getModelDescription() { return modelDescription; }
    public void setModelDescription(String modelDescription) { this.modelDescription = modelDescription; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
}
