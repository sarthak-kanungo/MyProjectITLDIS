package com.i4o.dms.itldis.service.activityreport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.service.jobcard.domain.ServiceMtServiceTypeInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="sv_activity_sv_report")
public class ServiceActivityReportServiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_type_id")
    private ServiceMtServiceTypeInfo serviceMtServiceTypeInfo;

    private Integer noOfMachine;

    @ManyToOne
    @JoinColumn(name = "service_activity_report_id")
    @JsonBackReference(value = "a")
    private ServiceActivityReport serviceActivityReport;
}


