package com.i4o.dms.kubota.security.service;

import com.i4o.dms.kubota.security.model.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationImpl implements UserAuthentication {

    public SecurityUser getSecurityUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public Long getDealerEmployeeId() {
        return getSecurityUser().getDealerEmployeeId();
    }

    @Override
    public String getUsername() {
        return getSecurityUser().getUsername();
    }

    @Override
    public Long getDealerId() {
        return getSecurityUser().getDealerId();
    }

    @Override
    public Long getKubotaEmployeeId() {
        return getSecurityUser().getKubotaEmployeeId();
    }

    @Override
    public Boolean getManagementAccess() {
        return getSecurityUser().getManagementAccess();
    }
    
    public Long getLoginId(){
    	return getSecurityUser().getId();
    }

	@Override
	public Long getBranchId() {
		// TODO Auto-generated method stub
		return getSecurityUser().getBranchId();
	}
}
