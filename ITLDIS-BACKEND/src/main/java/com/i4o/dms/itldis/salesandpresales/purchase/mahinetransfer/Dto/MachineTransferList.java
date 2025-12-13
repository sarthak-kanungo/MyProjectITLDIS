package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","requestNumber", "requestDate", "status", "coDealerCode", "coDealerName", "itemNumber", "product", "model", "subModel", "variant"})

public interface MachineTransferList {

    Long getId();
    @JsonFormat(pattern = "dd-MM-yyyy")
    String getRequestDate();
    String getRequestNumber();
    String getStatus();
    String getProduct();
    String getSeries();
    String getModel();
    String getSubModel();
    String getVariant();
    String getItemNumber();
    String getCoDealerCode();
    String getCoDealerName();
    String getAction();
    @JsonIgnore
    Integer getTotalCount();
}
