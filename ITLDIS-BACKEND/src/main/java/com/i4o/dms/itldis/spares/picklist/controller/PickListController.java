package com.i4o.dms.itldis.spares.picklist.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.picklist.domain.PickList;
import com.i4o.dms.itldis.spares.picklist.domain.PickListReturn;
import com.i4o.dms.itldis.spares.picklist.dto.PickListSearchDto;
import com.i4o.dms.itldis.spares.picklist.dto.PickListSearchItem;
import com.i4o.dms.itldis.spares.picklist.dto.SalesOrderDetailsDto;
import com.i4o.dms.itldis.spares.picklist.repository.PickListRepository;
import com.i4o.dms.itldis.spares.picklist.repository.PicklistReturnRepository;
import com.i4o.dms.itldis.spares.stock.repository.SpareStockStoreBinDetailRepository;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/counterSales")
public class PickListController {
	
	 @Autowired
	 private UserAuthentication userAuthentication;
	 
	 @Autowired
	 private PickListRepository pickListRepository;
	 
	 @Autowired
	 private PicklistReturnRepository picklistReturnRepo;
	
	 @Autowired
	 private SpareStockStoreBinDetailRepository stockStoreBinDetailRepository;
	 
	@GetMapping(value = "/viewPicklist")
	public ResponseEntity<?> viewPicklist(@RequestParam Long id){
		ApiResponse<SalesOrderDetailsDto> apiResponse = new ApiResponse<>();
		SalesOrderDetailsDto dto = new SalesOrderDetailsDto();
		Map<String, Object> headerDetails = pickListRepository.getPickHeaderDetails(id, userAuthentication.getBranchId());
		List<Map<String, Object>> partDetails = pickListRepository.getPickLineItemDetails(id, userAuthentication.getBranchId());
		dto.setHeaderResponse(headerDetails);
		dto.setPartDetails(partDetails);
		apiResponse.setResult(dto);
		apiResponse.setMessage("View Pick List Details");
		return ResponseEntity.ok(apiResponse);
	}
	
    @PostMapping(value = "/picklist")
    public ResponseEntity<?> savePicklist(@RequestBody PickList picklist) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        picklist.setCreatedBy(userAuthentication.getLoginId());
        picklist.setBranchId(userAuthentication.getBranchId());
        if(picklist.getPicklistHdrId()!=null){
        	picklist.setLastModifiedBy(userAuthentication.getLoginId());
        	picklist.setLastModifiedDate(new Date());
        }
        Long hdrId= picklist.getPicklistHdrId();
        pickListRepository.save(picklist);
        
        picklist.getItemDetails().forEach(item -> {
        	
            /*SpareStockStoreBinDetail stockStoreBinDetail = new SpareStockStoreBinDetail();
	        stockStoreBinDetail.setAvlQty(0);
	        stockStoreBinDetail.setBranchId(userAuthentication.getBranchId());
	        stockStoreBinDetail.setBinLocationId(item.getBinLocationId());
	        stockStoreBinDetail.setCreatedby(userAuthentication.getLoginId());
	        stockStoreBinDetail.setCreateddate(new Date());
	        stockStoreBinDetail.setInQty(0);
	        stockStoreBinDetail.setItemNo(item.getPickedItemNo());
	        stockStoreBinDetail.setOutQty(item.getIssueQty());
	        stockStoreBinDetail.setRefTransHdrId(item.getPicklistDtlId());
	        stockStoreBinDetail.setSpegst(item.getSpegst());
	        stockStoreBinDetail.setSpmgst(item.getSpmgst());
	        stockStoreBinDetail.setSpmrp(item.getSpmrp());
	        stockStoreBinDetail.setStoreId(item.getStoreId());
	        stockStoreBinDetail.setTransactionDate(picklist.getCreatedDate());
	        stockStoreBinDetail.setTransactionType("SP_SALES_PICKLIST_DTL");
	        stockStoreBinDetail.setUnitPrice(item.getUnitPrice());
	        stockStoreBinDetail.setUsedQty(0);
        
	        stockStoreBinDetailRepository.save(stockStoreBinDetail);
	        
	        Long stockbinDtlId = item.getStockBinDtlId();
	        
	        if(stockbinDtlId!=null){
	        	SpareStockStoreBinDetail ss = stockStoreBinDetailRepository.getOne(stockbinDtlId);
	        	ss.setUsedQty((ss.getUsedQty()!=null?ss.getUsedQty():0)+item.getIssueQty());
	        	ss.setAvlQty((ss.getAvlQty()!=null?ss.getAvlQty():0)-item.getIssueQty());
	        	ss.setModifiedby(userAuthentication.getLoginId());
	        	ss.setModifieddate(new Date());
	        	stockStoreBinDetailRepository.save(ss);
	        }*/
        	
        	pickListRepository.updateStockDetails(userAuthentication.getBranchId(), 
        			item.getPickedItemNo(), 
        			item.getStoreId(), 
        			item.getBinLocationId(), 
        			(hdrId==null?"PICK":"PICK_RT"), 
        			item.getPicklistDtlId(), 
        			item.getSpmrp(), 
        			item.getUnitPrice(), 
        			item.getSpegst(), 
        			item.getSpmgst(), 
        			(hdrId==null?0:item.getReturnQty()),
        			(hdrId==null?item.getIssueQty():0),
        			userAuthentication.getLoginId());
        	if(hdrId!=null && item.getReturnQty()!=null && item.getReturnQty()>0){
        		PickListReturn pickRtn = new PickListReturn();
        		pickRtn.setPicklistDtlId(item.getPicklistDtlId());
        		pickRtn.setReturnQty(item.getReturnQty());
        		pickRtn.setCreatedBy(userAuthentication.getLoginId());
        		pickRtn.setCreatedDate(new Date());
        		picklistReturnRepo.save(pickRtn);
        	}
        });
        
