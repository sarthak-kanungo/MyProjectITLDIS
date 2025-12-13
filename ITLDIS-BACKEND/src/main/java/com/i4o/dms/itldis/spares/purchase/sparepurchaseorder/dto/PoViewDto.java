package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoViewDto {


    private HeaderResponse headerResponse;

    private List<Map<String, Object>> partDetails;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String,Object> supplierName;
    
    private List<Map<String, Object>> approvalResponse;
}
