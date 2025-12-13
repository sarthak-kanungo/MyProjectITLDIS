package com.i4o.dms.kubota.masters.usermanagement.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ADM_MENU_ROLE_USER_HDR")
public class UserFunctionMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable=false)
    private Long roleId;
    @Column(updatable=false)
    private Long loginUserId;
    @Column(updatable=false)
    private Date createdDate;
    @Column(updatable=false)
    private Long createdBy;
    
    private Character isactive;
    
    private Date lastModifiedDate;
    
    private Long lastModifiedBy;
    
}
