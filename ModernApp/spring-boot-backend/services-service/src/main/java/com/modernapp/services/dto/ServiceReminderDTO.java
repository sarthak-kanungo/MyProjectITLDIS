package com.modernapp.services.dto;

import java.util.Date;

public class ServiceReminderDTO {
    private String scheduleID;
    private String vinNo;
    private String modelCodeDesc;
    private String jobTypeDesc;
    private String customerName;
    private String contactNo;
    private Date dueDate;
    private String followUpStatus;
    private Date lastFollowUpDate;
    private String dealerCode;
    private String dealerName;

    public ServiceReminderDTO() {
    }

    public ServiceReminderDTO(String scheduleID, String vinNo, String modelCodeDesc, String jobTypeDesc, String customerName, String contactNo, Date dueDate, String followUpStatus, Date lastFollowUpDate, String dealerCode, String dealerName) {
        this.scheduleID = scheduleID;
        this.vinNo = vinNo;
        this.modelCodeDesc = modelCodeDesc;
        this.jobTypeDesc = jobTypeDesc;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.dueDate = dueDate;
        this.followUpStatus = followUpStatus;
        this.lastFollowUpDate = lastFollowUpDate;
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getModelCodeDesc() {
        return modelCodeDesc;
    }

    public void setModelCodeDesc(String modelCodeDesc) {
        this.modelCodeDesc = modelCodeDesc;
    }

    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public Date getLastFollowUpDate() {
        return lastFollowUpDate;
    }

    public void setLastFollowUpDate(Date lastFollowUpDate) {
        this.lastFollowUpDate = lastFollowUpDate;
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
}
