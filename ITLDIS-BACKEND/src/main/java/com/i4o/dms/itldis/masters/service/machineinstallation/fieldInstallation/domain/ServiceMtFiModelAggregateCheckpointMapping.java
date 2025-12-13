package com.i4o.dms.itldis.masters.service.machineinstallation.fieldInstallation.domain;


import com.i4o.dms.itldis.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity

public class ServiceMtFiModelAggregateCheckpointMapping {

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
    @JoinColumn(name = "fi_aggregate_id")
    private ServiceMtFieldInstallationAggregate fieldInstallationAggregate;

    @ManyToOne
    @JoinColumn(name = "fi_checkpoint_id")
    private ServiceMtFieldInstallationCheckpoint fieldInstallationCheckPoint;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelMaster model;

}
