package com.i4o.dms.kubota.masters.service.machineinstallation.deliveryInstallation.domain;

import com.i4o.dms.kubota.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter

public class ServiceMtDiModelAggregateCheckpointMapping {

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
    @JoinColumn(name = "di_aggregate_id")
    private ServiceMtDeliveryInstallationAggregate deliveryInstallationAggregate;

    @ManyToOne
    @JoinColumn(name = "di_checkpoint_id")
    private ServiceMtDeliveryInstallationCheckpoint deliveryInstallationCheckpoint;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelMaster model;

 }

