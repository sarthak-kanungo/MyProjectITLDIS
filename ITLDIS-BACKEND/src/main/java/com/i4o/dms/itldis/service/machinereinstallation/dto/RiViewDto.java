package com.i4o.dms.itldis.service.machinereinstallation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RiViewDto {

    private RiViewHeaderResponse riViewHeaderData;

    private List<Map<String,Object>> reInstallationCheckpointList;

    private List<Map<String,Object>> reInstallationMachineDetailsList;

    private List<Map<String,Object>> reInstallationRepresentativeDetailsList;

}
