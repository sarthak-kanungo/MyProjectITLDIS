package com.i4o.dms.kubota.accpac.domain;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class DmsAccpacSparesPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String purchaseOrderNumber;

    @NotNull
    private Date purchaseOrderDate = new Date();

    @NotBlank(message = "Order Type is Mandatory")
    @Column(length = 100)
    private String orderType;

    @NotNull
    private String freightBorneBy;

    private String transportMode;
    private String transporter;
    @NotNull
    private Double creditLimit;

    @NotNull
    private Double currentOutStanding;
    @NotNull
    private Double overduesOutStanding;
    @NotNull
    private Double paymentUnderProcess;
    @NotNull
    private Double ordersUnderProcess;
    @NotNull
    private Double availableLimit;
    @NotNull
    private Double netAmountPayable;
    private String remarks;

 /*   @OneToMany(mappedBy = "dmsSparePurchaseOrder", cascade = CascadeType.ALL)
    private Set<DmsAccpacSparesPoItem> sparePurchaseOrderPartDetailSet =
            new HashSet();*/

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

    @NotNull
    private String dealerCode;

    @NotNull
    @Column(length = 50)
    private Double totalBaseAmount;

    @NotNull
    @Column(length = 50)
    private Double totalPoAmount;

    @NotNull
    @Size(max=30)
    private String status;

}
