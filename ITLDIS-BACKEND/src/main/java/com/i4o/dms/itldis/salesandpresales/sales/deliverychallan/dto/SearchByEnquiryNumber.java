package com.i4o.dms.itldis.salesandpresales.sales.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchByEnquiryNumber {


    public  EnquiryProspectDetailsResponse enquiryProspectDetailsResponse;

    public List<MachineDetailsResponse> machineDetailsResponse;

    public List<DeliverableChecklistResponse> deliverableChecklistResponse;

//    public List<ImplementDetailsResponse> implementDetailsResponses;

}
