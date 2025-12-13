package com.i4o.dms.itldis.salesandpresales.grn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.accpac.domain.AccPacInvoicePartDetails;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AccPacInvoiceDto {
    private Long id;
    private String invoiceNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    private String billTo;
    private String shipTo;
    private String lrNo;
    private Double invoiceTotalValue;
    private Double additionalAmount;
    private Double additionalCgstAmount;
    private Double additionalSgstAmount;
    private Double additionalIgstAmount;
    private Double totalAdditionalAmount;
    private List<Map<String,Object>> accPacInvoicePartDetails = new ArrayList<>();

}