        pickListRepository.updateSaleOrderStatus(picklist.getPicklistHdrId(), userAuthentication.getBranchId());
        
        apiResponse.setStatus(HttpStatus.OK.value());
        if(hdrId==null)
        	apiResponse.setMessage("PickList created successfuly");
        else
        	apiResponse.setMessage("PickList returned successfuly");
        return ResponseEntity.ok(apiResponse);
    }
	 
    @GetMapping(value = "/getCustomerOrderNoAutocomplete")
    public ResponseEntity<?> getCustomerOrderNoAutocomplete(@RequestParam String saleOrderNumber) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(pickListRepository.getPickListCustomerOrderNoAuto(
        		saleOrderNumber, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("PickList Customer Order list");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "/getCustomerDetails/{id}")
    public ResponseEntity<?> getCustomerDetails(@PathVariable Long id) {
        ApiResponse<SalesOrderDetailsDto> apiResponse = new ApiResponse<>();
        SalesOrderDetailsDto dto = new SalesOrderDetailsDto();
        dto.setHeaderResponse(pickListRepository.getSalesOrderCustomerDetails(id, userAuthentication.getBranchId()));
        dto.setPartDetails(pickListRepository.getSalesOrderItemDetails(id, userAuthentication.getBranchId()));
        apiResponse.setResult(dto);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("PickList Customer Order list");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSalesOrderAutocomplete")
    public ResponseEntity<?> getSalesOrderAutocomplete(@RequestParam String saleOrderNumber ) {
    	System.out.println("branchI--"+userAuthentication.getUsername());
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(pickListRepository.getSalesOrderAutocomplete(
        		saleOrderNumber, userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("PickList Customer Order list");
        return ResponseEntity.ok(apiResponse);
    }
    

    @PostMapping(value = "/searchPickList")
    public ResponseEntity<?> search(@RequestBody PickListSearchDto pickListSearchDto) {
    	System.out.println("counteror_order"+pickListSearchDto);
        ApiResponse apiResponse = new ApiResponse();
        List<PickListSearchItem> list= pickListRepository.searchPickList(
        		pickListSearchDto.getPicklistNumber(),
        		pickListSearchDto.getOrderStatus(),
        		pickListSearchDto.getOrderFromDate(),
        		pickListSearchDto.getOrderToDate(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
        		pickListSearchDto.getPage(),
        		pickListSearchDto.getSize(),
                userAuthentication.getUsername(),
                'N', 0L);
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setResult(list);
       
        apiResponse.setCount(count);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Search pick list");
        return ResponseEntity.ok(apiResponse);
    }

}
