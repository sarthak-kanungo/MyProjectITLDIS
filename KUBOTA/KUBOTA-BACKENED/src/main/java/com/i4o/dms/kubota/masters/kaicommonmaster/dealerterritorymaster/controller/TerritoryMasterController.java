package com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.domain.DealerTerritoryMapHdr;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.dto.DTMtSearchRequestDto;
import com.i4o.dms.kubota.masters.kaicommonmaster.dealerterritorymaster.service.TerritoryMasterService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
		methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/master/kaicommonmaster/territoryMaster")
public class TerritoryMasterController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private TerritoryMasterService territoryService;
	
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@PostMapping("/saveTerritoryMapping")
	public ResponseEntity<?> saveTerritoryMapping(@RequestBody DealerTerritoryMapHdr territoryMapHdr)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.saveDealerTerritoryMap(territoryMapHdr);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/saveTerritoryMapping Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("Territory Mapping Submit Unsuccessfull!");
			apiResponse.setResult(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author suraj.gaur
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/viewDealerTerritoryMapping")
	public ResponseEntity<?> viewDealerTerritoryMapping(@RequestParam String territoryNo)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.viewDealerTerritoryMapping(territoryNo);
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/viewDealerTerritoryMapping Exception: " + e.getMessage());
			e.printStackTrace();
			apiResponse.setMessage("View Territory Mapping Unsuccessfull!");
			apiResponse.setResult(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getAllDealers")
	public ResponseEntity<?> getAllDealers(@RequestParam("dealer") String dealer)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getAllDealers(dealer);
			apiResponse.setMessage("Get all Dealers Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getAllDealers Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getBranchByDealerId")
	public ResponseEntity<?> getBranchByDealerId(@RequestParam(value="dealerId")Long dealerId)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getBranchByDealerId(dealerId);
			apiResponse.setMessage("Get all Branch By Dealers Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getBranchByDealerId Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getCountry")
	public ResponseEntity<?> getCountry()
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getCountry();
			apiResponse.setMessage("Get country Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getCountry Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getState")
	public ResponseEntity<?> getState(@RequestParam(value="dealerId")Long dealerId)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getState(dealerId);
			apiResponse.setMessage("Get state Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getState Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getDistrict")
	public ResponseEntity<?> getDistrict(@RequestParam(value="stateId")Long stateId)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getDistrict(stateId);
			apiResponse.setMessage("Get District By State Id Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getDistrict Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	/**
	 * @author mahesh.kumar
	 * @return ResponseEntity<?>
	 */
	@GetMapping("/getTehsil")
	public ResponseEntity<?> getTehsil(@RequestParam(value="districtId")Long districtId)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.getTehsil(districtId);
			apiResponse.setMessage("Get Tehsil By District Id Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getTehsil Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @param requestDto
	 * @return ResponseEntity<?>
	 * @apiNote Searching Dealer Territory Mapping 
	 */
	@PostMapping("/searchDTM") //DTM = Dealer Territory Mapping
	public ResponseEntity<?> searchDTM(@RequestBody DTMtSearchRequestDto requestDto)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.searchDTM(requestDto);
			apiResponse.setMessage("Get Dealer Territory Mapping Search Details Successful");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/searchDTM Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @param territoryNo
	 * @return ResponseEntity<?>
	 * @apiNote this method is for auto search territory number
	 */
	@GetMapping("/autoSearchTerritoryNo")
	public ResponseEntity<?> autoSearchTerritoryNo(@RequestParam("territoryNo") String territoryNo)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.autoSearchTerritoryNo(territoryNo);
			apiResponse.setMessage("Auto Search Territory No Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoSearchTerritoryNo Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
	
	/**
	 * @author mahesh.kumar
	 * @param branchName
	 * @return ResponseEntity<?>
	 * @apiNote this method is for auto search branch name
	 */
	@GetMapping("/autoSearchBranchName")
	public ResponseEntity<?> autoSearchBranchName(@RequestParam("branchName") String branchName)
	{
		try {
			ApiResponse<?> apiResponse = territoryService.autoSearchBranchName(branchName);
			apiResponse.setMessage("Auto Search Branch Name Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			
			return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
			ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/autoSearchBranchName Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
	}
	
}
