package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class FunctionalityPermissionMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;

    private Boolean applicableToKubota = false;

    private Boolean applicableToDealer = false;

    private Integer seqNo;

    private String routerLink;

    @ManyToOne
    @JoinColumn(name = "functionality_id")
    private FunctionalityMaster functionalityMaster;
}
