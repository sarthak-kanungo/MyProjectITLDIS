package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class UserFunctionalityPermissionMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "login_user_id")
    private LoginUser loginUser;

    @ManyToOne
    @JoinColumn(name = "functionality_permission_mapping_id")
    private FunctionalityPermissionMapping functionalityPermissionMapping;

    @ManyToOne
    @JoinColumn(name = "user_functionality_mapping_id")
    private UserFunctionMapping userFunctionMapping;

}
