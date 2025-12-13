package com.i4o.dms.itldis.service.pdc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdcSearchDto {
    public String chassisNo;
    public String pdcNo;
    public String model;
    public String pdcFromDate;
    public String pdcToDate;
    public Long orgId;
    public Integer page;
    public Integer size;
}
