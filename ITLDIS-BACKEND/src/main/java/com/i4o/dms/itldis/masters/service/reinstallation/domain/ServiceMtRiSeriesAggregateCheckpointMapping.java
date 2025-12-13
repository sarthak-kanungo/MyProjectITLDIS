package com.i4o.dms.itldis.masters.service.reinstallation.domain;

import com.i4o.dms.itldis.masters.products.domain.Series;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity

public class ServiceMtRiSeriesAggregateCheckpointMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 3)
    @NotBlank(message = "Checkpoint sequence no cannot blank")
    private Integer checkpointSequenceNo;

    @Size(max = 3)
    @NotBlank(message = "Aggregate sequence no cannot blank")
    private Integer aggregateSequenceNo;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String activeStatus;

    @ManyToOne
    @JoinColumn(name = "ri_aggregate_id")
    private ServiceMtReinstallationAggregate serviceMtReinstallationAggregate;

    @ManyToOne
    @JoinColumn(name = "ri_checkpoint_id")
    private ServiceMtReinstallationCheckpoint serviceMtReinstallationCheckPoint;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private Series series;

}
