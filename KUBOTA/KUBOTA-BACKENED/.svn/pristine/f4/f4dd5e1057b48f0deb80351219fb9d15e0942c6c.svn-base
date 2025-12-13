package com.i4o.dms.kubota.spares.purchase.transitreport.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"invoiceNo","invoiceDate","invoiceAmount","transportMode","transporter","lrNo","expectedDeliveryDate","noOfBoxes","status"})
public interface SearchResponsedto {
	public String getInvoiceNo();
	public String getInvoiceDate();
	public String getInvoiceAmount();
	public String getTransportMode();
	public String getTransporter();
	public String getLrNo();
	public String getExpectedDeliveryDate();
	public String getNoOfBoxes();
	public String getStatus();
	@JsonIgnore
	public Long getRecordCount();
}
