package com.i4o.dms.kubota.spares.invoice.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpareInvoiceSearch {

    public Long salesInvoiceId;

    public String customerCode;

    public String customerName;

    public String referenceDocument;

    public String customerType;

    public String salesType;

    public String modeOfTransport;

    public String transporter;
    
    public String fromDate;
    
    public String toDate;

    private Integer page;

    private Integer size;
    
    private String wcrNo;
    
    private String jobCardNumber;
}
