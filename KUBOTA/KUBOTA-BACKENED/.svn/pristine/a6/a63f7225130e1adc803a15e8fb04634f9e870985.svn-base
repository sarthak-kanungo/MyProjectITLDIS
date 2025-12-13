package com.i4o.dms.kubota.service.machineinstallation.domain;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.i4o.dms.kubota.masters.service.machineinstallation.fieldInstallation.domain.ServiceMtFieldInstallationCheckpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class FiChassisCheckpointInfo  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 190281817976237865L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtFieldInstallationCheckpoint serviceMtFieldInstallationCheckpoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_di_id")
    private ServiceMachineInstallation serviceMachineInstallation;

    public FiChassisCheckpointInfo(ServiceMtFieldInstallationCheckpoint serviceMtFieldInstallationCheckpoint,
                                   ServiceMachineInstallation serviceMachineInstallation) {
        this.serviceMtFieldInstallationCheckpoint = serviceMtFieldInstallationCheckpoint;
        this.serviceMachineInstallation = serviceMachineInstallation;
    }
}
