/*
package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ConversionTypeActivityProposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "marketing_activity_proposal_id")
    @JsonBackReference(value = "conversion_type_activity_proposal")
    private MarketingActivityProposal marketingActivityProposal;

    @ManyToOne
    @JoinColumn(name = "enquiry_id")
    @JsonBackReference
    private Enquiry enquiry;
}
*/
