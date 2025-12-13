package com.i4o.dms.itldis.service.mrc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchServiceMrc {


    private String mrcNo=null;
    
    private String invoiceNo=null;

    private String mrcFromDate=null;

    private String mrcToDate=null;
    
    private String machineNo;
    
    private String invoiceFromDate=null;

    private String invoiceToDate=null;

    private Integer page=0;

    private Integer size=10;
    
    public Long orgId;

}
