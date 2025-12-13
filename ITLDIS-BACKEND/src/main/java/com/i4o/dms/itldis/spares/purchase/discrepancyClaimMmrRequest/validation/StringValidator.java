package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.validation;

import org.springframework.stereotype.Component;

@Component
public class StringValidator extends SpPartDiscrepancyClaimAbstractValidator<Object>{

	@Override
	public boolean validate(Object object) {
		if(object instanceof String)
			return true;
		return false;
	}

}
