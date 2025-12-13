package com.i4o.dms.itldis.service.pdi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PdiSearchDto {

    public Long pdiId;

    public String pdiFromDate;

    public String pdiToDate;

    public String kaiInvoiceNumber;

    public String dmsGrnNumber;

    public String dmsGrnFromDate;

    public String dmsGrnToDate;

    public Integer dealerId;

    public Integer dealerEmployeeId;

    public Integer kubotaEmployeeId;

    public Boolean managementAccess;

    public Integer page;

    public Integer size;
    
    public Long orgId;

}
