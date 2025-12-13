package com.i4o.dms.kubota.masters.profilepicture.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "ADM_USER_PROFILE_IMAGE")
public class UserProfilePicture {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 300)
    private String imageName;
    
    @Column(unique = true, nullable = false)
    private String userCode;

}
