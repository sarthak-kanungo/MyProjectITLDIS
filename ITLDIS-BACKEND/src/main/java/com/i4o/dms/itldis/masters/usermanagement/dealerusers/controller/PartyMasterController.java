package com.i4o.dms.itldis.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.PartyMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.PartySearchDto;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto.PartySearchResponse;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.PartyMasterRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.itldis.salesandpresales.sales.exchangeInventory.domain.ExchangeInventory;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/master/partyMaster")
public class PartyMasterController {

	@Autowired
	private PartyMasterRepo partyMasterRepo;

	@Autowired
	private UserAuthentication userAuthentication;

	@PostMapping("/addParty")
	public ResponseEntity<?> addParty(@Valid @RequestBody PartyMaster partyMaster) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			
			partyMaster.setBranchId(partyMaster.getBranchId());
			partyMaster.setCreatedBy(userAuthentication.getLoginId());
			partyMaster.setCreatedDate(new Date());
			partyMaster.setLocality("");
			partyMaster.setPartyCode("");
			PartyMaster partyMaster1 = partyMasterRepo.save(partyMaster);
			partyMaster1.setPartyCode(NumberGenerator.generatePartyCode(partyMaster1.getId()));
			partyMasterRepo.save(partyMaster1);
			apiResponse.setMessage("Party Master Added successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Party master can't saved because of duplicate records");
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("party Master can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/partyCodeAuto")
	public ResponseEntity<?> partyCodeAuto(@RequestParam("partyCode") String partyCode) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> code = partyMasterRepo.findByPartyCodeContaining(partyCode,
				userAuthentication.getBranchId());
		apiResponse.setMessage("Party Code get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(code);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/partyNameAuto")
	public ResponseEntity<?> partyNameAuto(@RequestParam("partyName") String partyName,
			@RequestParam("categoryId") Long categoryId) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> name = partyMasterRepo.findByPartyNameContaining(partyName, categoryId);
		apiResponse.setMessage("Party Name get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(name);
		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/searchParty")
	public ResponseEntity<?> searchParty(@RequestBody PartySearchDto partySearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<PartySearchResponse> partySerach=partyMasterRepo.searchParty
				(userAuthentication.getUsername(),partySearchDto.getPartyCode(), partySearchDto.getPartyName(),
				partySearchDto.getPage(), partySearchDto.getSize());
		Long count =0l;
		if (partySerach != null && partySerach.size() > 0) {
			count = partySerach.get(0).getTotalRecords();
		}
		
		apiResponse.setResult(partySerach);
		apiResponse.setCount(count);
//		apiResponse.setCount(partyMasterRepo.searchPartyCount(partySearchDto.getPartyCode(),
//				partySearchDto.getPartyName(), partySearchDto.getPage(), partySearchDto.getSize()));

		return ResponseEntity.ok(apiResponse);
	}

	@PostMapping("/updateParty")
	public ResponseEntity<?> updateExchangeInventory(@RequestBody PartyMaster partyMaster) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			Long id = partyMaster.getId();
			PartyMaster partyMasterObject = partyMasterRepo.findById(id).get();
//			partyMasterObject.setBranchId(userAuthentication.getBranchId());
			partyMasterObject.setLastModifiedBy(userAuthentication.getLoginId());
			partyMasterObject.setLastModifiedDate(new Date());
			partyMasterObject.setAddress1(partyMaster.getAddress1());
			partyMasterObject.setAddress2(partyMaster.getAddress2());
			partyMasterObject.setAddress3(partyMaster.getAddress3());
			partyMasterObject.setAdharCardNumber(partyMaster.getAdharCardNumber());
			partyMasterObject.setCategory(partyMaster.getCategory());
			partyMasterObject.setCity(partyMaster.getCity());
			partyMasterObject.setContactName(partyMaster.getContactName());
			partyMasterObject.setCountry(partyMaster.getCountry());
			partyMasterObject.setDesignation(partyMaster.getDesignation());
			partyMasterObject.setDistrict(partyMaster.getDistrict());
			partyMasterObject.setEmail(partyMaster.getEmail());
			partyMasterObject.setGstNumber(partyMaster.getGstNumber());
			partyMasterObject.setLocality(partyMaster.getLocality());
			partyMasterObject.setMobileNumber(partyMaster.getMobileNumber());
			partyMasterObject.setPanNumber(partyMaster.getPanNumber());
			partyMasterObject.setPartyCategoryId(partyMaster.getPartyCategoryId());
			partyMasterObject.setPartyCode(partyMaster.getPartyCode());
			partyMasterObject.setPartyLocation(partyMaster.getPartyLocation());
			partyMasterObject.setPartyName(partyMaster.getPartyName());
			partyMasterObject.setPinCode(partyMaster.getPinCode());
			partyMasterObject.setPinId(partyMaster.getPinId());
			partyMasterObject.setPostOffice(partyMaster.getPostOffice());
			partyMasterObject.setState(partyMaster.getState());
			partyMasterObject.setTehsil(partyMaster.getTehsil());
			partyMasterObject.setTelephone(partyMaster.getTelephone());
			partyMasterObject.setTitle(partyMaster.getTitle());

			partyMasterRepo.saveAndFlush(partyMasterObject);
			apiResponse.setMessage("Party Master Updated successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Party master can't update because of duplicate records");
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("party Master can't updated");
		}
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("searchParty/{id}")
	public ResponseEntity<?> getEnquiryById(@PathVariable Long id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get party master by id");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(partyMasterRepo.findById(id));
		return ResponseEntity.ok(apiResponse);

	}
	@GetMapping("/getPartyCategories")
	public ResponseEntity<?> partyCategories() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> categories = partyMasterRepo.findByPartyCategories(userAuthentication.getLoginId().intValue());
		apiResponse.setMessage("Categories get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(categories);
		return ResponseEntity.ok(apiResponse);
	}
	@GetMapping("/getBranchCode")
	public ResponseEntity<?> getBranchCode(@RequestParam("branchCode") String branchCodeValue ) {
		ApiResponse apiResponse = new ApiResponse();
		System.out.println(userAuthentication.getUsername()+" .. "+branchCodeValue);
		List<Map<String, Object>> categories = partyMasterRepo.findBranchCode(branchCodeValue,userAuthentication.getUsername());
		apiResponse.setMessage("Categories get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(categories);
		return ResponseEntity.ok(apiResponse);
	}

}
