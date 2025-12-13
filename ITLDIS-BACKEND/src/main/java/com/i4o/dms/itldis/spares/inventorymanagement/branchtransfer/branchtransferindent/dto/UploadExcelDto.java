package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author suraj.gaur
 */
@Getter
@Setter
public class UploadExcelDto {
	
	@ExcelCellName("Item No")
    @ExcelCell(0)
	private String itemNo;
	
	@ExcelCellName("Quantity")
    @ExcelCell(1)
	private String quantity;
	
}
