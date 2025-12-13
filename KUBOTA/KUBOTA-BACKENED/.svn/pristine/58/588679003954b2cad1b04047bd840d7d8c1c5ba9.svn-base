package com.i4o.dms.kubota.accpac.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@JsonIgnoreProperties(value = {"createdDate", "lastModifiedDate"}, allowSetters = true)
@Getter
@Setter
@Entity
@Table(name = "accpac_dealer_outstanding")
public class AccpacPoDetails {     //Rename the Entity Class as AccpacDmsDealerOutstanding
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

    private Long dealer_id;
    @Column(length = 15)
    private double totalCreditLimit;

    @Column(length = 15)
    private double availableLimit;

    //@NotBlank(message = "Total Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double totalOs;


    //@NotBlank(message = "Current Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double currentOs;

    //@NotBlank(message = "0To30 Days Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double os0To30Days;

    //@NotBlank(message = "31To60 Days Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double os31To60Days;

    //@NotBlank(message = "61To90 Days Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double os61To90Days;

    //@NotBlank(message = "90 Days Os can`t be blank")
    @NotNull
    @Column(length = 15)
    private double os90Days;

    //    @NotNull(message = "Order under process is mandatory")
    private Double orderUnderProcess;

    //    @NotNull(message = "Pending order is mandatory")
    private Double pendingOrder;

    private Double pendingPayment;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date syncDate;


}
