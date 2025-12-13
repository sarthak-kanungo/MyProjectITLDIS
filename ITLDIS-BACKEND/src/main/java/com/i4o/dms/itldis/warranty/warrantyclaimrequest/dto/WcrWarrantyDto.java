package com.i4o.dms.itldis.warranty.warrantyclaimrequest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillViewDto;

@Getter
@Setter
public class WcrWarrantyDto {
    PcrWarrantyClaimDto pcrWarrantyClaimDto;
    GoodwillViewDto goodwillViewDto;
    WarrantyWcrView warrantyWcrView;
    List<Map<String,Object>> labourCharge;
    List<Map<String,Object>> outSideLabourCharge;
    List<Map<String,Object>> warrantyPart;
    List<Map<String,Object>> approvalHierDetails;
    Boolean isShowInspectionBtn;
    Map<String,Boolean> invoiceType;
}
