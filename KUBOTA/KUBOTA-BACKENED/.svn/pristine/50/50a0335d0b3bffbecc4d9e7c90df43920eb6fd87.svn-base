package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ADM_MENU_MST")

public class FunctionalityMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long parentId;

//    private Long level;

    private String module;

    private Boolean roleActiveStatus=false;

//    private Boolean roleDeleteStatus = false;

    private String funcLevel;

    private Boolean applicableToKubota = false;

    private Boolean applicableToDealer = false;

    private Integer seqNo;

    private String routerLink;

    @Transient
    private Boolean accessibleFlag ;

    private Boolean ispermission;
    
    private String menucode;

    private String menuname;

}
