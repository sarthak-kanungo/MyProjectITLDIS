package com.i4o.dms.itldis.spares.inventorymanagement.machinestock.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.itldis.spares.inventorymanagement.machinestock.domain.MachineStockSearchModel;
import com.i4o.dms.itldis.spares.inventorymanagement.machinestock.domain.MachineStockSearchResponse;
import com.i4o.dms.itldis.spares.inventorymanagement.machinestock.repository.MachineStockRepository;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/spares/machinestock")
public class MachineStockController {

	@Autowired
	MachineStockRepository machineStockRepository;
	@Autowired
	UserAuthentication userAuthentication;
	
	@PostMapping("/searchMachineStock")
	public ResponseEntity<?> searchMachineStock(@RequestBody MachineStockSearchModel searchDto) {
		ApiResponse<List<MachineStockSearchResponse>> apiResponse = new ApiResponse<List<MachineStockSearchResponse>>();
		List<MachineStockSearchResponse> result = machineStockRepository
				.getMachineStockSearchResult( 
						searchDto.getDealerCode(),searchDto.getProduct(),
						searchDto.getSeries(),
						searchDto.getModel(),
						searchDto.getSubModel(),
						searchDto.getVariant(),
						searchDto.getItemNo(),
						searchDto.getEngineNo(),
						searchDto.getChassisNo(),
						searchDto.getOrgHierId(),
						userAuthentication.getUsername(),
						searchDto.getInvoiceFromDate(),	//Suraj--13-12-2022
						searchDto.getInvoiceToDate(),	//Suraj--13-12-2022
						searchDto.getGrnDoneFlag(),	//Suraj--02-01-2023
						"N", searchDto.getPage(), searchDto.getSize()
						);
		Long count = 0l;
		if (result != null && result.size() > 0) {
			count = result.get(0).getTotalCount();
		}
		apiResponse.setCount(count);
		apiResponse.setResult(result);
		apiResponse.setMessage("Search Machine Stock Records");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	
	  @PostMapping("/getMachineStockExcel")
	    public ResponseEntity<InputStreamResource> msExcelReport(@RequestBody MachineStockSearchModel msExcel, HttpServletResponse response) throws IOException{
	    	Integer size = Integer.MAX_VALUE-1;
	    	List<MachineStockSearchResponse> result = machineStockRepository
					.getMachineStockSearchResult( 
							msExcel.getDealerCode(),
							msExcel.getProduct(),
							msExcel.getSeries(),
							msExcel.getModel(),
							msExcel.getSubModel(),
							msExcel.getVariant(),
							msExcel.getItemNo(),
							msExcel.getEngineNo(),
							msExcel.getChassisNo(),
							msExcel.getOrgHierId(),
							userAuthentication.getUsername(),
							msExcel.getInvoiceFromDate(),	//Suraj--13-12-2022
							msExcel.getInvoiceToDate(),	//Suraj--13-12-2022
							msExcel.getGrnDoneFlag(),	//Suraj--02-01-2023
							"N", msExcel.getPage(), msExcel.getSize()
							);
	    	
	    	ByteArrayInputStream in = ExcelCellGenerator.machineStockExcelReport(result, userAuthentication.getBranchId());

	        response.setContentType("application/vnd.ms-excel");

	        HttpHeaders headers = new HttpHeaders();
	        String filename = "MSReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
	        headers.add("Content-Disposition", "attachment ; filename = "+filename);
	        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
	    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	    }
}
