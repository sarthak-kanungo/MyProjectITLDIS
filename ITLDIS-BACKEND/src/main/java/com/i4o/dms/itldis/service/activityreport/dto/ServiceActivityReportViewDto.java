package com.i4o.dms.itldis.service.activityreport.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ServiceActivityReportViewDto
{
    private Map<String,Object> activityReportHeaderData;

    private List<Map<String,Object>> activityMachineReport;

    private List<Map<String,Object>> activityServiceReport;

    private List<Map<String,Object>> activityJobCardReport;

    private List<Map<String,Object>> activityReportPhotoList;

}
