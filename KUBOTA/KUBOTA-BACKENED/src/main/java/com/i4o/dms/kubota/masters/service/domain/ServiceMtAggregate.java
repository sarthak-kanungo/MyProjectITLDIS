package com.i4o.dms.kubota.masters.service.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="SV_MT_CHECKLIST_AGGREGATE")
public class ServiceMtAggregate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aggregateId;

    @NotBlank(message = "aggregate can't be null")
    private String aggregate;

    @NotNull
    @NotBlank(message = "active status can't be blank")
    private String activeStatus;

    @OneToMany(mappedBy = "serviceMrcAggregate",cascade = CascadeType.ALL)
    private List<ServiceMtCheckPoint> serviceMtMrcCheckPoint;
}
