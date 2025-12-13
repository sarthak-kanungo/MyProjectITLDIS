package com.i4o.dms.itldis.service.activityreport.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.products.domain.Series;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="sv_activity_machine_report")
public class ServiceActivityReportMachineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="machine_series_id")
    private Series machineSeries;

    private Integer noOfMachine;

    @ManyToOne
    @JoinColumn(name = "service_activity_report_id")
    @JsonBackReference(value = "c")
    private ServiceActivityReport serviceActivityReport;
}
