package com.i4o.dms.itldis.service.machinereinstallation.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.i4o.dms.itldis.masters.service.reinstallation.domain.ServiceMtReinstallationCheckpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class RiChassisCheckpointId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6997286311033755364L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtReinstallationCheckpoint serviceMtReinstallationCheckpoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_reinstallation_id")
    private ServiceMachineReinstallation serviceMachineReinstallation;

    public RiChassisCheckpointId(ServiceMtReinstallationCheckpoint serviceMtReinstallationCheckpoint,
                                 ServiceMachineReinstallation serviceMachineReinstallation) {
        this.serviceMtReinstallationCheckpoint = serviceMtReinstallationCheckpoint;
        this.serviceMachineReinstallation = serviceMachineReinstallation;
    }
}
