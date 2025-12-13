package com.i4o.dms.itldis.salesandpresales.purchase.purchaseOrder.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "ADM_DEALER_BANK_FINANCE_MAP")
public class DealerBankDetails implements Serializable {

    @EmbeddedId
    private DealerBankDetailsId dealerBankDetailsId;

    @NotNull
    @Column(length = 50)
    private String flexiLoanAccountNumber;

    @NotNull
    @Column(length = 50)
    private String category;

    @NotNull
    @Column(length = 15)
    private int numberOfDays;

    @NotNull
    @Column(length = 15)
    private double cfCreditLimit;

    @Column(length = 15)
    private double availableAmount;

    @NotNull
    @Column(length = 15)
    private double utilisedLimit;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(updatable = false)
    private Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastModifiedDate = new Date();

  
}
