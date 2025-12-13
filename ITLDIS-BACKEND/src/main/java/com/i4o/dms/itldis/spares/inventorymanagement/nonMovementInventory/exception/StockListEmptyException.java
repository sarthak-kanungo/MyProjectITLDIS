package com.i4o.dms.itldis.spares.inventorymanagement.nonMovementInventory.exception;

public class StockListEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockListEmptyException() {
		super("Stock List can not be Empty !!");
	}

	public StockListEmptyException(String message) {
		super(message);
	}

}
