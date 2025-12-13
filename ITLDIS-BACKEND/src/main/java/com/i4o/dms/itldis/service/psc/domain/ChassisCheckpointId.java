package com.i4o.dms.itldis.service.psc.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.i4o.dms.itldis.masters.service.domain.ServiceMtCheckPoint;

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
    @JoinColumn(name = "service_psc_id")
    private ServicePsc servicePsc;

    public ChassisCheckpointId(ServiceMtCheckPoint serviceMtCheckPoint, ServicePsc servicePsc) {
        this.serviceMtCheckPoint = serviceMtCheckPoint;
        this.servicePsc = servicePsc;
    }
}
