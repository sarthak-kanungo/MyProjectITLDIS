package com.i4o.dms.itldis.masters.service.pdi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class ServiceMtPdiCheckpoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String activeStatus;

    @NotBlank(message = "checkpoints cannot blank")
    private String pdiCheckpoint;

    private String defaultTick;

    @NotBlank(message = "fieldType cannot blank")
    @Size(max = 20)
    private String fieldType;

    @Size(max =100)
    private String specification;

}
