package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SearchDto  {
    private String activityNumber=null;

    private String activityType=null;

    private String activityStatus=null;

    private String fromDate=null;

    private String toDate=null;
    
    private Long hierId;

    private Integer page=0;

    private Integer size=0;
}
