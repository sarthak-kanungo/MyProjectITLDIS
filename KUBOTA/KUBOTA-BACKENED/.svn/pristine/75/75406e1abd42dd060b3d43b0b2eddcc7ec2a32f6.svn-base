package com.i4o.dms.kubota.masters.service.pdc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class ServiceMtPdcCheckpoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status can't be blank")
    private String activeStatus;

    @NotBlank(message = "checkpoint can't be blank")
    private String pdcCheckpoint;

    private String defaultTick;

    @NotBlank(message = "fieldType can't be blank")
    @Size(max = 20)
    private String fieldType;

    @Size(max =100)
    private String specification;
}
