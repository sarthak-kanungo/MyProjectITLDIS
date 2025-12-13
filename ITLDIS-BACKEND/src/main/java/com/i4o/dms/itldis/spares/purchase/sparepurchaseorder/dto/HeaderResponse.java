package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HeaderResponse implements Serializable {

    private Map<String, Object> headerDetails;

    private Map<String, Object> orderType;

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private Map<String, Object> coDealerMaster;

}
