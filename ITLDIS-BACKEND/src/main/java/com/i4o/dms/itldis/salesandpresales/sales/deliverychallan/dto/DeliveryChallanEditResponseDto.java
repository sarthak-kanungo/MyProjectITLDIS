package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryChallanEditResponseDto {

    public DeliveryChallanEditResponse deliveryChallanEditResponse;

    public Map<String, Object> allotmentResponse;
    
    public List<Map<String,Object>> approvalHier;
}
