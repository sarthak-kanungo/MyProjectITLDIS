package com.i4o.dms.itldis.eamg.servicebulletin.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Service Bulletin Response DTO
 */
@Data
public class ServiceBulletinResponseDto {
    private String status;
    private String message;
    private String bulletinNo;
    private List<String> yearList;
    private List<Map<String, Object>> bulletinList;
    private Map<String, Object> bulletinDetails;
    private Date issueDate;
}
