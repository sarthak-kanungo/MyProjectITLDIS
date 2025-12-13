package com.i4o.dms.kubota.masters.usermanagement.dealerusers.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerEmployeeSearchDto;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto.DealerEmployeeSearchResponse;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryTransferHistoryRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.i4o.dms.kubota.storage.StorageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, methods = {
		RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT })
@RequestMapping(value = "/api/dealerEmployeeMaster")
public class DealerEmployeeMasterController {
	@Autowired
	private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

	@Autowired
	private EnquiryTransferHistoryRepo enquiryTransferHistoryRepo;

	@Autowired
	 private UserAuthentication userAuthentication;
	
	@Autowired
	private StorageService storageService;

	@GetMapping("/getSalesPerson")
	public ResponseEntity<?> getSalesPersonName() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get sales person name");
		apiResponse.setStatus(HttpStatus.OK.value());
		String ids = null;
		apiResponse.setResult(
				enquiryTransferHistoryRepo.getSalesPersonName(userAuthentication.getDealerEmployeeId(), ids));
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/getDealerCodes")
	public ResponseEntity<?> getDealerCodes() {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("get dealer codes successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(dealerEmployeeMasterRepo.getDealerCodes());
		return ResponseEntity.ok(apiResponse);
	}
	
	   @PostMapping(value = "/saveDealer")
	    public ResponseEntity<?> saveDealer(@RequestBody DealerEmployeeMaster dealerEmployeeMaster) {
	    	//dealerEmployeeMaster.setDealerId(1L);
	    	
	        ApiResponse apiResponse = new ApiResponse();
	        try
	        {	dealerEmployeeMaster.setActiveStatus("Y");
	        	dealerEmployeeMaster.setCreatedBy(userAuthentication.getLoginId());
	            dealerEmployeeMasterRepo.save(dealerEmployeeMaster);
	            apiResponse.setMessage("Dealer Employee Master successfully saved.");
	            apiResponse.setStatus(HttpStatus.OK.value());
	            try{
	            	dealerEmployeeMasterRepo.createUserForEmployee(dealerEmployeeMaster.getId(), userAuthentication.getUsername());
	            }catch(Exception ex){
	            	ex.printStackTrace();
	            }
	        }
	        catch (DataIntegrityViolationException e)
	        {
	        	e.printStackTrace();
	        	apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	            apiResponse.setMessage("Dealer Employee Master can't saved because of duplicate records");
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	            apiResponse.setMessage("Dealer Employee Master can't saved");
	        }
	        return ResponseEntity.ok(apiResponse);
	    }

/*
	@PostMapping(value = "/saveDealer", consumes = { "multipart/form-data" })
	public ResponseEntity<?> saveDealer(
			@ModelAttribute("dealerEmployeeMaster") DealerEmployeeMaster dealerEmployeeMaster) {
		// driving licence
		MultipartFile licenceImage = dealerEmployeeMaster.getDrivingLicenceDocument();
		String photoName = licenceImage.getOriginalFilename();
		String name = "licence_image_" + System.currentTimeMillis() + "_" + photoName;
		storageService.store(licenceImage, name);
		dealerEmployeeMaster.setDrivingLicencePath(photoName);

		// employee photo
		MultipartFile employeeImage = dealerEmployeeMaster.getEmployeePhotoDocument();
		String employeePhoto = employeeImage.getOriginalFilename();
		String employeePhotoName = "employee_photo_" + System.currentTimeMillis() + "_" + employeePhoto;
		storageService.store(employeeImage, employeePhotoName);
		dealerEmployeeMaster.setPhotoPath(employeePhoto);

		// Gov Id Proof
		MultipartFile IdProofImage = dealerEmployeeMaster.getGovIdProofDocument();
		String IdProof = IdProofImage.getOriginalFilename();
		String IdProofPhoto = "gov_id_proof_" + System.currentTimeMillis() + "_" + IdProof;
		storageService.store(IdProofImage, IdProofPhoto);
		dealerEmployeeMaster.setGovIdPath(IdProof);

		ApiResponse apiResponse = new ApiResponse();
		try {
			dealerEmployeeMasterRepo.save(dealerEmployeeMaster);
			apiResponse.setMessage("Dealer Employee Master successfully saved.");
			apiResponse.setStatus(HttpStatus.OK.value());
		} catch (DataIntegrityViolationException e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Dealer Employee Master can't saved because of duplicate records");
		} catch (Exception e) {
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			apiResponse.setMessage("Dealer Employee Master can't saved");
		}
		return ResponseEntity.ok(apiResponse);
	}
*/
	@GetMapping("/statusDropdown")
	public ResponseEntity<?> statusDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> status = dealerEmployeeMasterRepo.getStatusDropdown();
		apiResponse.setMessage("get status successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(status);
		return ResponseEntity.ok(apiResponse);

	}

	@GetMapping("/getEmployeeName")
	public ResponseEntity<?> getEmployeeName(@RequestParam("employeeCode") String employeeCode) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> name = dealerEmployeeMasterRepo.getEmployeeName(employeeCode);
		apiResponse.setMessage("Employee Name get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(name);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/employeeCodeAuto")
	public ResponseEntity<?> employeeCodeAuto(@RequestParam("dealerId") Integer dealerId,@RequestParam("employeeCode") String employeeCode) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> code = dealerEmployeeMasterRepo.findByEmployeeCodeContainingByDealerCode(dealerId,employeeCode);
		apiResponse.setMessage("Employee Code get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(code);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getEmployeeMasterdepartment")
	public ResponseEntity<?> employeeMasterdepartment() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> department = dealerEmployeeMasterRepo.getEmployeeMasterdepartment();
		apiResponse.setMessage("Employee Master department get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(department);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getEmployeeMasterDesignation")
	public ResponseEntity<?> employeeMasterDesignation() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> designation = dealerEmployeeMasterRepo.getEmployeeMasterDesignation();
		apiResponse.setMessage("Employee Master Designation get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(designation);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/highestQualificationDropdown")
	public ResponseEntity<?> highestQualificationDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> qualification = dealerEmployeeMasterRepo.getQualificationDropdown();
		apiResponse.setMessage("get Highest Qualification successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(qualification);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/bloodGroupDropdown")
	public ResponseEntity<?> bloodGroupDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> bloodGroup = dealerEmployeeMasterRepo.getBloodGroupDropdown();
		apiResponse.setMessage("Blood Group get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(bloodGroup);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/maritalStatusDropdown")
	public ResponseEntity<?> maritalStatusDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> status = dealerEmployeeMasterRepo.getMaritalStatusDropdown();
		apiResponse.setMessage("Marital Status get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(status);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/genderDropdown")
	public ResponseEntity<?> genderDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		//List<Map<String, Object>> gender = dealerEmployeeMasterRepo.getGenderDropdown();
		apiResponse.setMessage("gender get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		//apiResponse.setResult(gender);
		return ResponseEntity.ok(apiResponse);
	}

	//
	@GetMapping("/relationshipDropdown")
	public ResponseEntity<?> relationshipDropdown() {
		ApiResponse apiResponse = new ApiResponse();
		//List<Map<String, Object>> relationship = dealerEmployeeMasterRepo.getRelationshipDropdown();
		apiResponse.setMessage("relationship get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		//apiResponse.setResult(relationship);
		return ResponseEntity.ok(apiResponse);
	}

	@GetMapping("/employeeNameAuto")
	public ResponseEntity<?> employeeNameAuto(@RequestParam("firstName") String firstName) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> name = dealerEmployeeMasterRepo.findByFirstNameContaining(firstName);
		apiResponse.setMessage("Employee Name get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(name);
		return ResponseEntity.ok(apiResponse);
	}
/*
	@PostMapping("/searchDealerEmployee")
	public ResponseEntity<?> searchDealerEmployee(@RequestBody DealerEmployeeSearchDto dealerEmployeeSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully ");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(dealerEmployeeMasterRepo.searchDealerEmployee(dealerEmployeeSearchDto.getEmployeeCode(),
				dealerEmployeeSearchDto.getFirstName(), dealerEmployeeSearchDto.getPage(),
				dealerEmployeeSearchDto.getSize()));

		apiResponse.setCount(dealerEmployeeMasterRepo.searchDealerEmployeeCount(
				dealerEmployeeSearchDto.getEmployeeCode(), dealerEmployeeSearchDto.getFirstName(),
				dealerEmployeeSearchDto.getPage(), dealerEmployeeSearchDto.getSize()));

		return ResponseEntity.ok(apiResponse);
	}
*/
	
	@PostMapping("/searchDealerEmployee")
	public ResponseEntity<?> searchDealerEmployee(@RequestBody DealerEmployeeSearchDto dealerEmployeeSearchDto) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("data get successfully ");
		apiResponse.setStatus(HttpStatus.OK.value());
		List<DealerEmployeeSearchResponse> result = 
				(dealerEmployeeMasterRepo.searchDealerEmployee(userAuthentication.getUsername(),
						dealerEmployeeSearchDto.getEmployeeCode(), dealerEmployeeSearchDto.getMobileNo(), 
						dealerEmployeeSearchDto.getEmployeeName(), dealerEmployeeSearchDto.getState(),
						dealerEmployeeSearchDto.getDealerId(), dealerEmployeeSearchDto.getDepartmentId(),
						dealerEmployeeSearchDto.getPage(),	dealerEmployeeSearchDto.getSize()));
		apiResponse.setResult(result);
		Long count=0L;
		if (result!= null && result.size()>0) {
			count = result.get(0).getCount();
		}
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getDealerDetails")
	public ResponseEntity<?> dealerDetails(@RequestParam("dealerDetalis") String dealerDetalis,
			@RequestParam(value="isFor",required=false) String isFor) {
		ApiResponse<List<?>> apiResponse = new ApiResponse<>();
		String userName = userAuthentication.getUsername();
		apiResponse.setResult(dealerEmployeeMasterRepo.getDealerDetails(dealerDetalis,userName,isFor));
		apiResponse.setMessage("Dealer Details get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("/getReportingEmployee")
	public ResponseEntity<?> reportingEmployee(@RequestParam("dealerCode") String dealerCode,  @RequestParam("searchText") String searchText, @RequestParam("empCode") String empCode) {
		ApiResponse apiResponse = new ApiResponse();
		if(empCode.equals("null")) {
			empCode=null;
		}
		List<Map<String, Object>> name = dealerEmployeeMasterRepo.getReportingEmployee(dealerCode, searchText,empCode);
		apiResponse.setMessage("Reporting Employee get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(name);
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping("getViewEmployeeMaster")
	public ResponseEntity<?> viewEmployeeMaster(@RequestParam String dealerEmpId) {
		ApiResponse apiResponse = new ApiResponse();
		List result = new ArrayList()
; 		apiResponse.setMessage("Employee Details by id get Successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		DealerEmployeeMaster viewEmpDetails = dealerEmployeeMasterRepo.findByDealerEmpId(dealerEmpId);
		Map<String , Object> dealerDetails = dealerEmployeeMasterRepo.getDealerDetailsById(viewEmpDetails.getDealerId());
		if(viewEmpDetails.getActiveStatus()!=null && viewEmpDetails.getActiveStatus().equals("Y")) {
			viewEmpDetails.setActiveStatus("ACTIVE");
		}
		if(viewEmpDetails.getActiveStatus()==null || viewEmpDetails.getActiveStatus().equals("N")) {
			viewEmpDetails.setActiveStatus("INACTIVE");
		}
		result.add(viewEmpDetails);
		result.add(dealerDetails);
		apiResponse.setResult(result);
		return ResponseEntity.ok(apiResponse);

	}
	
//	@GetMapping("/getDuplicateEmpCode")
//	public ResponseEntity<?> duplicateEmpCode(@RequestParam("employeeCode") String employeeCode) {
//		ApiResponse apiResponse = new ApiResponse();
//		apiResponse.setResult(dealerEmployeeMasterRepo.getDuplicateEmpCode(employeeCode));
//		apiResponse.setMessage("Employee code check successfully");
//		apiResponse.setStatus(HttpStatus.OK.value());
//		return ResponseEntity.ok(apiResponse);
//	}
	
	@GetMapping("/employeeCodeForSearchPage")
	public ResponseEntity<?> employeeCodeForSearchPage(@RequestParam("mobilenumber") String mobilenumber,@RequestParam("employeeCode") String employeeCode) {
		ApiResponse apiResponse = new ApiResponse();
		List<Map<String, Object>> code = null;
		if(employeeCode.equals("null")) {
			code = dealerEmployeeMasterRepo.employeeCodeForSearchPage(userAuthentication.getUsername(),mobilenumber,null);
		}
		if(mobilenumber.equals("null")) {
			code = dealerEmployeeMasterRepo.employeeCodeForSearchPage(userAuthentication.getUsername(),null,employeeCode);
		}
		apiResponse.setMessage("Employee Code get successfully");
		apiResponse.setStatus(HttpStatus.OK.value());
		apiResponse.setResult(code);
		return ResponseEntity.ok(apiResponse);
	}
	@PostMapping(value="/updateEmployeeMaster")
	public ResponseEntity updateEmployeeMaster(@RequestBody DealerEmployeeMaster empMaster) {
		ApiResponse apiResponse = new ApiResponse();
		try {
			DealerEmployeeMaster empMasterUpdate = dealerEmployeeMasterRepo.getOne(empMaster.getId());
			empMasterUpdate.setDealerId(empMasterUpdate.getDealerId());
			empMasterUpdate.setEmployeeCode(empMaster.getEmployeeCode());
			empMasterUpdate.setTitle(empMaster.getTitle());
			empMasterUpdate.setFirstName(empMaster.getFirstName());
			empMasterUpdate.setMiddleName(empMaster.getMiddleName());
			empMasterUpdate.setLastName(empMaster.getLastName());
			empMasterUpdate.setEmailId(empMaster.getEmailId());
			empMasterUpdate.setMobileNo(empMaster.getMobileNo());
			empMasterUpdate.setDepartmentId(empMaster.getDepartmentId());
			empMasterUpdate.setDesignationId(empMaster.getDesignationId());
			empMasterUpdate.setReportingEmployeeId(empMaster.getReportingEmployeeId());
			empMasterUpdate.setAddress1(empMaster.getAddress1());
			empMasterUpdate.setAddress2(empMaster.getAddress2());
			empMasterUpdate.setAddress3(empMaster.getAddress3());
			empMasterUpdate.setPinCode(empMaster.getPinCode());
			empMasterUpdate.setLocality(empMaster.getLocality());
			empMasterUpdate.setTehsil(empMaster.getTehsil());
			empMasterUpdate.setCity(empMaster.getCity());
			empMasterUpdate.setState(empMaster.getState());
			empMasterUpdate.setCountry(empMaster.getCountry());
			empMasterUpdate.setActiveStatus(empMaster.getActiveStatus());
			empMasterUpdate.setAlternateMobileNo(empMaster.getAlternateMobileNo());
			empMasterUpdate.setEmergencyContactName(empMaster.getEmergencyContactName());
			empMasterUpdate.setEmergencyContactNo(empMaster.getEmergencyContactNo());
			empMasterUpdate.setDivision(empMaster.getDivision());
			empMasterUpdate.setLicenceType(empMaster.getLicenceType());
			empMasterUpdate.setDrivingLicenceNo(empMaster.getDrivingLicenceNo());
			empMasterUpdate.setDrivingLicenceExpiryDate(empMaster.getDrivingLicenceExpiryDate());
			empMasterUpdate.setBirthDate(empMaster.getBirthDate());
			empMasterUpdate.setAnniversaryDate(empMaster.getAnniversaryDate());
			empMasterUpdate.setHighestQualification(empMaster.getHighestQualification());
			empMasterUpdate.setMaritalStatus(empMaster.getMaritalStatus());
			empMasterUpdate.setBloodGroup(empMaster.getBloodGroup());
			empMasterUpdate.setSex(empMaster.getSex());
			empMasterUpdate.setJoiningDate(empMaster.getJoiningDate());
			empMasterUpdate.setLatestSalary(empMaster.getLatestSalary());
			empMasterUpdate.setLeavingDate(empMaster.getLeavingDate());
			empMasterUpdate.setPfNo(empMaster.getPfNo());
			empMasterUpdate.setPanNo(empMaster.getPanNo());
			empMasterUpdate.setEsiNo(empMaster.getEsiNo());
			empMasterUpdate.setBankAccountNo(empMaster.getBankAccountNo());
			empMasterUpdate.setBankName(empMaster.getBankName());
			empMasterUpdate.setBankBranch(empMaster.getBankBranch());
			empMasterUpdate.setCreatedDate(empMaster.getCreatedDate());
			empMasterUpdate.setLastModifiedDate(new Date());
			empMasterUpdate.setCreatedBy(empMaster.getCreatedBy());
			empMasterUpdate.setLastModifiedBy(userAuthentication.getLoginId());
			dealerEmployeeMasterRepo.save(empMasterUpdate);
			apiResponse.setMessage("Employee Master Updated Successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
			} catch (Exception e) {
				e.printStackTrace();
				apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				apiResponse.setMessage("Employee Master can't Updated");
		}
		return ResponseEntity.ok(apiResponse);
	}
	
	@GetMapping(value="/validateMobileNo")
	public ResponseEntity validateMobileNo(@RequestParam String mobileno,
			@RequestParam(required=false) Integer id
			){
		ApiResponse apiResponse = new ApiResponse();
		String mobileNo = this.dealerEmployeeMasterRepo.validateMobileNo(mobileno, id);
		apiResponse.setStatus(HttpStatus.OK.value());
		
		if(mobileNo!=null)
			apiResponse.setMessage("EXIST");
		else
			apiResponse.setMessage("NOTEXIST");
		return	ResponseEntity.ok(apiResponse);
	}
	
}
