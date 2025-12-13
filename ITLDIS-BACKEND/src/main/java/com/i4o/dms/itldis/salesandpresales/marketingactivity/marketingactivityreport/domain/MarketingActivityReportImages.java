package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "SM_activity_report_image")
public class MarketingActivityReportImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private String photoPath;

    @NotNull
    @Column(length = 300)
    private String fileName;


    @ManyToOne
    @JoinColumn(name = "marketing_activity_report_id")
    private MarketingActivityReport marketingActivityReport;

}
