package com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.controller;

import static com.i4o.dms.kubota.configurations.Constants.ONE_YEAR_OLD;
import static com.i4o.dms.kubota.configurations.Constants.TWO_YEAR_OLD;
import static com.i4o.dms.kubota.configurations.Constants.THREE_YEAR_OLD;
import static com.i4o.dms.kubota.configurations.Constants.MORE_THAN_3_YEARS;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListReqDto;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.AuctionPartsListResDto;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSaveReqDto;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.dto.NonMovInvSearchReqDto;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.repository.SpareStockCurrentRepo;
import com.i4o.dms.kubota.spares.inventorymanagement.nonMovementInventory.service.NonMovInventoryService;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.ResponseSearchPurchaseOrder;
import com.i4o.dms.kubota.spares.purchase.sparepurchaseorder.dto.SearchSparePurchaseOrder;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.utils.ExcelCellGenerator;

/**
 * @author suraj.gaur
 */
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/spares/nonMovInv")
public class NonMovInventoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private NonMovInventoryService inventoryService;
	
	@Autowired
	private SpareStockCurrentRepo stockCurrentRepo;
	
	/**
	 * @author suraj.gaur
	 * @param claimNoString
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/saveNonMovInventory")
	public ResponseEntity<?> saveNonMovInventory(@RequestBody NonMovInvSaveReqDto reqDto)
	{
		try {
			ApiResponse<?> apiResponse = inventoryService.saveNonMovInventory(reqDto.getStockCurrent());
			apiResponse.setMessage("Saving Non-Movement Inventory Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/saveNonMovInventory Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getAgingType")
	public ResponseEntity<?> getAgingType()
	{
		try {
			ApiResponse<List<String>> apiResponse = new ApiResponse<>();
			
			List<String> claimType = new ArrayList<>();
			claimType.add(ONE_YEAR_OLD);
			claimType.add(TWO_YEAR_OLD);
			claimType.add(THREE_YEAR_OLD);
			claimType.add(MORE_THAN_3_YEARS);
			
			apiResponse.setResult(claimType);
			apiResponse.setMessage("Get Aging Types Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getAgingType Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/nonMovInvSearch")
	public ResponseEntity<?> nonMovInvSearch(@RequestBody NonMovInvSearchReqDto reqDto)
	{
		try {
			ApiResponse<?> apiResponse = inventoryService.nonMovInvSearch(reqDto);
			apiResponse.setMessage("Non Movement Inventory Search Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/nonMovInvSearch Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/autoGetNonMovSpareItems")
	public ResponseEntity<?> autoGetNonMovSpareItems(@RequestParam String itemStr)
	{
		try {
			ApiResponse<?> apiResponse = inventoryService.autoGetNonMovSpareItems(itemStr);
			apiResponse.setMessage("Autoget Non Movement Inventory Item no Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/autoGetNonMovSpareItems Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @param reqestDto
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/auctionPartsList")
	public ResponseEntity<?> auctionPartsList(@RequestBody AuctionPartsListReqDto reqestDto)
	{
		try {
			ApiResponse<?> apiResponse = inventoryService.auctionPartsList(reqestDto);
			apiResponse.setMessage("Get Auction Parts List Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/auctionPartsList Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @param reqestDto
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/nonMovInvSearchForHo")
	public ResponseEntity<?> nonMovInvSearchForHo(@RequestBody AuctionPartsListReqDto reqestDto)
	{
		try {
			ApiResponse<?> apiResponse = inventoryService.nonMovInvSearchForHo(reqestDto);
			apiResponse.setMessage("Get Non Movement inventory Search For Ho Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/nonMovInvSearchForHo Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	@PostMapping("/nonMovInvSearchForHoReportExcel")
    public ResponseEntity<InputStreamResource> nonMovInvSearchForHoReportExcel(@RequestBody AuctionPartsListReqDto requestReport,
            HttpServletResponse response) throws IOException{
    	
    	Integer size = requestReport.getSize();//Integer.MAX_VALUE-1;
    	
    	List<AuctionPartsListResDto> list = stockCurrentRepo.nonMovInvSearchForHo(
    			requestReport.getIsAuction(),
    			requestReport.getPage(),
                size);
    	
    	ByteArrayInputStream in = ExcelCellGenerator.nonMovInvSearchForHoReportExcel(list);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "NonMovInvSearchForHoReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
}
