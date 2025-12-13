package com.i4o.dms.itldis.masters.usermanagement.user.dto;

import java.util.List;

public interface MappedFunctionsResponse {
    String getFunctionality();

    Long getId();

    String getRouterLink();

    Integer getSequenceNo();

    Long getParentId();

    List<MappedFunctionsResponse> getChildren();
}
