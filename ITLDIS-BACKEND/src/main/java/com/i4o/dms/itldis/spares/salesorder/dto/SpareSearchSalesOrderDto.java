package com.i4o.dms.itldis.spares.salesorder.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SpareSearchSalesOrderDto {

    private Long salesOrderId;

    private String customerName;

    private String customerType;

    private String orderStatus;

    private String orderFromDate;

    private String orderToDate;

    @NotNull(message = "page is mandatory")
    private Integer page;

    @NotNull(message = "size is mandatory")
    private Integer size;

}
