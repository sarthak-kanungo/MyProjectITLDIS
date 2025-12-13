package com.i4o.dms.kubota.salesandpresales.enquiry.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"enquiryNumber","enquiryDate","salesPerson","enquiryStatus","enquiryType","variant","customerName","tehsil","district","city","state"})
public interface SearchEnquiryToTransfer {

    public Long getId();
    public Long getSalesPersonId();
    public String getEnquiryNumber();
    @JsonFormat(pattern = "dd-MM-yyyy")
    public String getEnquiryDate();
    public String getSalesPerson();
    public String getEnquiryStatus();
    public String getEnquiryType();
    public String getVariant();
    public String getCustomerName();

    public String getTehsil();
    public String getDistrict();
    public String getCity();
    public String getState();



}
