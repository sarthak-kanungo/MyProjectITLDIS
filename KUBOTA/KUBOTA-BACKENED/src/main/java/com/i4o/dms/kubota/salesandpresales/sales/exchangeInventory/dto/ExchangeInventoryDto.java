package com.i4o.dms.kubota.salesandpresales.sales.exchangeInventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","enquiryNumber","soldStatus","brandName","modelName","modelYear","invInDate","estimatedExchangePrice",
	"buyerName","buyerContactNo","saleDate","sellingPrice","saleRemarks"})
public interface ExchangeInventoryDto {

    Long getId();
    String getBrandName();
    String getModelName();
    Integer getModelYear();
    String getInvInDate();
    Double getEstimatedExchangePrice();
//    String getStatus();
//    Long getBuyerId();
    String getBuyerName();
    String getBuyerContactNo();
    String getSaleDate();
    Double getSellingPrice();
    String getEnquiryNumber();
    String getSaleRemarks();
    String getSoldStatus();
    @JsonIgnore
    Long getTotalRecords();
    String getAction();
//    interface Enquiry{
//        Long getId();
//       String getEnquiryNumber();
//       String getMobileNumber();
//       String getVariant();
//    }
}
