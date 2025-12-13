package com.i4o.dms.itldis.masters.usermanagement.kubotausers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "code", "transporterName", "title", "designation", "transporterLocation", "firstName",
        "middleName","lastName", "mobileNumber", "email", "gstNumber", "panNumber","adharCardNumber","address1","address2",
        "address3","pinCode","locality","tehsil","country","city","state","district","telephone"})

public interface TransporterSearchResponse {
    Long getId();

    String getCode();

    String getTransporterName();

    String getTitle();

    String getDesignation();

    String getTransporterLocation();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    String getMobileNumber();

    String getEmail();

    String getGstNumber();

    String getPanNumber();

    String getAdharCardNumber();

    String getAddress1();

    String getAddress2();

    String getAddress3();

    String getPinCode();

    String getLocality();

    String getTehsil();

    String getCountry();

    String getCity();

    String getState();

    String getDistrict();

    String getTelephone();

}

