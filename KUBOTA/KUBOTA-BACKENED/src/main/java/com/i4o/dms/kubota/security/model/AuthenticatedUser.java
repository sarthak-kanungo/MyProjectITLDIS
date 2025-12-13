package com.i4o.dms.kubota.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUser {
    private Long id;
    private String username;
    private String password;
    private Long dealerEmployeeId;
    private Long dealerId;
    private Long kubotaEmployeeId;
    private Long branchId;
    private Boolean managementAccess = false;
    private String loginIdStatus;
    private String mobileNo;
    private String emailId;
    
}
