package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "SM_activity_report_enquiry_detail")
@JsonIgnoreProperties(value = {"enquiry_id"}, allowSetters = true)
public class ActivityReportEnquiryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityReportEnquiryId;

    @ManyToOne
    @JoinColumn(name = "marketing_activity_report")
    @JsonBackReference(value = "marketing_activity_report")
    private MarketingActivityReport marketingActivityReportId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tentativeDateOfPurchase;

    @Column
    Boolean isContacted;

    @ManyToOne
    @JoinColumn(name = "enquiry_id")
    @JsonBackReference
    private Enquiry enquiry;


}
