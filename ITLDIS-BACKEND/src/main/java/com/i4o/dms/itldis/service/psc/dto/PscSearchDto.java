package com.i4o.dms.itldis.service.psc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PscSearchDto {
    private String chassisNo;

    private String pscNo;

    private String fromDate;

    private String toDate;

    public Integer dealerId;

    public Integer dealerEmployeeId;

    public Integer kubotaEmployeeId;

    public Boolean managementAccess;

    private Integer page;

    private Integer size;
    
    public Long orgId;
}
