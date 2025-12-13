package com.i4o.dms.kubota.spares.salesorder.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SpareSalesOrderViewDto {

    Map<String,Object> headerResponse;

    List<Map<String,Object>> partDetails;
}
