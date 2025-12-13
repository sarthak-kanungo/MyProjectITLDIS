package com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.dto;

import java.util.List;

import com.i4o.dms.kubota.crm.crmmodule.customerCareExeCall.domain.QaCrmCceCallFeedback;
import com.i4o.dms.kubota.service.servicebooking.dto.ServiceBookingViewDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CceEnquiryViewDto {
	
	private CceHdrViewDto cceHdrViewDto;
	
	private SaleEnquiryDetailDto enquiryDetailDto;
	
	private DealerDetailsViewDto dealerDetailsDto;
	
	private ServiceBookingViewDto serviceBookingViewDto;
	
	private List<QaCrmCceCallFeedback> callFeedback;
	
	private String customerId;
	
	private String customerCode;
	
}
