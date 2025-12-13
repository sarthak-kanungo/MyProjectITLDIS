package com.i4o.dms.kubota.masters.service.machineinstallation.fieldInstallation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
public class ServiceMtFieldInstallationAggregate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String activeStatus;

    @Size(max = 100)
    @NotBlank(message = "aggregate type cannot blank")
    private String aggregate;

}

