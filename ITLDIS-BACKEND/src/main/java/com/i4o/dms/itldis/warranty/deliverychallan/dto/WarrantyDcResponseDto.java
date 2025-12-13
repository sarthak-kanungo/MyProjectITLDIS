package com.i4o.dms.itldis.warranty.deliverychallan.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"action","dcNo","dcDate","DealerCode","dealerName","dealerEmail","transporterName","docketNumber","numberOfBox","dispatchDate"})
public interface WarrantyDcResponseDto {
    String getDcNo();
    String getDcDate();
    String getDealerCode();
    String getDealerName();
    String getDealerEmail();
    String getTransporterName();
    String getDocketNumber();
    String getNumberOfBox();
    String getDispatchDate();
    String getAction();

    @JsonIgnore
    Long getTotalCount();

}
