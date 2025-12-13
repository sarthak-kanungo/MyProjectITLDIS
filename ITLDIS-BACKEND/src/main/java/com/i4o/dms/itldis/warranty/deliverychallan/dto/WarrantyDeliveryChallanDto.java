package com.i4o.dms.itldis.warranty.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class WarrantyDeliveryChallanDto {
    WarrantyDeliveryChallanViewDto warrantyDeliveryChallanViewDto;
    List<Map<String,Object>> warrantyPart;
    Map<String,Object> dealerMaster;
}
