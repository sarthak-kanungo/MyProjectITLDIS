package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ADM_MENU_ROLE_DTL")
public class RoleFunctionalityMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleMaster roleMaster;

    @ManyToOne
    @JoinColumn(name = "function_id")
    private FunctionalityMaster functionalityMaster;

//    private Long assignedBy;
}
