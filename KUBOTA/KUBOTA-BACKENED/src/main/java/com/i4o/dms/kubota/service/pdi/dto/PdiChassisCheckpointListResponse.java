package com.i4o.dms.kubota.service.pdi.dto;


public interface PdiChassisCheckpointListResponse {

    Long getId();

    String getObservedSpecification();

    String getPdiCheckpointId();

    String getRemarks();

    String getServicePdiId();

}
