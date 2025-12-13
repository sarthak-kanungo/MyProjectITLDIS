package com.i4o.dms.kubota.accpac.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class DmsAccpacSalesPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accpacOrderNo;
    private Date accpacOrderDate;

    private String branchCode;
    private String branchName;

    private String poNumber;
    private Date poDate;
    private String dealerCode;
    private String poType;
    private String depot;

    private Double paymentPending;
    private Double totalOs;
    private Double totalCreditLimit;

    @NotBlank(message = "PO status can`t be blank")
    @NotNull
    @Column(length = 50)
    private String poStatus;

    @NotNull
    @Column(length = 15)
    private double basicAmount;

    //@NotBlank(message = "Gst Amount can`t be blank")
    @NotNull
    @Column(length = 15)
    private double totalGstAmount;
    //@NotBlank(message = "Total Amount Amount can`t be blank")

    @NotNull
    @Column(length = 15)
    private double totalAmount;

    private Double availableLimit;
    private Double os90Days;
    private Double os61To90Days;
    private Double os31To60Days;
    private Double os0To30Days;
    private Double netOs;
    private Double currentOs;

    private Double channelFinanceAvailable;

    @OneToMany(mappedBy = "dmsAccpacSalesPo",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DmsAccpacSalesPoItem> dmsAccpacSalesPoItems;


}


