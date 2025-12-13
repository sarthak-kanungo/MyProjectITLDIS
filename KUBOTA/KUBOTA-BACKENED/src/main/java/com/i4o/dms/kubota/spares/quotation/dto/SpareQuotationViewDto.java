package com.i4o.dms.kubota.spares.quotation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SpareQuotationViewDto {

    Map<String, Object> headerResponse;

    List<Map<String, Object>> partDetails;

    List<Map<String, Object>> partDetailsForSalesOrder;

}
