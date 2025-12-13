package com.i4o.dms.kubota.masters.dealermaster.customermaster.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMasterRequest;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.domain.CustomerMasterRequestApproval;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.dto.CustomerMasterSearchDto;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.dto.CustomerMasterSearchResponseDto;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterChangeRequestApprovalRepo;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterChangeRequestRepo;
import com.i4o.dms.kubota.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrApproval;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/customerMaster")
public class CustomerMasterController {

    private Map<String, Object> map = new HashMap<>();

    @Autowired
    private CustomerMasterRepo customerMasterRepo;
    
    @Autowired
    private CustomerMasterChangeRequestRepo customerMasterChangeRequestRepo;
    
    @Autowired
    private CustomerMasterChangeRequestApprovalRepo customerMasterChangeRequestApprovalRepo;
    
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmpRepo;

    @PostMapping(value = "/addCustomer")
    public ResponseEntity<?> addCustomer(@RequestBody CustomerMaster customerMaster) {
        ApiResponse apiResponse = new ApiResponse();
        CustomerMaster customerMaster1 = customerMasterRepo.save(customerMaster);
        apiResponse.setMessage("Customer  Added Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(customerMaster1);
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/addCustomerChangeRequest")
    public ResponseEntity<?> addCustomerChangeRequest(@RequestBody CustomerMasterRequest customerMaster) {
        ApiResponse apiResponse = new ApiResponse();
        customerMaster.setCreatedBy(userAuthentication.getLoginId());
        customerMaster.setCreatedDate(new Date());
        customerMaster.setStatus("Approval Pending");
        //CustomerMasterRequest masterRequest = customerMasterChangeRequestRepo.save(customerMaster);
        
        //Suraj-29-03-2023
        CustomerMasterRequest masterRequest = null;
        Optional<CustomerMasterRequest> customerReq = customerMasterChangeRequestRepo.getByCustomerId(customerMaster.getCustomerId());
        
        if (!customerReq.isPresent()) { // new entity
        	masterRequest = customerMasterChangeRequestRepo.save(customerMaster);
        	
        	final CustomerMasterRequest fMasterRequest = masterRequest;
        	List<Map<String,Object>> list = customerMasterChangeRequestRepo.getCustomerChangeRequestApprovalHierarchyLevel(userAuthentication.getDealerId());
            List<CustomerMasterRequestApproval> approvalsHier = new ArrayList<>();
            list.forEach(designationHierarchy -> {
				CustomerMasterRequestApproval approval = new CustomerMasterRequestApproval();
				approval.setCustReqId(fMasterRequest.getCustReqId());
				approval.setApproverLevelSeq((Integer) designationHierarchy.get("approver_level_seq"));
				approval.setDesignationLevelId((BigInteger) designationHierarchy.get("designation_level_id"));
				approval.setGrpSeqNo((Integer) designationHierarchy.get("grp_seq_no"));
				approval.setIsfinalapprovalstatus((Character) designationHierarchy.get("isFinalApprovalStatus"));
				approval.setApprovalStatus((String) designationHierarchy.get("approvalStatus"));
				approval.setRejectedFlag('N');
				approvalsHier.add(approval);
			});
            customerMasterChangeRequestApprovalRepo.saveAll(approvalsHier);
            
        } else {
        	customerReq.get().setCustReqId(customerReq.get().getCustReqId());
        	customerReq.get().setCustomerId(customerMaster.getCustomerId());
        	customerReq.get().setReqApprovalStatus("Approval Pending");
        	customerReq.get().setStatus(customerMaster.getStatus());
        	customerReq.get().setRecordType(customerMaster.getRecordType());
        	customerReq.get().setCustomerType(customerMaster.getCustomerType());
        	customerReq.get().setCompanyName(customerMaster.getCompanyName());
        	customerReq.get().setTitle(customerMaster.getTitle());
        	customerReq.get().setFirstName(customerMaster.getFirstName());
        	customerReq.get().setMiddleName(customerMaster.getMiddleName());
        	customerReq.get().setLastName(customerMaster.getLastName());
        	customerReq.get().setFatherName(customerMaster.getFatherName());
        	customerReq.get().setStd(customerMaster.getStd());
        	customerReq.get().setTelephoneNo(customerMaster.getTelephoneNo());
        	customerReq.get().setWhatsAppNo(customerMaster.getWhatsAppNo());
        	customerReq.get().setLanguage(customerMaster.getLanguage());
        	customerReq.get().setEmailId(customerMaster.getEmailId());
        	customerReq.get().setAadharNo(customerMaster.getAadharNo());
        	customerReq.get().setDob(customerMaster.getDob());
        	customerReq.get().setMobileNumber(customerMaster.getMobileNumber());
        	customerReq.get().setAlternateMobileNo(customerMaster.getAlternateMobileNo());
        	customerReq.get().setAnniversaryDate(customerMaster.getAnniversaryDate());
        	customerReq.get().setGstNo(customerMaster.getGstNo());
        	customerReq.get().setPancardNo(customerMaster.getPancardNo());
        	customerReq.get().setAddress1(customerMaster.getAddress1());
        	customerReq.get().setAddress2(customerMaster.getAddress2());
        	customerReq.get().setAddress3(customerMaster.getAddress3());
        	customerReq.get().setPinCode(customerMaster.getPinCode());
        	customerReq.get().setPinId(customerMaster.getPinId());
        	customerReq.get().setPostOffice(customerMaster.getPostOffice());
        	customerReq.get().setCity(customerMaster.getCity());
        	customerReq.get().setTehsil(customerMaster.getTehsil());
        	customerReq.get().setDistrict(customerMaster.getDistrict());
        	customerReq.get().setState(customerMaster.getState());
        	customerReq.get().setCountry(customerMaster.getCountry());
        	customerReq.get().setOccupation(customerMaster.getOccupation());
        	customerReq.get().setLandInAcres(customerMaster.getLandInAcres());
        	customerReq.get().setChassisNo(customerMaster.getChassisNo());
//        	customerReq.get().setProspectSoilTypes(customerMaster.getProspectSoilTypes());	//For all these 3 (this and below 2) we need to delete the data from table and then need to add each entry in this object with cust_req_id and then need to insert data into table as a fresh entity, when second and further time if dealer want to request for this then. 
//        	customerReq.get().setProspectCropNames(customerMaster.getProspectCropNames());
//        	customerReq.get().setProspectMachineryDetails(customerMaster.getProspectMachineryDetails());
        	customerReq.get().setLastModifiedBy(userAuthentication.getLoginId());
        	customerReq.get().setLastModifiedDate(new Date());
			masterRequest = customerMasterChangeRequestRepo.save(customerReq.get());
		}
        //Suraj-29-03-2023
        
//        List<Map<String,Object>> list = customerMasterChangeRequestRepo.getCustomerChangeRequestApprovalHierarchyLevel(userAuthentication.getDealerId());
//       
//        List<CustomerMasterRequestApproval> approvalsHier = new ArrayList<>();
//        list.forEach(designationHierarchy -> {
//        	CustomerMasterRequestApproval approval = new CustomerMasterRequestApproval();
//                    approval.setCustReqId(masterRequest.getCustReqId());
//                    approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
//                    approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
//                    approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
//                    approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
//                    approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
//                    approval.setRejectedFlag('N');
//                    approvalsHier.add(approval);
//                });
//        customerMasterChangeRequestApprovalRepo.saveAll(approvalsHier);
        
        apiResponse.setMessage("Customer Change Request Saved Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(masterRequest);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/submitChangeRequest")
    public ResponseEntity<?> submitChangeRequest(@RequestBody CustomerMasterRequest customerMaster){
    	ApiResponse apiResponse = new ApiResponse();
    	String message = customerMasterChangeRequestRepo.changeRequestApproval(customerMaster.getCustReqId(), customerMaster.getRemarks(), customerMaster.getApprovalType(), 
    			userAuthentication.getUsername(), userAuthentication.getKubotaEmployeeId());
    	apiResponse.setMessage("Customer Change Request Approval");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(message);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getCustomerMaster")
    public ResponseEntity<?> getCustomerMaster(@RequestParam String customerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get customer by Code");
        apiResponse.setStatus(HttpStatus.OK.value());
        CustomerMaster customer = customerMasterRepo.findByCustomerCode(customerCode);
        if(customer!=null){
        	List<Map<String, Object>> vehicleDetails = customerMasterRepo.getVehicleDetails(customerCode);
        	customer.setVehicleDetails(vehicleDetails);
        }
        apiResponse.setResult(customer);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getCustomerMasterChangeRequest")
    public ResponseEntity<?> getCustomerMasterChangeRequest(@RequestParam String customerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get customer by Code");
        apiResponse.setStatus(HttpStatus.OK.value());
        Long id = customerMasterRepo.getCustomerIdByCode(customerCode);
        apiResponse.setResult(customerMasterChangeRequestRepo.findByCustomerId(id));
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/customerMasterSearch")
    public ResponseEntity<?> customerMasterSearch(@RequestBody CustomerMasterSearchDto customerMaster) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer Master Search");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<CustomerMasterSearchResponseDto> list = customerMasterRepo.getCustomerMasterSearch(customerMaster.getCustomerCode(), customerMaster.getMobileNo(), customerMaster.getPage(), customerMaster.getSize(), userAuthentication.getUsername());
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }
    
    
    @PostMapping(value = "/customerMasterApprovalSearch")
    public ResponseEntity<?> customerMasterApprovalSearch(@RequestBody CustomerMasterSearchDto customerMaster) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer Master Search");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<CustomerMasterSearchResponseDto> list = customerMasterRepo.getCustomerMasterApprovalSearch(customerMaster.getPage(), customerMaster.getSize(), userAuthentication.getUsername());
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }
    
    @ApiOperation(value = "", notes = "")
    @PutMapping(value = "/updateCustomerMaster")
    public ResponseEntity<?> updateCustomerMaster(@RequestBody CustomerMaster customerMaster) {
        ApiResponse apiResponse = new ApiResponse();

        Optional<CustomerMaster> customerMaster1 = customerMasterRepo.getByCustomerCode(customerMaster.getCustomerCode());
        if (customerMaster1.isPresent()) {

            customerMaster.setCustomerCode(customerMaster.getCustomerCode());
            customerMaster.setCustomerType(customerMaster.getCustomerType());

            customerMaster.setFirstName(customerMaster.getFirstName());
            customerMaster.setMiddleName(customerMaster.getMiddleName());
            customerMaster.setLastName(customerMaster.getLastName());

            customerMaster.setWhatsAppNo(customerMaster.getWhatsAppNo());

            customerMaster.setPancardNo(customerMaster.getPancardNo());
            customerMaster.setAadharNo(customerMaster.getAadharNo());

            customerMaster.setDob(customerMaster.getDob());

            customerMaster.setLanguage(customerMaster.getLanguage());

            customerMaster.setAddress1(customerMaster.getAddress1());
            customerMaster.setAddress2(customerMaster.getAddress2());
            customerMaster.setAddress3(customerMaster.getAddress3());
            customerMaster.setPinCode(customerMaster.getPinCode());

            customerMaster.setTehsil(customerMaster.getTehsil());
            customerMaster.setDistrict(customerMaster.getDistrict());

            customerMaster.setCountry(customerMaster.getCountry());

            customerMaster.setLandInAcres(customerMaster.getLandInAcres());

            CustomerMaster updateCustomerMaster = customerMasterRepo.save(customerMaster);
            apiResponse.setMessage("UPDATED SUCCESSFULLY");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(customerMaster);
            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.setMessage("TRY AGAIN");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(customerMaster);
            return ResponseEntity.ok(apiResponse);
        }

    }

    @GetMapping("/getByCustomerCodeOrMobileNo")
    public ResponseEntity<?> getByCustomerCodeOrMobileNo(String customerCode, String mobileNo) {
        ApiResponse apiResponse = new ApiResponse();
        List<CustomerMaster> customerMasters = customerMasterRepo.sp_searchByCustomerCodeOrMobileNo(customerCode, mobileNo);
        apiResponse.setMessage("search customer by customer code or customer mobile no");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(customerMasters);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getDetailsByChassisNo")
    public ResponseEntity<?> getDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> chassisNumber = customerMasterRepo.getByDetailsByChassisNo(chassisNo);
        apiResponse.setMessage("Customer Details get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(chassisNumber);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/chassisNoAuto")
    public ResponseEntity<?> chassisNoAuto(@RequestParam("chassisNo") String chassisNo) {
        ApiResponse apiResponse = new ApiResponse();
        List sales = customerMasterRepo.findByChassisNumberContaining(chassisNo);
        apiResponse.setMessage("chassis number get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sales);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/customerCodeAuto")
    public ResponseEntity<?> customerCodeAuto(@RequestParam("customerCode") String customerCode) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> customerMasters = customerMasterRepo.findByCustomerCodeContaining(customerCode, userAuthentication.getUsername());
        apiResponse.setMessage("Customer code get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(customerMasters);
        return ResponseEntity.ok(apiResponse);
    }


    //need to change this service to below to customer master service
    @GetMapping("/autocompleteMobileNumber")
    public ResponseEntity<?> autocompleteMobileNumber(@RequestParam String mobileNumber) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            List<Map<String, Object>> autocompleteMobileNumber = customerMasterRepo.autocompleteMobileNumber(mobileNumber,userAuthentication.getUsername());
            apiResponse.setMessage("customer name, mobileNo. autocomplete");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(autocompleteMobileNumber);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            apiResponse.setMessage("No record Found");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.ok(apiResponse);
        }
    }
    @GetMapping("/validateMobileNumber")
    public ResponseEntity<?> validateMobileNumber(@RequestParam String mobileNumber, @RequestParam String customerCode) {
        ApiResponse apiResponse = new ApiResponse();
        
        String msg = customerMasterRepo.validateMobileNumber(mobileNumber, customerCode,userAuthentication.getUsername());
        apiResponse.setMessage("customer name, mobileNo. autocomplete");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(msg);
        return ResponseEntity.ok(apiResponse);
        
    }
    @GetMapping("getCustomerDetails")
    public ResponseEntity<?> getCustomerDetails(@RequestParam String customerCode) {
    	System.out.println("customerCode--->"+customerCode);
    	Map<String, Object> customerDetails = customerMasterRepo.getcustomerDetails(customerCode);
        System.out.println("customerDetails--->"+customerDetails);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(customerDetails);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getCustomerDetailsForPaymentReceipt")
    public ResponseEntity<?> getCustomerDetailsForPaymentReceipt(@RequestParam String customerCode) {
        Map<String, Object> customerDetails = customerMasterRepo.getcustomerDetailsForPaymentReceipt(customerCode);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(customerDetails);
        return ResponseEntity.ok(apiResponse);
    }
}
