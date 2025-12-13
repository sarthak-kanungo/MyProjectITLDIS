package com.i4o.dms.itldis.salesandpresales.enquiry.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EnquiryMobileNoResponse {
    public EnquiryProspectDto enquiryProspectDto;
    public List<EnquirySoilDto> enquirySoilDto;
    public List<EnquiryCropDto> enquiryCropDto;
    public List<EnquiryMachineDto> enquiryMachineDtos;
}
