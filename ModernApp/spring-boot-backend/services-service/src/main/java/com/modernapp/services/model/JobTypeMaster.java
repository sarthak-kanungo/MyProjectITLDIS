package com.modernapp.services.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Jobtypemaster")
public class JobTypeMaster {

    @Id
    @Column(name = "jobTypeID")
    private String jobTypeID;

    @Column(name = "jobTypeDesc")
    private String jobTypeDesc;

    @Column(name = "isActive")
    private String isActive;

    @Column(name = "freeService")
    private String freeService;

    @Column(name = "seqNo")
    private Integer seqNo;

    // Getters and Setters

    public String getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(String jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
}
