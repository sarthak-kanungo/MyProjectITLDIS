package com.i4o.dms.kubota.spares.purchase.orderplanningsheet.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetSearchRequestDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetSearchResponseDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetViewDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetViewDto.SPOrderPlanningSheetDetail;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.domain.SPOrderPlanningSheet;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemDetailsDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemDetailsReqDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.dto.OPSheetItemsDetailResDto;
import com.i4o.dms.kubota.spares.purchase.orderplanningsheet.repository.SPOrderPlanningSheetRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

@Service
public class SPOrderPlanningSheetServiceImpl implements SPOrderPlanningSheetService{
	
	@Autowired
	private UserAuthentication userAuthentication;
	
	@Autowired
    private JasperPrintService jasperPrintService;
	
	@Autowired
    private SPOrderPlanningSheetRepo opsRepo;
	
	@Override
	public ApiResponse<?> autoGetOPSheetNo(String opsNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(opsRepo.autoGetOpsNo(opsNo));
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> searchOPSheet(OPSheetSearchRequestDto opSheetSearchDto) {
		ApiResponse<List<OPSheetSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<OPSheetSearchResponseDto> responceDtoList = opsRepo.searchOPSheet(
				opSheetSearchDto.getOpSheetNo(),
				opSheetSearchDto.getOrderTypeId(),
				opSheetSearchDto.getLogicId(),
				opSheetSearchDto.getFromDate(),
				opSheetSearchDto.getToDate(),
				opSheetSearchDto.getPage(),
				opSheetSearchDto.getSize());
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}
	
	
	
	@Override
	public ApiResponse<?> getReorderQtyMonth() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(opsRepo.getReorderQtyMonth());
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> getSuggestedQtyMonth() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(opsRepo.getSuggestedQtyMonth());
		
		return apiResponse;
	}
	
	@Override
	public ApiResponse<?> getAllLogic() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(opsRepo.getAllLogic());
		
		return apiResponse;
	}
	
	
	@Override
	public void printOPSheetReport(String opSheetId, String printStatus, String filePath,
			OutputStream outputStream) throws Exception 
	{
		String jasperfile = filePath + "ORDERPLANNINGSHEETREPORT.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("orderid", opSheetId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
	}
	
	
	@Override
	public ApiResponse<?> viewOPSheet(String OPSheetNo) {
		ApiResponse<OPSheetViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(opsRepo.findByOpsNo(OPSheetNo));
		
		return apiResponse;
	}
	
	
	@Override
	public ApiResponse<?> getOPSheetItemDetails(OPSheetItemDetailsReqDto detailsReqDto) {
		ApiResponse<List<OPSheetItemsDetailResDto>> apiResponse = new ApiResponse<>();
		
		List<OPSheetItemDetailsDto> itemList = opsRepo.getOPSheetItemDetails(userAuthentication.getUsername(), 
				detailsReqDto.getOrderTypeId(), detailsReqDto.getLogicId(), detailsReqDto.getReOrderMonth(), 
				detailsReqDto.getSuggestedOrderMonth());
		
		List<OPSheetItemsDetailResDto> detailResDtos = new ArrayList<>();
		
		itemList.forEach(item -> {
			OPSheetItemsDetailResDto detailResDto = new OPSheetItemsDetailResDto();
			detailResDto.setItemId(item.getId());
			detailResDto.setItemNo(item.getItemNo());
			detailResDto.setItemDescription(item.getItemDescription());
			detailResDto.setReorderQty(item.getReorderQty());
			detailResDto.setDealerStock(item.getDealerStock());
			detailResDto.setKaiBackOrder(item.getKaiBackOrder());
			detailResDto.setTransitFromKai(item.getTransitFromKai());
			detailResDto.setUnitPrice(item.getUnitPrice());
			detailResDto.setGstPercent(item.getGstPercent());
			detailResDto.setL12mSales(item.getL12mSales());
			detailResDto.setSuggestedOrderQty(item.getSuggestedOrderQty());
			
			detailResDtos.add(detailResDto);
		});
		
		if(detailsReqDto.getOpSheetNo() != null && !detailsReqDto.getOpSheetNo().isEmpty() && !detailResDtos.isEmpty()) {
			OPSheetViewDto opSheetViewDto = opsRepo.findByOpsNo(detailsReqDto.getOpSheetNo());
			List<SPOrderPlanningSheetDetail> planningSheetDetails = opSheetViewDto.getOrderPlanningSheetDetails();
			
			Map<Long, SPOrderPlanningSheetDetail> planningSheetDetailsMap = planningSheetDetails.stream()
	                .collect(Collectors.toMap(detail -> detail.getSparePartMaster().getId(), Function.identity()));
			
			detailResDtos.forEach(item -> {
	            SPOrderPlanningSheetDetail planningDetail = planningSheetDetailsMap.get(item.getItemId());
	            if (planningDetail != null) {
	            	item.setId(planningDetail.getId());
	                item.setActualOrderQty(planningDetail.getActualOrderQty());
	                item.setOrderValue(planningDetail.getOrderValue());
	            }
	        });
		}
		
		apiResponse.setResult(detailResDtos);
		
		return apiResponse;
	}
	
	
	
	@Override
	public ApiResponse<?> saveOPSheet(SPOrderPlanningSheet orderPlanningSheet) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		orderPlanningSheet.setDealerId(userAuthentication.getDealerId());
		orderPlanningSheet.setOrderDate(new Date());
		
        if(orderPlanningSheet.getDraftFlag()){
        	orderPlanningSheet.setOpsNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis());
        }
        
        orderPlanningSheet.setStatus(orderPlanningSheet.getDraftFlag() ? "Draft" : "Submitted");
        
		if (orderPlanningSheet.getId() == null) {
			orderPlanningSheet.setCreatedBy(userAuthentication.getLoginId());
			orderPlanningSheet.setCreatedDate(new Date());
		} else {
			orderPlanningSheet.setLastModifiedBy(userAuthentication.getLoginId());
			orderPlanningSheet.setLastModifiedDate(new Date());
        }
        
        opsRepo.save(orderPlanningSheet);
        
        apiResponse.setResult("Submitted");
        apiResponse.setMessage(orderPlanningSheet.getDraftFlag() 
				? "Saving Order Planning Sheet Successful" : "Submit Order Planning Sheet Successful");
        
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getOrderTypes() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		List<Map<String, Object>> orderTypeList = opsRepo.getOrderTypes();
		
		apiResponse.setResult(orderTypeList);
		
		return apiResponse;
	}

}
