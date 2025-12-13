package com.i4o.dms.itldis.spares.inventorymanagement.stockAdjustment.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadExcelDto {

    @ExcelCellName("Adjustment Type")
    @ExcelCell(0)
	private String adjustmentType;
    
	@ExcelCellName("Part No")
    @ExcelCell(1)
    private String itemNumber;

    @ExcelCellName("Store")
    @ExcelCell(2)
	private String store;
    
    @ExcelCellName("Bin Name")
    @ExcelCell(3)
	private String location;
    
    @ExcelCellName("MRP")
    @ExcelCell(4)
	private Double mrp;

    @ExcelCellName("Qty")
    @ExcelCell(5)
	private Integer quantity;
    
}
