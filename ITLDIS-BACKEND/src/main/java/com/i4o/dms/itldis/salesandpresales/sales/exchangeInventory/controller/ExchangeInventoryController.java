package com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectCropGrown;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectMachineryDetail;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectSoilType;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryAttachmentsImages;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquiryPartialSearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquirySearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.Repository.ExchangeInventoryRepo;
import com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.domain.ExchangeInventory;
import com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.dto.ExchangeInventoryDto;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/salesandpresales/exchangeInventory")
public class ExchangeInventoryController {

	@Autowired
	private ExchangeInventoryRepo exchangeInventoryRepo;

	@Autowired
	private EnquiryRepo enquiryRepo;
	
	@Autowired
	private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

	@Autowired
	private UserAuthentication userAuthentication;

	@Autowired
	private DealerMasterRepo dealerMasterRepo;

	@Autowired
	private CustomerMasterRepo customerMasterRepo;

	private Logger logger = LoggerFactory.getLogger(ExchangeInventoryController.class);

	@GetMapping("/getExchangeInventorySearch")
	public ResponseEntity<?> getEnquiryInventorySearch(@RequestParam(required = true) Long userId,
			@RequestParam(required = false) String enquiryNumber, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam(required = false) String status,
			@RequestParam(required = false, defaultValue = "0") Long hierId, @RequestParam Integer page,
			@RequestParam Integer size) {

		Map<String, Object> map = new HashMap<>();

		Long getEnquirySearchCount = 0L;

		List<ExchangeInventoryDto> exchangeInventorySearchResponseDto = exchangeInventoryRepo
				.getExchangeInventorySearch(userAuthentication.getManagementAccess(), userAuthentication.getBranchId(),
						userAuthentication.getKubotaEmployeeId(), userAuthentication.getDealerEmployeeId(),
						enquiryNumber, fromDate, toDate, status, userAuthentication.getUsername(), 'N', hierId, page,
						size);

		if (exchangeInventorySearchResponseDto != null && exchangeInventorySearchResponseDto.size() > 0) {
			getEnquirySearchCount = exchangeInventorySearchResponseDto.get(0).getTotalRecords();
		}
		map.put("result", exchangeInventorySearchResponseDto);
		map.put("message", "get enquiry search");
		map.put("status", HttpStatus.OK.value());
		map.put("count", getEnquirySearchCount);

		return ResponseEntity.ok(map);
	}

	@PostMapping("/updateExchangeInventory")
	public ResponseEntity<?> updateExchangeInventory(@RequestBody ExchangeInventory exchangeInventoryObject) {
		ApiResponse apiResponse = new ApiResponse();
		
		Long  id= exchangeInventoryObject.getOldVehId();
		ExchangeInventory exchangeInventory = exchangeInventoryRepo.findById(id).get();
		exchangeInventory.setBranchId(userAuthentication.getBranchId());
		exchangeInventory.setModifiedby(userAuthentication.getLoginId());
		exchangeInventory.setModifieddate(new Date());
		exchangeInventory.setBuyerContactNo(exchangeInventoryObject.getBuyerContactNo());
		exchangeInventory.setBuyerName(exchangeInventoryObject.getBuyerName());
		exchangeInventory.setSellingprice(Double.valueOf(exchangeInventoryObject.getSellingprice()));
		exchangeInventory.setSaleremarks(exchangeInventoryObject.getSaleremarks());
		exchangeInventory.setSaledate(new Date());
		exchangeInventory.setStatus("Sold");
		exchangeInventoryRepo.save(exchangeInventory);
		apiResponse.setMessage("Enquiry saved successfully  ");
		apiResponse.setStatus(HttpStatus.OK.value());
		Enquiry enquiry=enquiryRepo.findById(exchangeInventory.getEnquiry().getId()).get();
		String enquiryNumber=enquiry.getEnquiryNumber();
		System.out.println(enquiryNumber);
		apiResponse.setResult(enquiryNumber);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("getExchangeInventorySearch/{id}")
	public ResponseEntity<?> getEnquiryInventoryById(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get exchange inventory by id");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(exchangeInventoryRepo.findById(id));
		return ResponseEntity.ok(apiResponse);

	}

}
