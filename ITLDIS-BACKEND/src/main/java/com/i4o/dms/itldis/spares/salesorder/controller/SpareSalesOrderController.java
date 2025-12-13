package com.i4o.dms.itldis.spares.salesorder.controller;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartIssueRepository;
import com.i4o.dms.itldis.spares.quotation.dto.SpareQuotationViewDto;
import com.i4o.dms.itldis.spares.salesorder.domain.SpareSalesOrder;
import com.i4o.dms.itldis.spares.salesorder.domain.SpareSalesOrderPartDetail;
import com.i4o.dms.itldis.spares.salesorder.dto.SpareSaleOrderResponseDto;
import com.i4o.dms.itldis.spares.salesorder.dto.SpareSearchSalesOrderDto;
import com.i4o.dms.itldis.spares.salesorder.repository.SpareSalesOrderRepository;
import com.i4o.dms.itldis.spares.salesorder.service.SpareSalesOrderService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/salesOrder")
public class SpareSalesOrderController {

    @Autowired
    private SpareSalesOrderService spareSalesOrderService;

    @Autowired
    private SpareSalesOrderRepository spareSalesOrderRepository;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmpRepo;

    @Autowired
    private SparePartIssueRepository sparePartIssueRepository;
    

    @PostMapping(value = "/saveSpareSalesOrder")
    public ResponseEntity<?> saveSpareSalesOrder(@RequestBody SpareSalesOrder salesOrder) {
    	System.out.println("contactNumber--"+salesOrder.getContactNumber());

        ApiResponse apiResponse = new ApiResponse();
        salesOrder.setCreatedDate(new Date());
        apiResponse.setStatus(HttpStatus.OK.value());
        if (salesOrder.getDraftFlag()) {
            salesOrder.setStatus("Draft");
            apiResponse.setMessage("Spare Sales Order Saved ....");
        } else {
            salesOrder.setStatus("Open");
            apiResponse.setMessage("Spare Sales Order Submitted ....");
            for(SpareSalesOrderPartDetail part : salesOrder.getSpareSalesOrderPartDetailList()){
            	part.setBackQuantity(part.getQuantity());
            }
        }
        spareSalesOrderService.saveSpareSaleOrder(salesOrder);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getSalesOrderNumberAutocomplete")
    public ResponseEntity<?> getSalesOrderAutocomplete(@RequestParam String salesOrderNumber) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(spareSalesOrderRepository.getSpareSalesOrderAutocomplete(
                salesOrderNumber, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Sales order autocomplete");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getQuotationNumberAutocomplete")
    public ResponseEntity<?> getQuotationNumberAutocomplete(@RequestParam String quotationNumber) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(spareSalesOrderRepository.getQuotationNumberAutocomplete(
                quotationNumber, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Quotation Number list");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getCustomerNameAutocomplete")
    public ResponseEntity<?> getCustomerNameAutocomplete(@RequestParam String customerName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(spareSalesOrderRepository.getSpareSalesOrderCustomerNameAutocomplete(
                customerName, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Customer Name autocomplete");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getItemDetailsByItemNumber")
    public ResponseEntity<?> getItemDetailsByItemNumber(@RequestParam String itemNumber,
                                                        @RequestParam String state) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(spareSalesOrderRepository.getItemDetailsByItemNumber(
                itemNumber, state, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Item Details list");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAvailableStockByItemNumber")
    public ResponseEntity<?> getAvailableStockByItemNumber(@RequestParam String itemNo, @RequestParam(required=false,defaultValue="NON-FOC") String category) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("part issue");
        apiResponse.setResult(sparePartIssueRepository.getAvailableStockForPartIssue(itemNo, userAuthentication.getBranchId(), category));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<?> search(@RequestBody SpareSearchSalesOrderDto spareSearchSalesOrderDto) {
        ApiResponse apiResponse = new ApiResponse();
        
        List<SpareSaleOrderResponseDto> list= spareSalesOrderRepository.spareSalesOrderSearch(
                spareSearchSalesOrderDto.getSalesOrderId(),
                spareSearchSalesOrderDto.getCustomerName(),
                spareSearchSalesOrderDto.getCustomerType(),
                spareSearchSalesOrderDto.getOrderStatus(),
                spareSearchSalesOrderDto.getOrderFromDate(),
                spareSearchSalesOrderDto.getOrderToDate(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                spareSearchSalesOrderDto.getPage(),
                spareSearchSalesOrderDto.getSize(),
                userAuthentication.getUsername(),
                'N', 0L);
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setResult(list);
       
        apiResponse.setCount(count);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Search list sale Order");
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getSalesOrderById/{id}")
    public ResponseEntity<?> getSalesOrderById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();

        SpareQuotationViewDto spareQuotationViewDto = new SpareQuotationViewDto();
        spareQuotationViewDto.setHeaderResponse(spareSalesOrderRepository.getSpareSalesOrderHeaderDetails(id, userAuthentication.getBranchId()));
        spareQuotationViewDto.setPartDetailsForSalesOrder(spareSalesOrderRepository.getSpareSalesOrderPartDetails(id, userAuthentication.getBranchId()));
        apiResponse.setResult(spareQuotationViewDto);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Customer Name autocomplete");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getRetailerOrMechanicAutocomplete")
    public ResponseEntity<?> getRetailerOrMechanicAutocomplete(@RequestParam String searchKey, @RequestParam String customerType) {
        ApiResponse apiResponse = new ApiResponse();    
        System.out.println("input BId--->"+userAuthentication.getBranchId());
        List<Map<String, Object>> result = spareSalesOrderRepository.getRetailerAndMechanicAutocomplete(searchKey, customerType, userAuthentication.getBranchId());
        System.out.println("list of mechanic--->"+result);
        apiResponse.setResult(result);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Retailer or Mechanic list");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getRetailerOrMechanicDetails")
    public ResponseEntity<?> getRetailerOrMechanicDetails(@RequestParam Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(spareSalesOrderRepository.getRetailerAndMechanicDetails(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Retailer or Mechanic Details");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerDetails")
    public ResponseEntity<?> getDealerDetails(@RequestParam Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(spareSalesOrderRepository.getDealerDetails(id));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Dealer Details");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerCodeAutocomplete")
    public ResponseEntity<?> getDealerDetails(@RequestParam String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(spareSalesOrderRepository.getDealerCodeAutocomplete(dealerCode));
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Dealer Code list");
        return ResponseEntity.ok(apiResponse);
    }

//    @GetMapping(value = "/deletePart")
//    public ResponseEntity<?> deletePart(@RequestParam String partId) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setResult(spareSalesOrderRepository.deletePart(partId, userAuthentication.getDealerId()));
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setMessage("Delete part");
//        return ResponseEntity.ok(apiResponse);
//    }
    
    

    
    @PostMapping(value = "/customerUploadExcel")
    public ResponseEntity<?> customerUploadExcel(MultipartFile file,
                                         @RequestParam String state,
                                         @RequestParam(required=false) String existingItems,
                                         @RequestParam(required=false) String discountRate) {
        ApiResponse apiResponse = new ApiResponse();

        System.out.println("UploadExcel-->>>>"+file+ " "+state+" "+existingItems);
        try {
            List<Map<String, Object>> excel =
            		spareSalesOrderService.customerUploadExcel(file, state, existingItems, Double.valueOf(discountRate));
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
    
    
    	@PostMapping(value = "/downloadCustomerOrderSearchExcel")
    	ResponseEntity<InputStreamResource> downloadCustomerOrderExecelReport(@RequestBody SpareSearchSalesOrderDto salseExcel, HttpServletResponse response) throws IOException{
    		Integer size = Integer.MAX_VALUE-1;
    		List <SpareSaleOrderResponseDto> saleOrderResponseDto = spareSalesOrderRepository.spareSalesOrderSearch(salseExcel.getSalesOrderId(),
														    				salseExcel.getCustomerName(),
														    				salseExcel.getCustomerType(),
														    				salseExcel.getOrderStatus(),
														    				salseExcel.getOrderFromDate(),
														    				salseExcel.getOrderToDate(),
														    				userAuthentication.getDealerId(),
														    				userAuthentication.getDealerEmployeeId(),
														    				userAuthentication.getKubotaEmployeeId(),
														    				userAuthentication.getManagementAccess(),
														    				salseExcel.getPage(),
														    				size,
														    				userAuthentication.getUsername(), 'N', 0L);
			ByteArrayInputStream byteInputStream = ExcelCellGenerator.customerSalseOrderExcelReport(saleOrderResponseDto);
	        response.setContentType("application/vnd.ms-excel");
	        HttpHeaders headers = new HttpHeaders();
	        String filename = "CustomerSalesOrder_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
	        headers.add("Content-Disposition", "attachment ; filename = "+filename);
	        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
	        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(byteInputStream));
			}
}
