package com.i4o.dms.itldis.masters.service.reinstallation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
public class ServiceMtReinstallationCheckpoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String activeStatus;

    @NotBlank(message = "reinstallation checkpoints cannot blank")
    private String riCheckpoint;

    private String defaultTick;

    @NotBlank(message = "field Type cannot blank")
    @Size(max = 20)
    private String fieldType;

    @Size(max =100)
    private String specification;

}
