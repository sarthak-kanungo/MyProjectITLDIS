package com.i4o.dms.kubota.service.machinereinstallation.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RiSearchDto {

    private String series;

    private String reInstallationNo;

    private String serviceStaffName;

    private String fromDate;

    private String toDate;

    public Integer dealerId;

    public Integer dealerEmployeeId;

    public Integer kubotaEmployeeId;

    public Boolean managementAccess;

    private Integer page;

    private Integer size;
}
