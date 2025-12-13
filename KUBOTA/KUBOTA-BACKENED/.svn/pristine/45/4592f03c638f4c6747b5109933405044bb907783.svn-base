package com.i4o.dms.kubota.spares.inventorymanagement.currentstock.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.inventorymanagement.currentstock.dto.CurrentStockDto;
import com.i4o.dms.kubota.spares.inventorymanagement.currentstock.dto.CurrentStockResponse;
import com.i4o.dms.kubota.spares.inventorymanagement.currentstock.repository.CurrentStockRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;


@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/spares/currentstock")
public class CurrentStockController {

	@Autowired
	private CurrentStockRepo currentStockRepo;

	@Autowired
	private UserAuthentication userAuthentication;


//	@GetMapping("searchAutoTransferNumber")
//	public ResponseEntity<?> searchAutoTransferNumber(@RequestParam("transferNo")String transferNo){
//		ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Transfer No search");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(btbtDTLRepository.searchAutoTransferNumber(transferNo,userAuthentication.getUsername()));
//        return ResponseEntity.ok(apiResponse);
//	}
//	
	@PostMapping("/searchCurrentStock")
	public ResponseEntity<?> searchCurrentStock(@RequestBody CurrentStockDto searchDto) {
		ApiResponse apiResponse = new ApiResponse();
		List<CurrentStockResponse> result = currentStockRepo
				.getCurrentStockSearchResult(searchDto.getItemNo(), userAuthentication.getBranchId());
		Long count = 0l;
		if (result != null && result.size() > 0) {
			count = (long) result.size();
		}
		apiResponse.setCount(count);
		apiResponse.setResult(result);
		apiResponse.setMessage("Search Current Stock Records");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@PostMapping("/downloadCurrentStockExcelReport")
	public ResponseEntity<InputStreamResource> downloadCurrentStockExcelReport(@RequestBody CurrentStockDto searchDto,HttpServletResponse response) throws IOException{
	
		Integer size = Integer.MAX_VALUE-1;
		List<CurrentStockResponse> result = currentStockRepo.getCurrentStockSearchResult(
				searchDto.getItemNo(),
				userAuthentication.getBranchId());
		ByteArrayInputStream byteInputStream = ExcelCellGenerator.currentStockExcelReport(result);
        response.setContentType("application/vnd.ms-excel");
        HttpHeaders headers = new HttpHeaders();
        String filename = "CurrentStockDetails_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(byteInputStream));
	}
}
