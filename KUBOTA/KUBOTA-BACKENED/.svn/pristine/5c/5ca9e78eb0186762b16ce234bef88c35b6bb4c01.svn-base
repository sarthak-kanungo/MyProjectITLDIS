package com.i4o.dms.kubota.spares.purchase.grn.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id","edit","grnNumber","grnDate","grnType","grnStatus","invoiceNumber","invoiceDate","supplierName","supplierType","supplierGstNo",
        "modeOfTransport","supplierAddress1","supplierAddress2","supplierAddress3","supplierState","transporter",
        "invoiceValue","grnDoneBy","noOfBoxesSent","noOfBoxesReceived","stores","basicAmount","gstAmount","totalGrnAmount"})
//goodsReceiptDate,receiptValue
public interface GrnSearchResponseDto {
    Long getId();
    String getEdit();
    String getGrnType();
    String getGrnNumber();
    String getGrnStatus();
    String getGrnDate();
    String getSupplierName();
    String getSupplierType();
    String getSupplierGstNo();
    String getModeOfTransport();
    String getSupplierAddress1();
    String getSupplierAddress2();
    String getSupplierAddress3();
    String getSupplierState();
    String getTransporter();
    String getInvoiceNumber();
    String getInvoiceDate();
    String getInvoiceValue();
    String getGrnDoneBy();
    Integer noOfBoxesSent();
    Integer noOfBoxesReceived();
    @JsonIgnore
    String getBranchCode();
//    Double getReceiptValue();
//    String getGoodsReceiptDate();
    String getStores();
    Double getBasicAmount();
    Double getGstAmount();
    Double getTotalGrnAmount();
    @JsonIgnore
    Long getRecordCount();
    

    @JsonIgnore
    String getItemNo();
    @JsonIgnore
    String getItemDesc();
    @JsonIgnore
    Integer getQuantity();
    @JsonIgnore
    BigDecimal getNdp();
    @JsonIgnore
    BigDecimal getBasic();
    @JsonIgnore
    BigDecimal getGst();
    @JsonIgnore
    BigDecimal getTotalAmount();
}
