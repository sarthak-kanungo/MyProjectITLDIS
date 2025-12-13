package com.i4o.dms.kubota.spares.invoice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "edit","cancel","salesInvoiceNumber", "invoiceDate", "customerType",
		"referenceDocument", "referenceDocumentNo", "referenceDocumentDate", "salesType", "customerCode",
        "customerName", "contactNumber", "customerAddress1", "customerAddress2", "postOffice", "pincode", "village", "tehsil",
        "district", "state", "modeOfTransport", "transporter", "lrNo", "chassisNo", "engineNo"})
public interface ResponseSearchSparesInvoice {

    String getId();

    String getEdit();
    
    @JsonProperty("Sales_Invoice_Number")
    String getSalesInvoiceNumber();

    @JsonProperty("Invoice_Date")
    String getInvoiceDate();

    @JsonProperty("Customer_Type")
    String getCustomerType();

    String getReferenceDocument();
    
    @JsonProperty("Reference_Document")
    String getReferenceDocumentNo();

    @JsonProperty("Reference_Document_Date")
    String getReferenceDocumentDate();

    @JsonProperty("Sales_Type")
    String getSalesType();

    @JsonProperty("Customer_Code")
    String getCustomerCode();

    @JsonProperty("Customer_Name")
    String getCustomerName();

    @JsonProperty("Contact_Number")
    String getContactNumber();

    @JsonProperty("Customer_Address1")
    String getCustomerAddress1();

    @JsonProperty("Customer_Address2")
    String getCustomerAddress2();

    @JsonProperty("Post_Office")
    String getPostOffice();

    @JsonProperty("Pincode")
    String getPincode();

    @JsonProperty("Village")
    String getVillage();

    @JsonProperty("Tehsil")
    String getTehsil();

    @JsonProperty("District")
    String getDistrict();

    @JsonProperty("State")
    String getState();

    @JsonProperty("Mode_Of_Transport")
    String getModeOfTransport();

    @JsonProperty("Transporter")
    String getTransporter();

    @JsonProperty("L_R_No")
    String getLrNo();

    @JsonProperty("Cancel")
    String getCancel();

    String getChassisNo();
    
    String getEngineNo();
    
    //--------------------Suraj-22/09/22
    String getWcrNo();
    
    String getJobCardNo();
    //--------------------
    
    @JsonIgnore
    Long getRecordCount();

    @JsonIgnore
    String getItemNo();
    @JsonIgnore
    String getItemDesc();
    @JsonIgnore
    Integer getQuantity();
    @JsonIgnore
    BigDecimal getUnitPrice();
    @JsonIgnore
    BigDecimal getMrp();
    @JsonIgnore
    BigDecimal getGst();
    @JsonIgnore
    BigDecimal getAmount();
    @JsonIgnore
    BigDecimal getDiscountPerc();
    @JsonIgnore
    BigDecimal getDiscountAmount();
    @JsonIgnore
    BigDecimal getNetAmount();
    @JsonIgnore
    BigDecimal getCgstPerc();
    @JsonIgnore
    BigDecimal getCgstAmount();
    @JsonIgnore
    BigDecimal getSgstPerc();
    @JsonIgnore
    BigDecimal getSgstAmount();
    @JsonIgnore
    BigDecimal getIgstPerc();
    @JsonIgnore
    BigDecimal getIgstAmount();
    @JsonIgnore
    String getSuppliedPart();
    @JsonIgnore
    String getMachineSrNo();
    @JsonIgnore
    BigDecimal getTotalAmount();
    
}



