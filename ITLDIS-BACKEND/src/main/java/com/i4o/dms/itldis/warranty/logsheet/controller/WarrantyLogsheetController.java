package com.i4o.dms.itldis.warranty.logsheet.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.repository.SparesPartMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
import com.i4o.dms.itldis.warranty.deliverychallan.repository.WarrantyDeliveryChallanRepo;
import com.i4o.dms.itldis.warranty.logsheet.domain.WarrantyLogsheet;
import com.i4o.dms.itldis.warranty.logsheet.dto.LogsheetResponseDto;
import com.i4o.dms.itldis.warranty.logsheet.dto.LogsheetSearchDto;
import com.i4o.dms.itldis.warranty.logsheet.dto.WarrantyLogsheetViewDto;
import com.i4o.dms.itldis.warranty.logsheet.repository.WarrantyLogsheetRepo;
import com.i4o.dms.itldis.warranty.logsheet.service.WarrantyLogsheetService;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/logsheet")
public class WarrantyLogsheetController {

	@Autowired
	private UserAuthentication userAuthentication;
    @Autowired
    private WarrantyLogsheetRepo warrantyLogsheetRepo;
    @Autowired
    private WarrantyPcrRepo warrantyPcrRepo;
    @Autowired
    private WarrantyLogsheetService warrantyLogsheetService;

    @Autowired
    private WarrantyDeliveryChallanRepo warrantyDeliveryChallanRepo;

    @Autowired
    private SparesPartMasterRepo sparesPartMasterRepo;	//Suraj--20-02-2023

    @PostMapping("/saveWarrantyLogsheet")
    public ResponseEntity<?> saveWarrantyLogsheet(@RequestPart(value = "warrantyLogsheet") WarrantyLogsheet warrantyLogsheet,@RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();
        warrantyLogsheet.setLogsheetDate(new Date());
        apiResponse=warrantyLogsheetService.saveWarrantyLogsheet(warrantyLogsheet,multipartFileList);
        apiResponse.setMessage("warranty log sheet save successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/saveWarrantyLogsheetTest")
    public ResponseEntity<?> saveWarrantyLogsheetTest(@RequestBody WarrantyLogsheet warrantyLogsheet) {
        ApiResponse apiResponse = new ApiResponse();
        warrantyLogsheet.setCreatedBy(userAuthentication.getLoginId());
        apiResponse.setResult(warrantyLogsheetRepo.save(warrantyLogsheet));
        apiResponse.setMessage("warranty log sheet save successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/dropDownLogsheetType")
    public ResponseEntity<?> dropDownFieldCondition() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyLogsheetRepo.dropDownLogsheetType());
        apiResponse.setMessage("drop down logsheet type ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/warrantyLogsheetViewByLogsheetNo")
    public ResponseEntity<?> warrantyLogsheetView(@RequestParam String LogsheetNo) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyLogsheetViewDto dto= warrantyLogsheetRepo.findByLogsheetNo(LogsheetNo);
        
        //Suraj--20-02-2023-START
        if(dto != null && dto.getLogsheetFailurePartInfo() != null) {
        	for(int i = 0; i < dto.getLogsheetFailurePartInfo().size(); i++) {
        		dto.getLogsheetFailurePartInfo().get(i).setSparePartMaster(sparesPartMasterRepo.findByItemNo(dto.getLogsheetFailurePartInfo().get(i).getSparePartMasterId()));
        	}
        }
        //Suraj--20-02-2023-END
        
        if(dto!=null && dto.getServiceJobCard()!=null){
        	Long branchId = dto.getServiceJobCard().getBranchId();
        	Map<String,Object> dealerDtl = warrantyDeliveryChallanRepo.getDealerDetails(branchId);
        	dto.getServiceJobCard().setServiceDealer((String)dealerDtl.get("serviceDealer"));
        	dto.getServiceJobCard().setServiceDealerCity((String)dealerDtl.get("serviceDealerAddress"));

        	Map<String,Object> map = warrantyPcrRepo.getPcrNumberByJobCardId(dto.getServiceJobCard().getId());
        	if(map!=null){
        		dto.getServiceJobCard().setPcrNo((String)map.get("pcrNumber"));
        	}
        }
        
        apiResponse.setResult(dto);
        apiResponse.setMessage("Warranty Logsheet Details get Successfull!");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchLogsheetNo")
    public ResponseEntity<?> searchLogsheetNo(@RequestParam String logsheetNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyLogsheetRepo.searchLoghseetNo(logsheetNo));
        apiResponse.setMessage("search logsheet No ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/close")
    public ResponseEntity<?> close(@RequestParam Integer id) {
        ApiResponse apiResponse = new ApiResponse();
        warrantyLogsheetRepo.closeLoghseet(userAuthentication.getLoginId(), id);
        apiResponse.setMessage("Logsheet Closed successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchCustomerMobileNo")
    public ResponseEntity<?> searchCustomerMobileNumber(@RequestParam String mobileNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyLogsheetRepo.searchCustomerMobileNo(mobileNo));
        apiResponse.setMessage("search customer mobile no ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/logsheetSearch")
    public ResponseEntity<?> logsheetSearch(@RequestBody LogsheetSearchDto logsheetSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        List<LogsheetResponseDto> logsheetResponseDto=warrantyLogsheetRepo.logsheetSearch(logsheetSearchDto.getLogsheetNo(),logsheetSearchDto.getLogsheetType(),
                logsheetSearchDto.getStatus(),logsheetSearchDto.getJobCardNo(),logsheetSearchDto.getChassisNo(),logsheetSearchDto.getLogsheetFromDate(),
                logsheetSearchDto.getLogsheetToDate(),logsheetSearchDto.getModel(),logsheetSearchDto.getFailureType(),logsheetSearchDto.getMobileNo(),
                logsheetSearchDto.getRegistrationNo(),logsheetSearchDto.getJobCardFromDate(),logsheetSearchDto.getJobCardToDate(),logsheetSearchDto.getPage(),
                logsheetSearchDto.getSize(), userAuthentication.getUsername());
        Long count=0l;
        if(logsheetResponseDto!=null && !logsheetResponseDto.isEmpty()){
        	count = logsheetResponseDto.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        apiResponse.setResult(logsheetResponseDto);
        apiResponse.setMessage("search log sheet  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    
    @PostMapping("/downloadLogsheetExcelReport")
    public ResponseEntity<InputStreamResource> logsheetExcelReport(@RequestBody LogsheetSearchDto logsheetExcel, HttpServletResponse response) throws IOException {
//        Integer size = Integer.MAX_VALUE - 1;
        List<LogsheetResponseDto> logsheetData = warrantyLogsheetRepo.logsheetSearch(
        		logsheetExcel.getLogsheetNo(), 
        		logsheetExcel.getLogsheetType(),
                logsheetExcel.getStatus(), 
                logsheetExcel.getJobCardNo(), 
                logsheetExcel.getChassisNo(), 
                logsheetExcel.getLogsheetFromDate(),
                logsheetExcel.getLogsheetToDate(), 
                logsheetExcel.getModel(), 
                logsheetExcel.getFailureType(), 
                logsheetExcel.getMobileNo(),
                logsheetExcel.getRegistrationNo(), 
                logsheetExcel.getJobCardFromDate(), 
                logsheetExcel.getJobCardToDate(), 
                logsheetExcel.getPage(),
                logsheetExcel.getSize(), 
                logsheetExcel.getUsercode());

        ByteArrayInputStream in = ExcelCellGenerator.logsheetExcelReport(logsheetData);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Logsheet_Report_" + (Calendar.getInstance()).getTimeInMillis() + ".xlsx";
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }



}
