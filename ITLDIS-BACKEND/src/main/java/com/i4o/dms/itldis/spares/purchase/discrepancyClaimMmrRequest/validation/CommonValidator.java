package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.validation;

import org.springframework.stereotype.Component;

@Component
public class CommonValidator extends SpPartDiscrepancyClaimAbstractValidator<Object>{

	@Override
	public boolean validate(Object object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean validKaiUser(Object object) {
		if(object != null) {
			return true;
		}
		return false;
	}

}
