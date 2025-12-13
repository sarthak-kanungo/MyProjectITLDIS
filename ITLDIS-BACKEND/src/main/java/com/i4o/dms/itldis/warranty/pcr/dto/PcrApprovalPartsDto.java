package com.i4o.dms.itldis.warranty.pcr.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PcrApprovalPartsDto {
    private Long id;
    private Integer approvedQuantity;
    private Double gwApprovedPercent;
    private String priceType;
    private FailurePartsDetailsDto failureCode;
}
