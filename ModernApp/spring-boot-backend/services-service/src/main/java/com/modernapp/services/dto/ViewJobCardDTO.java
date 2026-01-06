package com.modernapp.services.dto;

public class ViewJobCardDTO {
    private String jobCardNo;
    private String jobCardDate;
    private String vinNo;
    private String payeeName;
    private String dealerCode;
    private String dealerName;
    private String location;
    private String locationCode;
    private String status;
    private String mobilePhone;

    public ViewJobCardDTO() {}

    public ViewJobCardDTO(String jobCardNo, Object jobCardDate, String vinNo, String payeeName, 
                          String dealerCode, String dealerName, String location, String locationCode, 
                          String status, String mobilePhone) {
        this.jobCardNo = jobCardNo;
        if (jobCardDate instanceof java.time.LocalDateTime) {
            this.jobCardDate = ((java.time.LocalDateTime) jobCardDate).format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else if (jobCardDate != null) {
            this.jobCardDate = jobCardDate.toString();
        } else {
            this.jobCardDate = "";
        }
        this.vinNo = vinNo;
        this.payeeName = payeeName;
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
        this.location = location;
        this.locationCode = locationCode;
        this.status = status;
        this.mobilePhone = mobilePhone;
    }

    // Getters and Setters
    public String getJobCardNo() { return jobCardNo; }
    public void setJobCardNo(String jobCardNo) { this.jobCardNo = jobCardNo; }

    public String getJobCardDate() { return jobCardDate; }
    public void setJobCardDate(String jobCardDate) { this.jobCardDate = jobCardDate; }

    public String getVinNo() { return vinNo; }
    public void setVinNo(String vinNo) { this.vinNo = vinNo; }

    public String getPayeeName() { return payeeName; }
    public void setPayeeName(String payeeName) { this.payeeName = payeeName; }

    public String getDealerCode() { return dealerCode; }
    public void setDealerCode(String dealerCode) { this.dealerCode = dealerCode; }

    public String getDealerName() { return dealerName; }
    public void setDealerName(String dealerName) { this.dealerName = dealerName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLocationCode() { return locationCode; }
    public void setLocationCode(String locationCode) { this.locationCode = locationCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMobilePhone() { return mobilePhone; }
    public void setMobilePhone(String mobilePhone) { this.mobilePhone = mobilePhone; }
}
