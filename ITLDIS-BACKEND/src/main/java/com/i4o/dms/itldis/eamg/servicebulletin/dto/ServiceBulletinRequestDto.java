package com.i4o.dms.itldis.eamg.servicebulletin.dto;

import lombok.Data;
import java.util.Date;
import java.util.Map;

/**
 * Service Bulletin Request DTO
 */
@Data
public class ServiceBulletinRequestDto {
    private String bulletinNo;
    private String type;
    private String yearOfIssue;
    private Date issueDate;
    private String subject;
    private String description;
    private Map<String, Object> additionalData;
}
