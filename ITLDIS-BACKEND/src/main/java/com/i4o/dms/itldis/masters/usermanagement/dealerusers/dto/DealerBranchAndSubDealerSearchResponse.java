package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","category","branchCode","subDealerName","location","subDealerGstNumber","subDealerPanNumber","subDealerAdharCardNumber","contactPerson"
        ,"title","firstName","middleName","lastName","designation","mobileNumber","emailId","address1","address2","address3","pinCode","locality",
         "tehsil","city","state","country","telephoneNo"})

public interface DealerBranchAndSubDealerSearchResponse {

    Long getId();

    String getCategory();

    String getBranchCode();

    String getSubDealerName();

    String getLocation();

    Double getSubDealerGstNumber();

    Double getSubDealerPanNumber();

    String getSubDealerAdharCardNumber();

    String getContactPerson();

    String getTitle();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    String getDesignation();

    String getMobileNumber();

    String getEmailId();

    String getAddress1();

    String getAddress2();

    String getAddress3();

    String getPinCode();

    String getLocality();

    String getTehsil();

    String getCity();

    String getState();

    String getCountry();

    String getTelephoneNo();
}
