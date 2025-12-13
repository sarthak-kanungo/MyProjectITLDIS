package com.i4o.dms.itldis.warranty.kaiinspectionsheet.dto;

import com.i4o.dms.itldis.warranty.deliverychallan.dto.WarrantyDeliveryChallanViewDto;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.domain.WarrantyWcr;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto.WarrantyWcrView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WcrDCForInspetionSheetDto {


    private WarrantyWcrView warrantyWcrView;
    List<Map<String,Object>> labourCharge;
    List<Map<String,Object>> outsideCharge;

}
