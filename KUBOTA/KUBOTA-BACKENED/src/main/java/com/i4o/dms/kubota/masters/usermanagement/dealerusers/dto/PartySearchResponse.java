package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","id","partyCode","partyName","partyLocation","title","contactName","gstNumber","panNumber","adharCardNumber",
	"designation","mobileNumber","email","address1","address2","address3","category","postOffice",
	"locality","city","tehsil","district","state","country","pinCode","telephone"})

public interface PartySearchResponse {
    Long getId();

    String getPartyCode();

    String getPartyName();

    String getPartyLocation();

    String getGstNumber();

    String getPanNumber();

    String getAdharCardNumber();

    @JsonIgnore
    String getFirstName();

    @JsonIgnore
    String getMiddleName();

    @JsonIgnore
    String getLastName();

    String getDesignation();

    String getMobileNumber();

    String getEmail();
    
    String getTitle();
    
    String getContactName();
    
    String getAddress1();
    
    String getAddress2();
    
    String getAddress3();
    
    String getCategory();
    
    String getPostOffice();
    
    String getLocality();
    
    String getCity();
    
    String getTehsil();
    
    String getDistrict();
    
    String getState();
    
    String getCountry();
    
    String getPinCode();
    
    String getTelephone();
    
    String getAction();
    
    @JsonIgnore
    Long getTotalRecords();
}

