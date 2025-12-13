package com.i4o.dms.itldis.spares.quotation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpareQuotationSearchDto {

    private Long quotationId;

    private String customerName;

    private String customerType;

    private String quotationFromDate;

    private String quotationToDate;

    private Integer page;

    private Integer size;
}
