package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception;

public class StockNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockNotFoundException() {
		super("The Selected Item is not available in Stock !!");
	}

	public StockNotFoundException(String message) {
		super(message);
	}

}
