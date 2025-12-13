package com.i4o.dms.kubota.service.machineinstallation.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="SV_INSTALLATION_CHASSIS_CHECKPOINT_INFO")
public class ServiceFiChassisCheckpointInfo {

    @EmbeddedId
    private FiChassisCheckpointInfo fiChassisCheckpointInfo;

    @Size(max = 200)
    private String remarks;

    private String observedSpecification;
    
    private Integer aggregateSequenceNo;
    
    private Integer checkpointSequenceNo;

    private Boolean okFlag;
}
