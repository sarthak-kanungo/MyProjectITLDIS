package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto;


import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;

public interface MarketingActivityDetailsForClaimDto {

    //Rajan has missed to add
    String getClaimNumber();

    String getActivityCreationDate();

    String getActivityType();

    int getNumberOfDays();

    String getFromDate();

    String getToDate();

    String getActualFromDate();

    String getActualToDate();

    String getLocation();

    Integer getTotalEnquiries();

    int getHotEnquiry();

    int getWarmEnquiry();

    int getColdEnquiry();

    int getTotalBookings();

    int getTotalRetails();

    String getClaimDate();


//Not added by Rajan
    Double getCostPerEnquiry();

    Double getCostPerHotEnquiry();

    Double getCostPerBooking();

    Double getCostPerRetail();

    MarketingActivityProposal getMarketingActivityProposal();

    interface MarketingActivityProposal{
        String getActivityNumber();
    }


}
