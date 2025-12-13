package com.i4o.dms.itldis.salesandpresales.enquiry.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReport;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "SA_ENQ_ATTACHMENTS_IMAGE")
public class EnquiryAttachmentsImages {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

     @NotNull
     @Column(length = 300)
     private String fileName;
     
     @ManyToOne
     @JoinColumn(name = "Enq_id")
     private Enquiry enquiry;
}
