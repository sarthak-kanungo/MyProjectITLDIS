/*
package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;

import java.util.Date;
import java.util.List;

public interface ActivityProposalViewDto {

    Long getActivityProposalId();

    String getActivityNumber();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getActivityCreationDate();

    String getActivityPurpose();

    String getActivityType();

    String getLocation();

    String getActivityStatus();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getFromDate();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getToDate();

    int getNumberOfDays();

    double getProposedBudget();

    double getMaxAllowedBudget();

    List<ActivityHead> getActivityHeads();

    interface ActivityHead {

        Long getId();

        String getHeadName();

        double getAmount();

        boolean getIsDelete();

    }



    List<ConversionTypeActivityProposal> getConversionTypeActivityProposals();

    DealerMaster getDealerMaster();

    interface DealerMaster{
        String getDealerCode();
        String getDealerName();
        String getState();
    }

    interface ConversionTypeActivityProposal {

        Long getId();


    }

    interface  EnquirySourceMaster
    {
        Long getId();
    }

    interface  ActivityTypeBudgetMaster
    {
        Long getId();
    }

    List<Enquiry> getEnquiries();

    interface Enquiry {
        Long getId();

        String getEnquiryNumber();

        String getEnquiryStatus();

        String getEnquiryDate();

        String getFirstName();

        String getMobileNumber();

        String getCity();

        String getTehsil();

        String getModel();

        String getEnquiryType();


    }
}
*/
