package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface ReportViewResponse {
    Long getReportId();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getActualFromDate();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getActualToDate();

    int getTotalEnquiries();

    int getTotalRetails();

    int getTotalBookings();

    List<MarketingActivityReportImages> getMarketingActivityReportImages();

    interface MarketingActivityReportImages {
        String getFileName();

        Long getId();
    }


    List<ActivityReportEnquiryDetails> getActivityReportEnquiryDetails();

    interface ActivityReportEnquiryDetails {
        Long getActivityReportEnquiryId();

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date getTentativeDateOfPurchase();

        Boolean getIsContacted();

        Enquiry getEnquiry();

        interface Enquiry {
            String getEnquiryNumber();

            @JsonFormat(pattern = "dd-MM-yyyy")
            Date getEnquiryDate();

            String getFirstName();

            String getLastName();

            String getMobileNumber();

            String getModel();

            String getVariant();

            String getEnquiryStatus();

            String getEnquiryType();

        }
    }

    MarketingActivityProposal getMarketingActivityProposal();

    interface MarketingActivityProposal {
        String getActivityNumber();

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date getActivityCreationDate();

        Integer getActivityType();
        
        String getActivityTypeName();
        
        void setActivityTypeName(String activityType);

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date getFromDate();

        @JsonFormat(pattern = "dd-MM-yyyy")
        Date getToDate();

        String getActivityStatus();

    }
}
