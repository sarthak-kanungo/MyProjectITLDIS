package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public interface MarketingActivityClaimViewDto {


    Long getClaimId();

    String grtClaimNumber();

    String getClaimStatus();

    int getTotalEnquiries();

    int getWarmEnquiries();

    int getHotEnquiries();

    int getColdEnquiries();

    Double getCostPerEnquiry();

    Double getCostPerHotEnquiry();

    int getTotalBookings();

    Double getCostPerBooking();

    int getTotalRetails();

    Double getCostPerRetail();

    String getActivityEffectiveness();

    String getGstInvoiceImage();

    Double getGstPercent();

    Double getGstAmount();

    List<ActivityHead> activityHeads();

    interface ActivityHead {
        Long getId();

        String getHeadName();

        Double getApprovedAmount();

        Double getAmount();

        Double getActualClaimAmount();

        Double getApprovedClaimAmount();

    }

    Double getTotalApprovedAmount();

    Double getTotalActualClaimAmount();

    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getClaimDate();


    interface MarketingActivityReportImagesDto{
        Long getId();
        String getPhotoPath();
    }

}
