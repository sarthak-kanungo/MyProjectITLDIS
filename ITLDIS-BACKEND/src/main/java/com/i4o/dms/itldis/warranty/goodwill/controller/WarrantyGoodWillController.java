package com.i4o.dms.itldis.warranty.goodwill.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillApprovalDto;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillSearch;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillSearchResponse;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillViewDto;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillViewResponseDto;
import com.i4o.dms.itldis.warranty.goodwill.repository.WarrantyGoodwillApprovalRepo;
import com.i4o.dms.itldis.warranty.goodwill.repository.WarrantyGoodwillRepo;
import com.i4o.dms.itldis.warranty.goodwill.service.GoodwillService;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/goodwill")
public class WarrantyGoodWillController {

    @Autowired
    private GoodwillService goodwillService;

    @Autowired
    private WarrantyPcrRepo warrantyPcrRepo;
    
    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private WarrantyGoodwillRepo warrantyGoodwillRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private WarrantyGoodwillApprovalRepo goodwillApprovalRepo;

    @PostMapping("/saveGoodwill")
    public ResponseEntity<?> saveGoodwill(@RequestPart WarrantyGoodwill warrantyGoodwill, @RequestPart List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();
        warrantyGoodwill.setBranchId(userAuthentication.getBranchId());
        warrantyGoodwill.setCreatedBy(userAuthentication.getLoginId());
        warrantyGoodwill.setStatus(warrantyGoodwill.getDraftFlag()?"Draft":"Open");
        if(warrantyGoodwill.getId()!=null){
        	warrantyGoodwill.setLastModifiedBy(userAuthentication.getLoginId());
        	warrantyGoodwill.setLastModifiedDate(new Date());
        }
        apiResponse= goodwillService.saveGoodwill(warrantyGoodwill,multipartFileList);
        return ResponseEntity.ok(apiResponse);
    }
    

    @GetMapping("/dropDownPriceType")
    public ResponseEntity<?> dropDownPriceType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyGoodwillRepo.dropDownPriceType());
        apiResponse.setMessage("drop down service to get price type");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownGoodwillType")
    public ResponseEntity<?> dropDownGoodwillType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyGoodwillRepo.dropDownGoodwillType());
        apiResponse.setMessage("drop down service to get goodwill type ");
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownGoodwillStatus")
    public ResponseEntity<?> dropDownGoodwillStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyGoodwillRepo.dropDownGoodwillStatus());
        apiResponse.setMessage("drop down service to get goodwill status ");
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/approveWarrantyGoodwill")
    public ResponseEntity<?> approveWarrantyGoodwill(@RequestBody GoodwillApprovalDto approvalDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(goodwillService.approvedGwl(approvalDto));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("warrantyGoodwillView")
    public ResponseEntity<?> warrantyGoodwillView(@RequestParam String goodwillNo) {
        ApiResponse apiResponse = new ApiResponse();
        GoodwillViewResponseDto goodwillViewResponseDto=new GoodwillViewResponseDto();
        GoodwillViewDto dto = warrantyGoodwillRepo.findByGoodwillNo(goodwillNo);
        if(dto!=null){
        	if(userAuthentication.getDealerId()==null){
        		dto.setCreateWcr(false);	
        	}else{
        		dto.setCreateWcr(warrantyPcrRepo.isCreateWcr(dto.getWarrantyPcr().getPcrNo(),"GOODWILL"));
        	}
        	String wcrNo =warrantyGoodwillRepo.getWcrNo(dto.getId());
        	dto.setWcrNo(wcrNo);
	        goodwillViewResponseDto.setGoodwillViewDto(dto);
	        goodwillViewResponseDto.setWarrantyPart(warrantyGoodwillRepo.getJobCardPartWarrantyInfo(goodwillNo));
	        goodwillViewResponseDto.setLabourCharge(warrantyPcrRepo.getLobourChargeInfoGoodwill(dto.getWarrantyPcr().getServiceJobCard().getId()));
	        goodwillViewResponseDto.setOutSideLabourCharge(warrantyPcrRepo.getJobCodeInfoGoodwill(dto.getWarrantyPcr().getServiceJobCard().getId()));
	        //if(userAuthentication.getDealerId()==null){
	        	goodwillViewResponseDto.setApprovalDetails(goodwillApprovalRepo.getApprovalHierDetails(dto.getId(), userAuthentication.getUsername()));
	        //}
        }
        apiResponse.setResult(goodwillViewResponseDto);
        apiResponse.setMessage("view warranty goodwill by goodwill no");
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchWarrantyGoodwill")
    public ResponseEntity<?> searchWarrantyGoodwill(@RequestBody GoodwillSearch goodwillSearch) {
        ApiResponse apiResponse = new ApiResponse();
        /*:management,:kaiUserId,:dealerId,:dealerEmployeeId,:goodwillNo,"
        		+ ":pcrNo,:jobcardNo,:status,:goodwillFromDate,:goodwillToDate,:machineModel,:chassisNo,"
        		+ ":failureType,:mobileNo,:registrationNo,:jobCardFromDate,:jobCardToDate,"
        		+ ":page,:size,:orgHier,:userCode,:includeInactive
*/ 
     List<GoodwillSearchResponse> goodwillSearchResponseList=warrantyGoodwillRepo.goodwillSearch(userAuthentication.getManagementAccess(),
                userAuthentication.getKubotaEmployeeId(),(userAuthentication.getDealerId()==null?goodwillSearch.getDealerId():userAuthentication.getDealerId()),
                userAuthentication.getDealerEmployeeId(),goodwillSearch.getGoodwillNo(),
                goodwillSearch.getPcrNo(),goodwillSearch.getJobcardNo(),goodwillSearch.getGoodwillStatus(),goodwillSearch.getGoodwillFromDate(),goodwillSearch.getGoodwillToDate(),
                goodwillSearch.getMachineModel(),goodwillSearch.getChassisNo(),goodwillSearch.getFailureType(),goodwillSearch.getMobileNo(),
                goodwillSearch.getRegistrationNo(),goodwillSearch.getJobCardFromDate(),goodwillSearch.getJobCardToDate(),
                goodwillSearch.getPage(),goodwillSearch.getSize(),goodwillSearch.getOrgHierarchyId(),userAuthentication.getUsername(),"N", goodwillSearch.getState(), (userAuthentication.getBranchId()==null?goodwillSearch.getBranch():userAuthentication.getBranchId()));
     
      Long count=0l;
      if(goodwillSearchResponseList!=null && goodwillSearchResponseList.size()>0){
    	  count=goodwillSearchResponseList.get(0).getRecordCount();
      }
      apiResponse.setResult(goodwillSearchResponseList);
      apiResponse.setCount(count);
      apiResponse.setMessage("search warranty goodwill");
        return ResponseEntity.ok(apiResponse);
    }
    
    
    @GetMapping(value = "searchAutoCompleteGoodwillNo")
    public ResponseEntity<?> searchAutoCompleteGoodwillNo(@RequestParam String goodwillNo)  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyGoodwillRepo.searchGoodwillNo(goodwillNo, userAuthentication.getUsername()));
        apiResponse.setMessage("search goodwillNo ");
        return ResponseEntity.ok(apiResponse);
    }
}
