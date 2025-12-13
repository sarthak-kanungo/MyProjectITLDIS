package com.i4o.dms.itldis.warranty.hotlinereport.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineVinMaster;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WA_HTLR_MACHINE_DTL")
public class WarrantyHotlineReportMachineDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "HTLR_ID", referencedColumnName = "id")
	private  WarrantyHotlineReport warrantyHotlineReport;
	
	@ManyToOne
    @JoinColumn(name = "CHASSIS_ID")
    private MachineVinMaster machineVinMaster;
	
	private String vendorCode;
	
	private String vendorName;
	
	private String vendorInvoiceNo;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date vendorInvoiceDate;
	
	private String containerNo;
	
}
