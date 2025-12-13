package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MachineTransferSearchDto {

    String  requestNumber;
    String requestStatus;
    String product;

    String series;
    String model;
    String subModel;
    String variant;
    String itemNumber;
    String requestFromDate;
    String requestToDate;
    Integer size;
    Integer page;

}
