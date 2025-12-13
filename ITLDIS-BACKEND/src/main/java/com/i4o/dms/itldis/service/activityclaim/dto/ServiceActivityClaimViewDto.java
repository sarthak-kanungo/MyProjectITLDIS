package com.i4o.dms.itldis.service.activityclaim.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ServiceActivityClaimViewDto
{
    private Map<String,Object> activityClaimHeaderData;

    private List<Map<String,Object>> activityClaimHeads;

    private List<Map<String,Object>> subActivities;

    private List<Map<String,Object>> reportPhotos;

    private List<Map<String,Object>> approvalDetails;

}
