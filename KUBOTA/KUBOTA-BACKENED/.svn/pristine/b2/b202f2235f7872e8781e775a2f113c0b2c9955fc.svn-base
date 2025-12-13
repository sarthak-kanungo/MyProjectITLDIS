package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MarketingActivityClaimDetailsByActivityNumberDto {

    private String activityCreationDate;

    private String activityType;

    private Integer numberOfDays;

    private String fromDate;

    private String toDate;

    private String actualFromDate;

    //  Double actualClaimAmount;

    private String claimNumber;

    private String activityNumber;

    private String actualToDate;

    private String location;

    private Integer totalEnquiries;

    private Integer hotEnquiry;

    private Integer warmEnquiry;

    private Integer coldEnquiry;

    private Integer totalBookings;

    private Integer totalRetails;

    //for view
    private String activityEffectiveness;

    private String gstInvoiceImage;

    private Long claimId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date claimDate;

    private String claimStatus;

    private Double costPerEnquiry;

    private Double costPerHotEnquiry;

    private Double costPerBooking;

    private Double costPerRetail;

    private List<MarketingActivityClaimHeadDtoClass> heads;

    private List<MarketingReportImagesClassDto> reportImages;
}
