package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.service;

import java.io.OutputStream;

import javax.transaction.Transactional;

import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain.SPOrderPlanningSheet;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemDetailsReqDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetSearchRequestDto;
import com.i4o.dms.kubota.utils.ApiResponse;

@Transactional
public interface SPOrderPlanningSheetService {
	
	ApiResponse<?> autoGetOPSheetNo(String opsNo);
	
	ApiResponse<?> searchOPSheet(OPSheetSearchRequestDto opSheetSearchDto);
	
	ApiResponse<?> getReorderQtyMonth();
	
	ApiResponse<?> getSuggestedQtyMonth();
	
	ApiResponse<?> getAllLogic();
	
	ApiResponse<?> viewOPSheet(String OPSheetNo);
	
	ApiResponse<?> getOPSheetItemDetails(OPSheetItemDetailsReqDto detailsReqDto);
	
	ApiResponse<?> saveOPSheet(SPOrderPlanningSheet orderPlanningSheet);
	
	void printOPSheetReport(String opSheetId, String printStatus, String filePath, OutputStream outputStream) throws Exception;

	ApiResponse<?> getOrderTypes();

}
