package com.i4o.dms.itldis.service.activityreport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "sv_activity_job_card_report")
public class ServiceActivityReportJobCardDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double labourCharges;

    private Double sparePartSale;

    private Double lubricants;

    @ManyToOne
    @JoinColumn(name = "service_activity_report_id")
    @JsonBackReference(value = "b")
    private ServiceActivityReport serviceActivityReport;

    @ManyToOne
    @JoinColumn(name = "service_job_card_id")
    private ServiceJobCard serviceJobCard;

}
