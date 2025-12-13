package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class PoApproval {
	
	private Boolean isSelect; //added by mahesh.kumar on 13-12-2023
    private Long purchaseOrderId;
    private Long userId;
    private String approvalFlag;
    private String remark;
    private List<PoApprovalMachineDetails> machineDetails;
    
    private Double totalOs;

    private Double currentOs;

    private Double os0To30Days;

    private Double os31To60Days;

    private Double os61To90Days;

    private Double os90Days;

    private Double paymentPending;

    private Double netOs;

    private Double orderUnderProcess;

    private Double pendingOrder;

    private Double exposureAmount;
    
    private Double basicAmount;

    private Double totalGstAmount;

    private Double totalAmount;
    
    private Double channelFinanceAvailable;

    private Double totalCreditLimit;

    private Double availableLimit;
    
    private Boolean isApprovalRequired = false;
}
