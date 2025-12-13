package com.i4o.dms.itldis.service.psc.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="SV_PSC_CHASSIS_CHECKPOINT_INFO")
public class ServicePscChassisCheckpointInfo {

	@EmbeddedId
    private ChassisCheckpointId chassisCheckpointId;

    @Size(max=200)
    private String remarks;

    @NotNull(message = "Ok Flag is mandatory")
    private Boolean okFlag;

    private String observedSpecification;
    
    private Integer aggregateSequenceNo;
    
    private Integer checkpointSequenceNo;


}

