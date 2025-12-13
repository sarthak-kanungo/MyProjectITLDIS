package com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KaiInspectionSheetSearchDto {
    String inspectionNo;
    String fromDate;
    String toDate;
    Long page;
    Long size;
    String wcrNo;


}
