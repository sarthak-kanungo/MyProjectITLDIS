package com.i4o.dms.kubota.masters.kaicommonmaster.transactionHierManagement.exception;

public class TransactionNameEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransactionNameEmptyException() {
		super("Transaction name should not be Empty !!");
	}

	public TransactionNameEmptyException(String message) {
		super(message);
	}
	
	

}
