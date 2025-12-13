package com.i4o.dms.kubota.service.pdi.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.i4o.dms.kubota.masters.service.domain.ServiceMtCheckPoint;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class ChassisCheckpointId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 974641908295206125L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtCheckPoint serviceMtCheckPoint;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_pdi_id")
    private ServicePdi servicePdi;

    public ChassisCheckpointId(ServiceMtCheckPoint serviceMtCheckPoint, ServicePdi servicePdi) {
        this.serviceMtCheckPoint = serviceMtCheckPoint;
        this.servicePdi = servicePdi;
    }
}
