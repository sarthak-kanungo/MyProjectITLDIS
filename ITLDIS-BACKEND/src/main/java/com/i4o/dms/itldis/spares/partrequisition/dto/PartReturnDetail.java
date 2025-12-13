package com.i4o.dms.itldis.spares.partrequisition.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PartReturnDetail {
    private Map<String,Object> headerData;
    private List<Map<String,Object>> lineItems;
}
