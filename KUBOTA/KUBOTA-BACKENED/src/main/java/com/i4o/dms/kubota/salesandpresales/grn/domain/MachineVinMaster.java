package com.i4o.dms.kubota.salesandpresales.grn.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.i4o.dms.kubota.masters.products.domain.MachineMaster;
import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="machine_vin_master")
public class MachineVinMaster {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long vinId;
	 
	 private String chassisNo;

     private String engineNo;
	 
	 @ManyToOne
	 @JoinColumn(name = "machine_master_id")
	 private MachineMaster machineMaster;
	 
	 private Date installationDate;
	 
	 private Date deliveryDate;
	 
     private String csbNumber;
     
     private String registrationNumber;
     
}
