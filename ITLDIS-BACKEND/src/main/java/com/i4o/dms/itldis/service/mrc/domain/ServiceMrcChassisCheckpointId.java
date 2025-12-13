package com.i4o.dms.itldis.service.mrc.domain;

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
public class ServiceMrcChassisCheckpointId implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1312335226999215783L;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtCheckPoint serviceMtCheckPoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_mrc_id")
    private ServiceMrc serviceMrc;

    public ServiceMrcChassisCheckpointId(ServiceMtCheckPoint serviceMtMrcCheckPoint, ServiceMrc serviceMrc) {
        this.serviceMtCheckPoint = serviceMtMrcCheckPoint;
        this.serviceMrc = serviceMrc;
    }
}
