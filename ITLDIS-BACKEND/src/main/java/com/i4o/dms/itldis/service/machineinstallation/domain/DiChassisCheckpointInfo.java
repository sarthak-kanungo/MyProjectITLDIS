package com.i4o.dms.itldis.service.machineinstallation.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.i4o.dms.itldis.masters.service.machineinstallation.deliveryInstallation.domain.ServiceMtDeliveryInstallationCheckpoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class DiChassisCheckpointInfo implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = -4311140605304866217L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtDeliveryInstallationCheckpoint serviceMtDeliveryInstallationCheckpoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_di_id")
    private ServiceMachineInstallation serviceMachineInstallation;

    public DiChassisCheckpointInfo(ServiceMtDeliveryInstallationCheckpoint serviceMtDeliveryInstallationCheckpoint,
                                   ServiceMachineInstallation serviceMachineInstallation) {
        this.serviceMtDeliveryInstallationCheckpoint = serviceMtDeliveryInstallationCheckpoint;
        this.serviceMachineInstallation = serviceMachineInstallation;
    }
}

