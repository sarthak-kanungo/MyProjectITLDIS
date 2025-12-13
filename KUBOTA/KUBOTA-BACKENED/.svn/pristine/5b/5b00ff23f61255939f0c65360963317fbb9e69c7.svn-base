package com.i4o.dms.kubota.salesandpresales.grn.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.i4o.dms.kubota.masters.dbentities.grn.Transporter;

import java.util.Date;

@JsonPropertyOrder({"id","dmsGrnNumber","grnDate","grnType","grnStatus","invoiceNumber","invoiceDate","billTo","shipTo",
"transporterName","driverName","driverMobile","transporterVehicleNo","lrNo","grnBy","grossTotalValue"})
@JsonIgnoreProperties({"totalCount"})
public interface GrnSearchResponseDto {
    Long getId();
    String getGrnType();
    String getDmsGrnNumber();
    String getGrnDate();
    String getDriverName();
    String getDriverMobile();
    String getTransporterVehicleNumber();
    String getGrnBy();
    Double getGrossTotalValue();
    String getInvoiceNumber();
    String getInvoiceDate();
    String getBillTo();
    String getShipTo();
    String getTransporterName();
    String getLrNo();
    Long getTotalCount();
}
