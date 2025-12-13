package com.i4o.dms.itldis.spares.quotation.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;
import com.i4o.dms.itldis.spares.quotation.dto.SpareQuotationSearchDto;
import com.i4o.dms.itldis.spares.quotation.dto.SpareQuotationSearchResponse;
import com.i4o.dms.itldis.spares.quotation.dto.SpareQuotationViewDto;
import com.i4o.dms.itldis.spares.quotation.repository.SpareQuotationRepository;
import com.i4o.dms.itldis.spares.quotation.service.SpareQuotationService;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/quotation")
public class SpareQuotationController {

    @Autowired
    private SpareQuotationRepository spareQuotationRepository;

    @Autowired
    private SpareQuotationService spareQuotationService;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmpRepo;

    @PostMapping("/saveQuotation")
    public ResponseEntity<?> saveQuotation(@RequestBody SpareQuotation spareQuotation) {
    	
        if (spareQuotation.getDraftFlag()) {
            spareQuotation.setQuotationStatus("Draft");
            spareQuotationService.saveSpareQuotation(spareQuotation);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Spare Quotation Saved...!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);
        } else {
            spareQuotation.setQuotationStatus("Open");
            spareQuotationService.saveSpareQuotation(spareQuotation);
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Spare Quotation Saved...!");
            apiResponse.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(apiResponse);
        }
    }

    @GetMapping(value = "/getQuotationNumberAutocomplete")
    public ResponseEntity<?> getQuotationNumberAutocomplete(@RequestParam String quotationNumber) {
    	System.out.println("quotationNumber--->"+quotationNumber+"==="+"branchId--->"+ userAuthentication.getBranchId());
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(spareQuotationRepository.getQuotationNumberAutocomplete(quotationNumber, userAuthentication.getBranchId()));
        apiResponse.setMessage("Quotation Number List");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/getQuotationSearch")
    public ResponseEntity<?> getQuotationSearch(@RequestBody SpareQuotationSearchDto spareQuotationSearchDto) {
    	
        ApiResponse<List<SpareQuotationSearchResponse>> apiResponse = new ApiResponse<>();

        List<SpareQuotationSearchResponse> spareQuotationSearchResponse = spareQuotationRepository.getQuotationSearch(
                spareQuotationSearchDto.getQuotationId(),
                spareQuotationSearchDto.getCustomerName(),
                spareQuotationSearchDto.getCustomerType(),
                spareQuotationSearchDto.getQuotationFromDate(),
                spareQuotationSearchDto.getQuotationToDate(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                spareQuotationSearchDto.getPage(),
                spareQuotationSearchDto.getSize(),
                userAuthentication.getUsername(),
            	'N', 0l);
        
        Long count = 0L;
        if(spareQuotationSearchResponse!=null && spareQuotationSearchResponse.size()>0){
        	count = spareQuotationSearchResponse.get(0).getRecordCount();
        }
        
        	
//        Long count = spareQuotationRepository.getQuotationSearchCount(
//                spareQuotationSearchDto.getQuotationId(),
//                spareQuotationSearchDto.getCustomerName(),
//                spareQuotationSearchDto.getCustomerType(),
//                spareQuotationSearchDto.getQuotationFromDate(),
//                spareQuotationSearchDto.getQuotationToDate(),
//                userAuthentication.getDealerId(),
//                userAuthentication.getDealerEmployeeId(),
//                userAuthentication.getKubotaEmployeeId(),
//                userAuthentication.getManagementAccess(),
//                spareQuotationSearchDto.getPage(),
//                spareQuotationSearchDto.getSize());
        apiResponse.setResult(spareQuotationSearchResponse);
        apiResponse.setCount(count);
        apiResponse.setMessage("Quotation List");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getQuotationById/{id}")
    public ResponseEntity<?> getQuotationById(@PathVariable Long id) {
    	System.out.println("id--->"+id+"==="+"dealerId--->"+userAuthentication.getDealerId());
        ApiResponse<SpareQuotationViewDto> apiResponse = new ApiResponse<>();
        SpareQuotationViewDto spareQuotationViewDto = new SpareQuotationViewDto();
        spareQuotationViewDto.setHeaderResponse(spareQuotationRepository.getQuotationViewHeaderDetails(id, userAuthentication.getBranchId()));
        spareQuotationViewDto.setPartDetails(spareQuotationRepository.getQuotationViewPartDetails(id, userAuthentication.getBranchId()));

        apiResponse.setResult(spareQuotationViewDto);
        apiResponse.setMessage("Quotation Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getQuotationByIdForSalesOrder/{id}")
    public ResponseEntity<?> getQuotationByIdForSalesOrder(@PathVariable Long id) {

        ApiResponse<SpareQuotationViewDto> apiResponse = new ApiResponse<>();
        SpareQuotationViewDto spareQuotationViewDto = new SpareQuotationViewDto();
        spareQuotationViewDto.setHeaderResponse(spareQuotationRepository.getQuotationViewHeaderDetails(id, userAuthentication.getBranchId()));
        spareQuotationViewDto.setPartDetailsForSalesOrder(spareQuotationRepository.getQuotationViewPartDetailsForSalesOrder(id, userAuthentication.getBranchId()));

        apiResponse.setResult(spareQuotationViewDto);
        apiResponse.setMessage("Quotation Details for Sales Order");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getCustomerNameAutocomplete")
    public ResponseEntity<?> getCustomerNameAutocomplete(@RequestParam String customerName) {
    	System.out.println("customerName--->"+customerName+"==="+"delaerId--->"+ userAuthentication.getDealerId());
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(
                spareQuotationRepository.getQuotationCustomerNameAutocomplete(
                        customerName, userAuthentication.getDealerId(), userAuthentication.getUsername())
        );
        apiResponse.setMessage("Customer name Autocomplete");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/uploadExcel")
    public ResponseEntity<?> uploadExcel(MultipartFile file,
                                         @RequestPart String state,
                                         @RequestPart(required=false) String existingItems,
                                         @RequestParam String discountRate) {
        ApiResponse apiResponse = new ApiResponse();
        try {
        	if(discountRate==null || discountRate.equalsIgnoreCase("null") || discountRate.trim().equals(""))
        		discountRate="0.0";
            List<Map<String, Object>> excel =
            		spareQuotationService.uploadExcel(file, state,existingItems,discountRate);
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
}

