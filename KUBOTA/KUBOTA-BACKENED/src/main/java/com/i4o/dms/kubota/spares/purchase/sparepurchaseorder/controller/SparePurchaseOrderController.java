package com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.DealerOutstandingResponse;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.OPSItemsDetailResponseDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.PoViewDto;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.SearchSparePurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderApprovalRepository;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.repository.SparePurchaseOrderRepository;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.service.SparesPurchaseOrderService;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/purchaseOrder")
public class SparePurchaseOrderController {

    @Autowired
    private SparePurchaseOrderRepository sparePurchaseOrderRepository;
    
    @Autowired
    private SparePurchaseOrderApprovalRepository sparePurchaseOrderApprovalRepository;

    @Autowired
    private SparesPurchaseOrderService sparesPurchaseOrderService;

    @Autowired
    private UserAuthentication userAuthentication;

    private Logger logger = LoggerFactory.getLogger(SparePurchaseOrderController.class);
    @GetMapping(value="/downloadTemplate")
    public ResponseEntity<InputStreamResource> downloadTemplate(
    		@RequestParam("filename")String filename,
    		HttpServletResponse response, HttpServletRequest request) throws FileNotFoundException{
    	File fileOnServer = null;
		FileInputStream inputStream = null;
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/template/"+filename);
		fileOnServer = new File(filePath);
		inputStream = new FileInputStream(fileOnServer);
    	response.setContentType("application/vnd.ms-excel");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment ; filename ="+fileOnServer.getName());
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(inputStream));
    }
    @GetMapping(value = "/getItemNumberAutoComplete")
    public ResponseEntity<?> getItemNumberAutoComplete(@RequestParam String itemNumber,
                                                       @RequestParam(required = false) String itemId,
                                                       @RequestParam String orderType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("item Number Auto Complete list");
        apiResponse.setStatus(HttpStatus.OK.value());

       /* if (orderType.equals("Other Suppliers") || orderType.equals("Oil and Lubricant")) {
            apiResponse.setResult(sparePurchaseOrderRepository
                    .getLocalItemNumberAutoComplete(itemNumber, itemId, orderType));
        } else {
            apiResponse.setResult(sparePurchaseOrderRepository
                    .getItemNumberAutoComplete(itemNumber, itemId));
        }*/
        apiResponse.setResult(sparePurchaseOrderRepository
                .getItemNumberAutoComplete(itemNumber, itemId, orderType, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "/getItemNumberSearchAutoComplete")
    public ResponseEntity<?> getItemNumberSearchAutoComplete(@RequestParam String itemNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("item Number Auto Complete list");
        apiResponse.setStatus(HttpStatus.OK.value());

       /* if (orderType.equals("Other Suppliers") || orderType.equals("Oil and Lubricant")) {
            apiResponse.setResult(sparePurchaseOrderRepository
                    .getLocalItemNumberAutoComplete(itemNumber, itemId, orderType));
        } else {
            apiResponse.setResult(sparePurchaseOrderRepository
                    .getItemNumberAutoComplete(itemNumber, itemId));
        }*/
        System.out.println("itemNumber : "+itemNumber);
        apiResponse.setResult(sparePurchaseOrderRepository
                .getItemNumberSearchAutoComplete( userAuthentication.getUsername(),itemNumber));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getItemDetailsByItemId")
    public ResponseEntity<?> getItemDetailsByItemId(
            @RequestParam String itemId,
            @RequestParam String orderType,
            @RequestParam(required=false, name="priceType") String priceType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("item details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePurchaseOrderRepository
                .getItemDetailsByItemId(itemId, orderType, userAuthentication.getDealerId(), priceType));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerOutStandingDetails")
    public ResponseEntity<?> getDealerOutStandingDetails(@RequestParam(name="poId", required=false)Long poId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Dealer outStanding details ");
        apiResponse.setStatus(HttpStatus.OK.value());
        System.out.println("poId : "+poId);
        DealerOutstandingResponse result = sparePurchaseOrderRepository.getDealerOutStanding(userAuthentication.getDealerId(), poId);
        System.out.println(result);
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getPurchaseOrderNumberAutoComplete")
    public ResponseEntity<?> getPurchaseOrderNumberAutoComplete(@RequestParam String poNumber) {
    	System.out.println("getPurchaseOrderNumberAutoComplete-->"+poNumber);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Purchase Order Number AutoComplete ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePurchaseOrderRepository
                .getPoNumberAutoComplete(poNumber, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/saveSparePurchaseOrder")
    public ResponseEntity<?> saveSparePurchaseOrder(@RequestBody SparePurchaseOrder sparePurchaseOrder) {
    	sparePurchaseOrder.setPurchaseOrderDate(new Date());
        if (sparePurchaseOrder.getDraftFlag()) {
            sparesPurchaseOrderService.saveSparePurchaseOrder(sparePurchaseOrder);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Spare Purchase Order Saved...!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);
        }
        sparePurchaseOrder.setLastModifiedDate(new Date());
        sparePurchaseOrder.setLastModifiedBy(userAuthentication.getLoginId());
        sparesPurchaseOrderService.saveSparePurchaseOrder(sparePurchaseOrder);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Purchase Order Submitted...!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/approveSparePurchaseOrder")
    public ResponseEntity<?> approveSparePurchaseOrder(@RequestParam(name="poId") Long poId,
    		@RequestParam(name="remark") String remark,
    		@RequestParam(name="flag") String flag) {
    	
        ApiResponse apiResponse = new ApiResponse();
        String success = sparePurchaseOrderApprovalRepository.poApproval(poId, userAuthentication.getKubotaEmployeeId(), remark, userAuthentication.getUsername(), flag);
        //poid, :kaiEmpId, :remark, :userCode, :approvalStatus
        if(success!=null && !success.equalsIgnoreCase("FAIL")){
        	apiResponse.setMessage(flag.equalsIgnoreCase("APPROVED") ? "Po Approved successfully" : 
        		flag.equalsIgnoreCase("REJECT") ? "Po rejected" : "PO on hold");
        }else{
        	apiResponse.setMessage("APPROVAL/REJECTION/HOLD FAILED");
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getSparesPoApprovalDetails")
    public ResponseEntity<?> getSparesPoApprovalDetails(@RequestParam Long poId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Sales Po Approval Details.");
        apiResponse.setStatus(HttpStatus.OK.value());
        if (userAuthentication.getKubotaEmployeeId() != null)
            apiResponse.setResult(sparePurchaseOrderApprovalRepository.getSparesPoApprovalDetails(poId, userAuthentication.getKubotaEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/searchPurchaseOrder")
    public ResponseEntity<?> searchPurchaseOrder(@RequestBody SearchSparePurchaseOrder searchSparePurchaseOrder) {
        ApiResponse apiResponse = new ApiResponse();
        List<ResponseSearchPurchaseOrder> list = sparePurchaseOrderRepository.searchSparePurchaseOrder(
                //searchSparePurchaseOrder.getDealerShip(),
                //searchSparePurchaseOrder.getBranch(),
                searchSparePurchaseOrder.getPoType(),
                searchSparePurchaseOrder.getPoStatus(),
                searchSparePurchaseOrder.getFromDate(),
                searchSparePurchaseOrder.getToDate(),
                searchSparePurchaseOrder.getPoNumber(),
                searchSparePurchaseOrder.getPartNumber(),
                searchSparePurchaseOrder.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchSparePurchaseOrder.getPage(),
                searchSparePurchaseOrder.getSize(),
                userAuthentication.getUsername(),
                'N',
                searchSparePurchaseOrder.getHierId(), "View");
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setMessage("search Purchase Order List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(list);
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/downloadSparePurchaseReportExcel")
    public ResponseEntity<InputStreamResource> downloadEnquiryReportExcel(@RequestBody SearchSparePurchaseOrder searchSparePurchaseOrder,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;
    	
    	List<ResponseSearchPurchaseOrder> list = sparePurchaseOrderRepository.searchSparePurchaseOrder(
                searchSparePurchaseOrder.getPoType(),
                searchSparePurchaseOrder.getPoStatus(),
                searchSparePurchaseOrder.getFromDate(),
                searchSparePurchaseOrder.getToDate(),
                searchSparePurchaseOrder.getPoNumber(),
                searchSparePurchaseOrder.getPartNumber(),
                searchSparePurchaseOrder.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchSparePurchaseOrder.getPage(),
                size,
                userAuthentication.getUsername(),
                'N',
                searchSparePurchaseOrder.getHierId(), "View");
    	
    	ByteArrayInputStream in = ExcelCellGenerator.spareOrderExcelReport(list);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "SparePurchaseOrderReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    @PostMapping("/downloadSparePurchaseDetailReport")
    public ResponseEntity<InputStreamResource> downloadSparePurchaseDetailReport(@RequestBody SearchSparePurchaseOrder searchSparePurchaseOrder,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = Integer.MAX_VALUE-1;
    	
    	List<ResponseSearchPurchaseOrder> list = sparePurchaseOrderRepository.searchSparePurchaseOrder(
                searchSparePurchaseOrder.getPoType(),
                searchSparePurchaseOrder.getPoStatus(),
                searchSparePurchaseOrder.getFromDate(),
                searchSparePurchaseOrder.getToDate(),
                searchSparePurchaseOrder.getPoNumber(),
                searchSparePurchaseOrder.getPartNumber(),
                searchSparePurchaseOrder.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchSparePurchaseOrder.getPage(),
                size,
                userAuthentication.getUsername(),
                'N',
                searchSparePurchaseOrder.getHierId(), "Details");
    	
    	ByteArrayInputStream in = ExcelCellGenerator.spareOrderDetailReport(list);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "SparePurchaseOrderDetailReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    
    @GetMapping(value = "/getSparePurchaseOrderById1/{id}")
    public ResponseEntity<?> getPoById1(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Purchase Order details");
        apiResponse.setResult(sparePurchaseOrderRepository.getOne(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getSparePurchaseOrderById")
    public ResponseEntity<?> getPoById(@RequestParam Long id, @RequestParam String orderType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Purchase Order details");
        PoViewDto poViewDto = sparesPurchaseOrderService.getViewDto(id, orderType);
        apiResponse.setResult(poViewDto);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getOrderTypeBySupplierType")
    public ResponseEntity<?> getOrderTypeBySupplierType(@RequestParam String supplierType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("order type list");
        apiResponse.setResult(sparePurchaseOrderRepository.getorderTypeBySupplierType(supplierType));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSupplierTypeDropdown")
    public ResponseEntity<?> getSupplierTypeDropdown() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("supplier type list");
        apiResponse.setResult(sparePurchaseOrderRepository.getSupplierTypeDropdown());
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerCodeAutocomplete")
    public ResponseEntity<?> getDealerCodeAutocomplete(@RequestParam String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Dealer details");
        apiResponse.setResult(sparePurchaseOrderRepository.getDealerCodeAutocomplete(dealerCode,userAuthentication.getDealerId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getItemDetailsByJobCardId")
    public ResponseEntity<?> getItemDetailsByJobCardId(@RequestParam Long jobCardId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Part List by job Card");
        apiResponse.setResult(sparePurchaseOrderRepository.getPoPartItemInfoByJobCardNo(jobCardId, userAuthentication.getDealerId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getJobCardAutocomplete")
    public ResponseEntity<?> getJobCardAutocomplete(@RequestParam String jobCardNo) { 	
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Job Card List");
        System.out.println(jobCardNo+","+ userAuthentication.getDealerId());
        List<Map<String,Object>> result = sparePurchaseOrderRepository.getJobCardNoAutocomplete(jobCardNo, userAuthentication.getDealerId());
        System.out.println(result);
        apiResponse.setResult(result);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/tcsPercList")
    public ResponseEntity<?> tcsPercList(){
    	ApiResponse apiResponse = new ApiResponse();        
        List<Map<String, Object>> tcsList = sparePurchaseOrderRepository.getTcsList();
        apiResponse.setResult(tcsList);
        apiResponse.setMessage("TCS Perc List");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/uploadExcel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadExcel(@RequestPart MultipartFile file,
                                         @RequestPart String orderType,
                                         @RequestPart(required=false) String existingItems,
                                         @RequestPart(required=false) String priceType) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Map<String, Object>> excel =
                    sparesPurchaseOrderService.uploadExcel(file, orderType, priceType, existingItems);
            apiResponse.setResult(excel);
            apiResponse.setMessage("Excel Uploaded Item Details");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);

        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            apiResponse.setMessage("Message" + e.getMessage());

            return ResponseEntity.badRequest().body(apiResponse);
        }
    }
    
    /**
     * @author suraj.gaur
     * @param poNumber
     * @return
     */
    @GetMapping(value = "/autoGetOPSnumber")
    public ResponseEntity<?> autoGetOPSnumber(@RequestParam String opsNo) {
    	try {
    		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
            List<Map<String, Object>> resList = sparePurchaseOrderRepository
                    .autoGetOPSnumber(userAuthentication.getDealerId(), opsNo);
                    
            apiResponse.setMessage("Order Planning Number Auto Get Success!");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(resList);
            
            return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			logger.info("/autoGetOPSnumber: " + e.getMessage());
			logger.error("An error occurred in /autoGetOPSnumber:", e);
			
			apiResponse.setStatus(HttpStatus.OK.value());
	        apiResponse.setMessage("There is some error occured!");
	        apiResponse.setResult("Message: " + e.getMessage());
	        
	        return ResponseEntity.ok(apiResponse);
		}
    }
    
    /**
     * @author suraj.gaur
     * @param opsNo
     * @return
     */
    @GetMapping(value = "/getOPSitemsDeatail")
    public ResponseEntity<?> getOPSitemsDeatail(@RequestParam String opsId,
            @RequestParam(required = false) String existingItems,
            @RequestParam(required = false) String priceType) {
    	try {
    		ApiResponse<OPSItemsDetailResponseDto> apiResponse = new ApiResponse<>();
    		OPSItemsDetailResponseDto itemsDetails =
                    sparesPurchaseOrderService.getOPSitemsDeatail(opsId, priceType, existingItems);
                    
            apiResponse.setMessage("Order Planning Number Items Details Get Success!");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(itemsDetails);
            
            return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			logger.info("/getOPSitemsDeatail: " + e.getMessage());
			logger.error("An error occurred in /getOPSitemsDeatail:", e);
			
			apiResponse.setStatus(HttpStatus.OK.value());
	        apiResponse.setMessage("There is some error occured!");
	        apiResponse.setResult("Message: " + e.getMessage());
	        
	        return ResponseEntity.ok(apiResponse);
		}
    }
    
}

