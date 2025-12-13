package com.i4o.dms.itldis.spares.purchase.orderplanningsheet.dto;

import lombok.Data;

/**
 * @author suraj.gaur
 */
@Data
public class OPSheetItemDetailsReqDto {
	private Long orderTypeId;
	private Long logicId;
	private float reOrderMonth;
	private float suggestedOrderMonth;
	private String opSheetNo;
}
