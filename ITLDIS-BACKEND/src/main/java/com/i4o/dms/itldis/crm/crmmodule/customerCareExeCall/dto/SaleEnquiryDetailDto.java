package com.i4o.dms.itldis.crm.crmmodule.customerCareExeCall.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleEnquiryDetailDto {
	
	private String source;
	
	private String model;
	
	private String subModel;
	
	private String variant;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date enquiryDate;
	
	@Size(max = 10,min = 10)
    private String mobileNumber;
	
	private String firstName;
    
    private String lastName;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date tentativePurchaseDate;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date nextFollowUpDate;
    
    private String remarks;
    
}
