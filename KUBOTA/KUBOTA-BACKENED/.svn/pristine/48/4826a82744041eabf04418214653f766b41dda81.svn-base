package com.i4o.dms.kubota.service.pdc.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="SV_PDC_CHASSIS_CHECKPOINT_INFO")
public class ServicePdcChassisCheckpointInfo {

    @EmbeddedId
    private ChassisCheckpointId chassisCheckpointId;

    @Size(max=200)
    private String remarks;

    private String observedSpecification;
    
    private Integer aggregateSequenceNo;
    
    private Integer checkpointSequenceNo;

    @NotNull(message = "Ok Flag is mandatory")
    private Boolean okFlag;


}
