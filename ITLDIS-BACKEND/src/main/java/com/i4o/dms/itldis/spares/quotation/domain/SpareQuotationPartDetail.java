package com.i4o.dms.itldis.spares.quotation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "SP_QUOTATION_PART_DETAIL")
public class SpareQuotationPartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull(message = "Item Number is mandatory")
//    @Column(length = 50)
//    private String itemNumber;
//
//    @Column(length = 100)
//    private String itemDescription;

    private String hsnCode;

    private Double unitPrice;

    private Integer quantity;

    private Double amount;

    @Max(value = 100,message = "Discount not more than 100")
    private Double discountPercent;

    private Double discountAmount;

    private Double netAmount;

    private Double cgstPercent;

    private Double cgstAmount;

    private Double sgstPercent;

    private Double sgstAmount;

    private Double igstpercent;

    private Double igstAmount;

    private Double subTotal;

    private Double gstAmount;

    private Double totalInvoiceAmount;

    @ManyToOne
    @JoinColumn(name = "quotation_id")
    @JsonBackReference
    @NotNull(message = "Spare Quotation Id is mandatory")
    private SpareQuotation spareQuotation;

    @ManyToOne
    @JoinColumn(name = "spare_part_master_id",referencedColumnName = "itemNo")
    @NotNull(message = "Spare part Master is mandatory")
    private SparePartMaster sparePartMaster;
}
