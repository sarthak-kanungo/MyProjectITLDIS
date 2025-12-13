package com.i4o.dms.kubota.accpac.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "accpac_channel_finance_invoice")
public class AccpacChannelFinanceInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String dealerCode;

//    @ManyToOne
//    @JoinColumn(name = "dealer_id")
//    @JsonBackReference
//    private DealerMaster dealerMaster;

//    @NotNull
//    @Column(length = 50)
//    private String bankName;
//
//    @NotNull
//    @Column(length = 50)
//    private String flexiLoanAccountNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date invoiceDate;

    @Column(length = 15)
    private Double invoiceAmount;

    @Column(length = 15,columnDefinition = "double default 0.0")
    private Double totalUtilisedAmount=0.0;

    @Column(length = 50)
    private String invoiceNumber;

    @Column(length = 50)
    private String status="pending";

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date syncDate=new Date();
}
