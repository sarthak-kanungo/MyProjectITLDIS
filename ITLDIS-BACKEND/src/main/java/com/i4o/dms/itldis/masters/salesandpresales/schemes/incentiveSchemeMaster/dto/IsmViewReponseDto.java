package com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.i4o.dms.itldis.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMasterAttachment;

/**
 * @author suraj.gaur
 */
public interface IsmViewReponseDto {
	
	Long getId();
	String getSchemeNumber();
	String getSchemeType();
	@JsonFormat(pattern = "dd-MM-yyyy")
	String getSchemeDate();
	String getReferenceSchemeNumber();
	String getStatus();
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getValidTo();
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date getValidFrom();
    Boolean getMaximumQuantity();
    Boolean getClaimAttachmentRequired();
    Boolean getIncentiveAllowedForExceededQuantity();
    String getSZone();
    String getSRegion();
    String getSProduct();
    String getSSeries();
    String getSModel();
    String getSSubModel();
    String getSVariant();
    String getSItem();

    MarketingActivityProposal getActivityProposal();
	interface MarketingActivityProposal {
		Long getActivityProposalId();
		String getActivityNumber();
		@JsonFormat(pattern = "dd-MM-yyyy")
		Date getActivityCreationDate();
		String getActivityPurpose();
	}
	
	List<IncentiveSchemeMasterDetail> getIncentiveSchemeDetails();
	interface IncentiveSchemeMasterDetail {
		Long getId();
	    String getT1Quantity();
	    String getT1IncentivePerQuantity();
	    String getT2Quantity();
	    String getT2IncentivePerQuantity();
	    String getT3Quantity();
	    String getT3IncentivePerQuantity();
	    String getT4Quantity();
	    String getT4IncentivePerQuantity();
	    String getT5Quantity();
	    String getT5IncentivePerQuantity();
	    Integer getMaximumQuantity();
	    
	    DealerEmployeeMaster getDealerEmployee();
	    interface DealerEmployeeMaster {
	    	Long getId();
	        Long getDealerId();
	        String getEmployeeCode();
	        String getFirstName();
	        String getMiddleName();
	        String getLastName();
	    }
	    
	    DealerMaster getDealer();
	    interface DealerMaster {
	    	Long getId();
	        String getDealerCode();
	        String getDealerName();
	    }
	}
	
	//Special syntax for setting data in interface
	void setSchemeAttachment(IncentiveSchemeMasterAttachment attachment);
	IncentiveSchemeMasterAttachment getSchemeAttachment();
}
