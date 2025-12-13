package com.i4o.dms.kubota.service.machineinstallation.dto;

public interface DiViewHeaderResponse {

    Long getId();
    String getChassisNo();
    Long getChassisId();
    String getInstallationType();
    String getInstallationDate();
    String getInstallationNumber();
    String getEngineNo();
    String getModel();
    String getServiceStaffName();
    Long getServiceStaffNameId();
    String getCustomerRepName();
    String getCustomerName();
    String getRepresentativeType();
    Long getInstallationTypeId();
    String getCsbNumber();
}
