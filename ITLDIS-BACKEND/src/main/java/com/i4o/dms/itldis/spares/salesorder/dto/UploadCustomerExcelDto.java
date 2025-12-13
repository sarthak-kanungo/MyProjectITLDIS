package com.i4o.dms.itldis.spares.salesorder.dto;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class UploadCustomerExcelDto {

    @ExcelCellName("Item Number")
    @ExcelCell(0)
    private String itemNumber;

    @ExcelCellName("Quantity")
    @ExcelCell(1)
    private Long quantity;

}
