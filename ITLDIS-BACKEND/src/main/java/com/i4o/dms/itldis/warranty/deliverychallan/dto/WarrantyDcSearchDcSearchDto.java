package com.i4o.dms.itldis.warranty.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class WarrantyDcSearchDcSearchDto {
    String dcNo;
    String wcrNo;
    String dcFromDate;
    String dcToDate;
    String machineModel;
    String wcrFromDate;
    String wcrToDate;
    Long page;
    Long size;
}
