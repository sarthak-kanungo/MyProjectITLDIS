package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ResponseInvoiceDto {
    private Long id;
    //private DealerMaster dealerCode;
    private String flexiLoanAccountNumber;
    private String invoiceNumber;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;
    private Double invoiceAmount;
    private Double utilisedAmount=0.0;
    private Double totalUtilisedAmount=0.0;
    private String status;
    private String ageing;
    private String channelFinanceAmount;
}
