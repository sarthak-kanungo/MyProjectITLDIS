package com.i4o.dms.kubota.warranty.hotlinereport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "WA_HTLR_ATTACHMENT")
public class WarrantyHotlineReportAttachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "HTLR_ID", referencedColumnName = "id")
	private  WarrantyHotlineReport warrantyHotlineReport;

    @NotNull(message = "fileName in WarrantyHotlineReportAttachment can't be null")
    @Column(length = 300)
    private String fileName;

    @NotNull(message = "fileType in WarrantyHotlineReportAttachment can't be null")
    @Column(length=50)
    private String fileType;
    
    @NotNull(message = "attachStatus in WarrantyHotlineReportAttachment can't be null")
    private String attachStatus;
}
