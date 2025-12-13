package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception;

public class StockIdNullException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockIdNullException() {
		super("Stock Id can not be Null !!");
	}

	public StockIdNullException(String message) {
		super(message);
	}
	
}
