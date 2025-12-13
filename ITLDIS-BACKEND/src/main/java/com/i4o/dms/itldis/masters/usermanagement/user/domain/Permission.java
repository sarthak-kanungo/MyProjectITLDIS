package com.i4o.dms.itldis.masters.usermanagement.user.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String permission;

    @Transient
    private Boolean checkedFlag = false;
}
