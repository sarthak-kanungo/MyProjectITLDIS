package com.i4o.dms.itldis.warranty.goodwill.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class GoodwillViewResponseDto {

    GoodwillViewDto goodwillViewDto;
    List<Map<String,Object>> warrantyPart;
    List<Map<String,Object>> labourCharge;
    List<Map<String,Object>> outSideLabourCharge;
    
    List<Map<String,Object>> approvalDetails;
}
