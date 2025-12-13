package com.i4o.dms.kubota.masters.service.pdc.domain;

import com.i4o.dms.kubota.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class ServiceMtPdcModelAggregateCheckpointMapping
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelMaster model;

    @ManyToOne
    @JoinColumn(name = "aggregate_id")
    private ServiceMtPdcAggregate pdcAggregate;

    @Size(max = 3)
    @NotBlank(message = "Aggregate sequence no can't be blank")
    private Integer aggregateSequenceNo;

    @ManyToOne
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtPdcCheckpoint pdcCheckpoint;

    @Size(max = 3)
    @NotBlank(message = "Checkpoint sequence no can't be blank")
    private Integer checkpointSequenceNo;

    @Size(max = 1)
    @NotBlank(message = "active status can't be blank")
    private String activeStatus;
}
