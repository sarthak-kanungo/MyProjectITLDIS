package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {

    public String activityNumber=null;

    public String activityStatus=null;
    
    public Long activityType = null;

    public String fromDate=null;

    public String toDate=null;

    public Integer page=0;

    public Integer size=10;
    
    public Long dealerId;
    
    public Integer hierId;

    private Long state;
}
