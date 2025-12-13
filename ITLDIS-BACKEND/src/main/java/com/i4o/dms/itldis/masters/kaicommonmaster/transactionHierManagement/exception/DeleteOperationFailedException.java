package com.i4o.dms.itldis.masters.kaicommonmaster.transactionHierManagement.exception;

public class DeleteOperationFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DeleteOperationFailedException() {
		super("Delete operation of Hierarchy get Failed !!");
	}

	public DeleteOperationFailedException(String message) {
		super(message);
	}
	
}
