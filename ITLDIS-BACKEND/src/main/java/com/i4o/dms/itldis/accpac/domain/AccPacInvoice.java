package com.i4o.dms.itldis.accpac.domain;

import com.fasterxml.jackson.annotation.*;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate", "createdBy", "lastModifiedBy"}, allowSetters = true)
public class AccPacInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;
    private String billTo;
    private String shipTo;
    private String lrNo;
    private Boolean grnDoneFlag;
    private Boolean mrcDoneFlag = false;
    private Double invoiceTotalValue;
    private Double additionalAmount;
    private Double additionalCgstAmount;
    private Double additionalSgstAmount;
    private Double additionalIgstAmount;
    private Double totalAdditionalAmount;

    /*@OneToMany(mappedBy = "accPacInvoice",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AccPacInvoicePartDetails> accPacInvoicePartDetails;
    */
    private String purchaseOrderNo;

    private String dealerCode;

    private Date syncDate;

//    @ManyToOne
//    @JoinColumn(name="dealer_id")
//    private DealerMaster dealerMaster;
}
