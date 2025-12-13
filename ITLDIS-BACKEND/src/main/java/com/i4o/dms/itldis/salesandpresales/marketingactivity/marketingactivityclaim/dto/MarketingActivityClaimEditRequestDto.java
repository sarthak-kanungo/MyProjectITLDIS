package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto;

import java.util.List;

import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.domain.ActivityClaimHead;

import lombok.Data;

/**
 * @author suraj.gaur
 */
@Data
public class MarketingActivityClaimEditRequestDto {
	
	private Long claimId;
	private String claimStatus;
	private List<ActivityClaimHead> activityClaimHeads;

}
