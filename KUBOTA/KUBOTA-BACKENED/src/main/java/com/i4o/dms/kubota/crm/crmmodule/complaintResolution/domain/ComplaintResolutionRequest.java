package com.i4o.dms.kubota.crm.crmmodule.complaintResolution.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintResolutionRequest {

	private String frtStatus;
	private String reasonForDelayFrt;
	private Boolean isInvalid;
    private String actionTaken;
    private String reasonForDelayArt;
    private Long complaintId;
    private String complaintType;
    
}
