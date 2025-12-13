package com.i4o.dms.kubota.service.pdc.domain;

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
	private static final long serialVersionUID = 6595200078278443002L;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtCheckPoint serviceMtCheckPoint;
	
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_pdc_id")
    private ServicePdc servicePdc;

    public ChassisCheckpointId(ServiceMtCheckPoint serviceMtCheckPoint,ServicePdc servicePdc) {
        this.serviceMtCheckPoint = serviceMtCheckPoint;
        this.servicePdc = servicePdc;
    }
}
