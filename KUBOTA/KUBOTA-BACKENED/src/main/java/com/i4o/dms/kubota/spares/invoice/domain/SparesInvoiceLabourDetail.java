package com.i4o.dms.kubota.spares.invoice.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SP_SALES_INVOICE_LABOUR_CHARGES")
public class SparesInvoiceLabourDetail {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "spares_invoice_id")
    private SparesInvoice sparesInvoice;
	
	private Long frsCodeId;
	
	private Double hour;
	
	private Double amount;
	
	private String hsnCode;
	
	private Double discountAmount;
	
	private Double discountPercent;
	
	private Double netAmount;
	
	private Double cgstAmount;
	
	private Double cgstPercent;
	
	private Double sgstAmount;
	
	private Double sgstPercent;
	
	private Double igstAmount;
	
	private Double igstPercent;
	
	private Double gstAmount;
	
	private Double subTotal;
	
	private Double totalAmount;
}
