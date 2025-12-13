package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.exception;

public class InsertFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsertFailException() {
		super("Insert operation failed in the table !!");
	}

	public InsertFailException(String message) {
		super(message);
	}
	
}
