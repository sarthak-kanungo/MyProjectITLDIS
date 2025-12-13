package com.i4o.dms.itldis.warranty.goodwill.dto;

import java.util.List;

import com.i4o.dms.itldis.warranty.pcr.dto.PcrApprovalLabourDto;
import com.i4o.dms.itldis.warranty.pcr.dto.PcrApprovalOutsideChargeDto;
import com.i4o.dms.itldis.warranty.pcr.dto.PcrApprovalPartsDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodwillApprovalDto {	
    private String kaiRemarks;
    private Long warrantyGwlId;
    private String approvalStatus;
    private Double budgetConsumed; 
    
    private List<PcrApprovalPartsDto> approvalParts;
    private List<PcrApprovalLabourDto> approvalLabours;
    private List<PcrApprovalOutsideChargeDto> approvalOutsideCharges;
}

