package com.i4o.dms.itldis.masters.service.psc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
public class ServiceMtPscCheckPoint
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String activeStatus;

//    @Column(unique = true)
    @NotBlank(message = "checkpoints cannot blank")
    private String pscCheckpoint;

    private String defaultTick;

    @NotBlank(message = "fieldType cannot blank")
    @Size(max = 20)
    private String fieldType;

    @Size(max =100)
    private String specification;

    @ManyToOne
    @JoinColumn(name = "aggregate_id")
    private ServiceMtPscAggregate serviceMtPscAggregate;

}
