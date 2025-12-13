package com.i4o.dms.itldis.service.psc.domain;

import com.i4o.dms.itldis.masters.service.psc.domain.ServiceMtPscAggregate;
import com.i4o.dms.itldis.masters.service.psc.domain.ServiceMtPscCheckPoint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class PscChassisCheckpointId implements Serializable {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "aggregate_id")
    private ServiceMtPscAggregate serviceMtPscAggregate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "checkpoint_id")
    private ServiceMtPscCheckPoint serviceMtPscCheckPoint;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "service_psc_id")
    private ServicePsc servicePsc;

    public PscChassisCheckpointId(ServiceMtPscAggregate serviceMtPscAggregate, ServiceMtPscCheckPoint serviceMtPscCheckPoint,ServicePsc servicePsc) {
        this.serviceMtPscAggregate = serviceMtPscAggregate;
        this.serviceMtPscCheckPoint = serviceMtPscCheckPoint;
        this.servicePsc = servicePsc;
    }
}



