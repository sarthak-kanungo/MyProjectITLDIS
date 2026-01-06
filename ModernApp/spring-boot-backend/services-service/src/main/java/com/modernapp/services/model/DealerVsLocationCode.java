package com.modernapp.services.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Dealervslocationcode")
public class DealerVsLocationCode {
    
    @Id
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "location_code")
    private String locationCode;
    
    @Column(name = "dealer_name")
    private String dealerName;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "state_name")
    private String stateName;

    // Constructors
    public DealerVsLocationCode() {
    }

    public DealerVsLocationCode(String dealerCode, String locationCode, 
                               String dealerName, String location) {
        this.dealerCode = dealerCode;
        this.locationCode = locationCode;
        this.dealerName = dealerName;
        this.location = location;
    }

    // Getters and Setters
    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}

