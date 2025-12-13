package com.i4o.dms.kubota.service.machinereinstallation.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "SV_REINSTALLATION_CHASSIS_CHECKPOINT_INFO")
public class ServiceReinstallationChassisCheckpointInfo {

    @EmbeddedId
    private RiChassisCheckpointId riChassisCheckpointId;

    private String observedSpecification;

    private Boolean okFlag;
    
    private String remarks;
    
    private String checkpointSequenceNo;
    
    private String aggregateSequenceNo;
}
