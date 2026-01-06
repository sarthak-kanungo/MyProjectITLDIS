package com.modernapp.services.dto;

public class ServiceDoneLapseDTO {
    private String vinNo;
    private String modelCode;
    private String modelCodeDesc;
    private String jobTypeDesc;
    private String jobCardNo;
    private String jobCardDate;
    private String hmr;
    private String dealerCode;
    private String dealerName;
    private String locationName;

    public ServiceDoneLapseDTO() {}

    public String getVinNo() { return vinNo; }
    public void setVinNo(String vinNo) { this.vinNo = vinNo; }

    public String getModelCode() { return modelCode; }
    public void setModelCode(String modelCode) { this.modelCode = modelCode; }

    public String getModelCodeDesc() { return modelCodeDesc; }
    public void setModelCodeDesc(String modelCodeDesc) { this.modelCodeDesc = modelCodeDesc; }

    public String getJobTypeDesc() { return jobTypeDesc; }
    public void setJobTypeDesc(String jobTypeDesc) { this.jobTypeDesc = jobTypeDesc; }

    public String getJobCardNo() { return jobCardNo; }
    public void setJobCardNo(String jobCardNo) { this.jobCardNo = jobCardNo; }

    public String getJobCardDate() { return jobCardDate; }
    public void setJobCardDate(String jobCardDate) { this.jobCardDate = jobCardDate; }

    public String getHmr() { return hmr; }
    public void setHmr(String hmr) { this.hmr = hmr; }

    public String getDealerCode() { return dealerCode; }
    public void setDealerCode(String dealerCode) { this.dealerCode = dealerCode; }

    public String getDealerName() { return dealerName; }
    public void setDealerName(String dealerName) { this.dealerName = dealerName; }

    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
}
