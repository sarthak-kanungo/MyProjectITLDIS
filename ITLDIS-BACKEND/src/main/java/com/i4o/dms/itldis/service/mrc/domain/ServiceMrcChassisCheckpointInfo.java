package com.i4o.dms.itldis.service.mrc.domain;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SV_MRC_CHASSIS_CHECKPOINT_INFO")
public class ServiceMrcChassisCheckpointInfo {

    @EmbeddedId
    private ServiceMrcChassisCheckpointId serviceMrcChassisCheckpointId;

    private String observedSpecification;
    
    private Integer aggregateSequenceNo;
    
    private Integer checkpointSequenceNo;

    @Column(length = 300)
    private String remarks;

    @NotNull(message = "ok flag  can not be null")
    private Boolean okFlag;
  }
