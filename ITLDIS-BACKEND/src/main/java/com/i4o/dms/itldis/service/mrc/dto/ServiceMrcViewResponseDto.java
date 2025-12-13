package com.i4o.dms.itldis.service.mrc.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ServiceMrcViewResponseDto {

    private Map<String,Object> mrcHeaderData;

    private List<Map<String,Object>> mrcCheckpointList;

    private List<Map<String,Object>> mrcPhotoList;

    private List<Map<String,Object>> mrcDiscrepancyList;
}
