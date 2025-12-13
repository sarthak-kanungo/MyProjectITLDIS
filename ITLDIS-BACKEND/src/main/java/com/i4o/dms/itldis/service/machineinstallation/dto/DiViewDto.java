package com.i4o.dms.itldis.service.machineinstallation.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class DiViewDto {

    private DiViewHeaderResponse installationViewHeaderData;

    private List<Map<String,Object>> installationCheckpointList;

    private List<Map<String,Object>> machineInstallationPhotoList;


}

