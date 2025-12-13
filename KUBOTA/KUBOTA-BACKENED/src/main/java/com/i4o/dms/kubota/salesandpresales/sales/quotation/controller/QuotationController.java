package com.i4o.dms.kubota.salesandpresales.sales.quotation.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.domain.Quotation;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.domain.QuotationImplements;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.dto.QuotationDto;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.dto.QuotationSearchResponseDto;
import com.i4o.dms.kubota.salesandpresales.sales.quotation.repository.QuotationRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@Slf4j
@RequestMapping(value = "/api/sales/Quotation")
public class QuotationController {
	@Autowired
	private QuotationRepo quotationRepo;
	@Autowired
	private EnquiryRepo enquiryRepo;
	@Autowired
	private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
	@Autowired
	private UserAuthentication userAuthentication;
	@Autowired
	private DealerMasterRepo dealerMasterRepo;

	@PostMapping("/addQuotation")
	public ResponseEntity<?> addQuotation(@RequestBody Quotation quotation) {
		ApiResponse apiResponse = new ApiResponse();
		quotation.setBranchId(userAuthentication.getBranchId());
		Enquiry enquiry = enquiryRepo.getOne(quotation.getEnquiry().getId());
		quotation.setEnquiry(enquiry);
		quotation.setDealerEmployeeId(userAuthentication.getDealerEmployeeId());
		quotation.setLastModifiedBy(userAuthentication.getLoginId());
		quotation.setCreatedDate(new Date());
		quotationRepo.save(quotation);
		apiResponse.setMessage("Quotation created successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(quotationRepo.getQuotationNumberAfterQuotationCreation(quotation.getBranchId()));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getQuotation")
	public ResponseEntity<?> getQuotation(@RequestParam Long id) {
		ApiResponse apiResponse = new ApiResponse();
		if (id != null) {
			Quotation quotation = quotationRepo.findById(id).get();
			if (quotation.getQuotationImplements() != null) {
				for (QuotationImplements imp : quotation.getQuotationImplements()) {
					imp.setBasicPrice(imp.getQty() * imp.getRate());
				}
			}
			if (quotation.getRate() != null && quotation.getQty() != null) {
				quotation.setBasicPrice(quotation.getRate() * quotation.getQty());
			}
			apiResponse.setMessage("get quotation by quotation code");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setResult(quotation);
		} else {
			apiResponse.setMessage("no any quotation found");
			apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
			apiResponse.setResult("not found");
		}
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getQuotationSearch")
	public ResponseEntity<?> getQuotationSearch(@RequestParam(required = false) String quotationCode,
			@RequestParam(required = false) String sources, @RequestParam(required = false) String enquiryStatus,
			@RequestParam(required = false) String salesPerson, @RequestParam(required = false) String products,
			@RequestParam(required = false) String series, @RequestParam(required = false) String model,
			@RequestParam(required = false) String subModel, @RequestParam(required = false) String variant,
			@RequestParam(required = false) String itemNo, @RequestParam(required = false) String quotationFromDate,
			@RequestParam(required = false) String quotationToDate, @RequestParam(required = true) Integer page,
			@RequestParam(required = true) Integer size) {
//        ApiResponse apiResponse = new ApiResponse();

		Map<String, Object> map = new HashMap<>();
		

		List<QuotationSearchResponseDto> quotationSearchResponseDtoList = quotationRepo.getQuotationSearch(
				userAuthentication.getManagementAccess(), userAuthentication.getDealerId(),
				userAuthentication.getKubotaEmployeeId(), userAuthentication.getDealerEmployeeId(), quotationCode,
				sources, enquiryStatus, salesPerson, products, series, model, subModel, variant, itemNo,
				quotationFromDate, quotationToDate, page, size, userAuthentication.getUsername(), 'N');
		Long quotationSearchCount = 0L;
		if (quotationSearchResponseDtoList != null && quotationSearchResponseDtoList.size() > 0) {
			quotationSearchCount = quotationSearchResponseDtoList.get(0).getTotalCount();
		}

		map.put("message", "get enquiry search");
		map.put("status", HttpStatus.OK.value());
		map.put("result", quotationSearchResponseDtoList);
		map.put("count", quotationSearchCount);
		return ResponseEntity.ok(map);
	}

	@GetMapping("getEnquiryByEnquiryNo")
	public ResponseEntity<?> getEnquiryByEnquiryNo(@RequestParam String enquiryNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get quotation by quotation code");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(quotationRepo.getEnquiryByEnquiryNo(enquiryNo, userAuthentication.getBranchId()));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("getMachineDetailByItemNo")
	public ResponseEntity<?> getMachineDetailByItemNo(@RequestParam String itemNo) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get item ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(quotationRepo.getMachineDetailByItemNo(itemNo));
		return ResponseEntity.ok(apiResponse);
	}

	@SuppressWarnings("unchecked")
	@GetMapping("getQuotationCode")
	public ResponseEntity<?> getQuotationCode(@RequestParam String quotationCode) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get quotation code");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(quotationRepo.getQuotationCodes(quotationCode, userAuthentication.getBranchId(),
				userAuthentication.getUsername()));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("getQuotationByQuotationNumber")
	public ResponseEntity<?> getQuotationByQuotationNumber(@RequestParam String quotationNumber) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get quotation data by quotion number");
		apiResponse.setStatus(HttpStatus.OK.value());
		QuotationDto quotationDto = quotationRepo.findByQuotationNumber(quotationNumber);

		if (quotationDto.getQuotationImplements() != null) {
			for (com.i4o.dms.kubota.salesandpresales.sales.quotation.dto.QuotationDto.QuotationImplements imp : quotationDto
					.getQuotationImplements()) {
				imp.setBasicPrice(imp.getQty() * imp.getRate());
			}
		}
		if (quotationDto.getRate() != null && quotationDto.getQty() != null) {
			quotationDto.setBasicPrice(quotationDto.getRate() * quotationDto.getQty());
		}

		quotationDto.getEnquiry().setSalesPersonName(quotationDto.getEnquiry().getSalesPerson().getFirstName() + " "
				+ quotationDto.getEnquiry().getSalesPerson().getLastName());
		apiResponse.setResult(quotationDto);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("getAutoCompleteOpenEnquiryForQuotation")
	public ResponseEntity<?> getAutoCompleteOpenEnquiryForQuotation(@RequestParam String enquiryNumber) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get Auto Complete Open Enquiry For SpareQuotation ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(quotationRepo.getAutoCompleteOpenEnquiryForQuotation(enquiryNumber,
				userAuthentication.getDealerEmployeeId()));
		return ResponseEntity.ok(apiResponse);
	}

}
