package com.i4o.dms.itldis.warranty.pcr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PcrViewDto {

    WarrantyPcrViewDto warrantyPcrViewDto;
    List<Map<String,Object>> labourCharge;
    List<Map<String,Object>> outSideLabourCharge;
    List<Map<String,Object>> warrantyPart;
    List<Map<String,Object>> approvalDetails;
    Map<String,Object> goodwillEnable;
    List<Map<String,Object>> files;
    Map<String, Object> warrantyPeriod;
}
