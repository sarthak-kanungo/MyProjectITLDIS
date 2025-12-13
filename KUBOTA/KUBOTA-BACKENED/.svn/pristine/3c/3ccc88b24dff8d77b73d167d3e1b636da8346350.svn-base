package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"reportId","activityNumber","activityCreationDate","activityType","activityStatus",
        "fromDate","toDate","actualFromDate","actualToDate","totalEnquiries","totalRetails","totalBookings"})
@JsonIgnoreProperties("totalCount")
public interface SearchResponseMarketingActivityReportDto {

    @JsonProperty("id")
    Long getReportId();

    String getActivityNumber();

    String getActivityCreationDate();

    String getActivityType();

    String getActivityStatus();

    String getFromDate();

    String getToDate();

    String getActualFromDate();

    String getActualToDate();

    Integer getTotalEnquiries();

    Integer getTotalRetails();

    Integer getTotalBookings();
    
    Long getTotalCount();
}
