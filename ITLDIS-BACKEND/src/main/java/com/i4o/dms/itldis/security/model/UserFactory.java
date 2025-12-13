package com.i4o.dms.itldis.security.model;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 */
public class UserFactory {
    public static SecurityUser create(AuthenticatedUser user) {
        return new SecurityUser(user.getId(), user.getPassword(), user.getUsername(),
                user.getDealerEmployeeId(), user.getDealerId(),
                user.getKubotaEmployeeId(),
                user.getBranchId(),
                user.getManagementAccess(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(user.getLoginIdStatus())
        );
    }
}
