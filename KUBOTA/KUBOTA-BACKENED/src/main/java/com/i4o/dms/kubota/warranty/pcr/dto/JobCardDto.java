package com.i4o.dms.kubota.warranty.pcr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JobCardDto {

    JobCardViewDto jobCardViewDto;
    List<JobCardPartDto> jobCardPartDto;
    List<Map<String,Object>> labourCharge;
    List<Map<String,Object>> outSideLabourCharge;
    Map<String,Object> warrantyPeriod;
}
