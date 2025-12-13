package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MachineAllotmentDto {

    public List<Map<String,Object>> machineAllotmentDetails;

    public Map<String,Object> enquiryProspectDetailsResponse;
}
