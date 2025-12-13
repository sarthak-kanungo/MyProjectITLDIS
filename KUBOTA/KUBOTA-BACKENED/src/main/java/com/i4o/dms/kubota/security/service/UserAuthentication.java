package com.i4o.dms.kubota.security.service;

public interface UserAuthentication {
    Long getDealerEmployeeId();

    String getUsername();

    Long getDealerId();
    
    Long getBranchId();

    Long getKubotaEmployeeId();

    Boolean getManagementAccess();
    
    Long getLoginId();
}
