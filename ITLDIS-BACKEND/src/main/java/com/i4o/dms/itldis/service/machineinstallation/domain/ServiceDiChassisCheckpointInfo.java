package com.i4o.dms.itldis.service.machineinstallation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="SV_INSTALLATION_CHASSIS_CHECKPOINT_INFO")
public class ServiceDiChassisCheckpointInfo {

    @EmbeddedId
    private DiChassisCheckpointInfo diChassisCheckpointInfo;

    @Size(max = 200)
    private String remarks;

    private String observedSpecification;
    
    private Integer aggregateSequenceNo;
    
    private Integer checkpointSequenceNo;

    private Boolean okFlag;
}
