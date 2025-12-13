package com.i4o.dms.itldis.spares.purchase.discrepancyClaimMmrRequest.validation;

/**
 * @author suraj.gaur
 * @Validator_Interface: Begin by defining a Validator interface that declares a method for validation. 
 * This interface should be generic to accommodate different types of objects.
 * @param <T>
 */
public interface SpPartDiscrepancyClaimValidator<T> {
	
	boolean validate(T object);

}
