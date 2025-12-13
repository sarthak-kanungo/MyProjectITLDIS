package com.i4o.dms.kubota.salesandpresales.sales.quotation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.domain.Quotation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name = "SA_QUOTATION_IMPLEMENT")
public class QuotationImplements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemNo;
    private String itemDescription;
    private String color;
    private Long qty;
    private Double rate;
    private Double grossDiscount;
    private Double amountAfterDiscount;
    private Double iGst;
    private Double cGst;
    private Double sGst;
    private Double gstAmount;
    private Double totalAmount;
    @Transient
    private Double basicPrice;

    @ManyToOne
    @JsonBackReference(value = "quotationRef")
    private Quotation quotation;

}
