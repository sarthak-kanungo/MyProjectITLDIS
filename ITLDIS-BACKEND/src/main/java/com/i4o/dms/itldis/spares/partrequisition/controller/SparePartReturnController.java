package com.i4o.dms.itldis.spares.partrequisition.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartReturn;
import com.i4o.dms.itldis.spares.partrequisition.dto.PartReturnDetail;
import com.i4o.dms.itldis.spares.partrequisition.dto.PartReturnSearchObject;
import com.i4o.dms.itldis.spares.partrequisition.dto.ReturnSearchResponse;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartReturnRepository;
import com.i4o.dms.itldis.spares.picklist.repository.PickListRepository;
import com.i4o.dms.itldis.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/partreturn")
@Slf4j
public class SparePartReturnController {
    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private SparePartReturnRepository sparePartReturnRepository;
    
    @Autowired
    private PickListRepository pickListRepository;

    @GetMapping("/getReasonsForReturn")
    public ResponseEntity<?> getReasonsForReturn() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("return reasons list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.getReasonsForReturn());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByRequisitionNoMobileNoEmpName")
    public ResponseEntity<?> searchByRequisitionNoMobileNoEmpName(@RequestParam String searchString) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search By Requisition NoMobileNo EmpName");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.searchByRequisitionNoMobileNoEmpName(searchString, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByJobCardNoForPartReturn")
    public ResponseEntity<?> searchByJobCardNoForPartReturn(@RequestParam String jobCardNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search By JobCardNo For Part Return");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.searchByJobCardNoForPartReturn(jobCardNo, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPartIssueDetailsForPartReturnByRequisitionId")
    public ResponseEntity<?> getPartIssueDetailsForPartReturnByRequisitionId(@RequestParam Long requisitionId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("getPartIssueDetailsByRequisitionId");
        PartReturnDetail partReturnDetail = new PartReturnDetail();
        partReturnDetail.setHeaderData(sparePartReturnRepository.getPartIssueDetailsForPartReturnByRequisitionId(requisitionId, userAuthentication.getBranchId(),userAuthentication.getUsername()));
        partReturnDetail.setLineItems(sparePartReturnRepository.getPartIssueItemDetailsForPartReturnByRequisitionId(requisitionId, userAuthentication.getBranchId()));
        apiResponse.setResult(partReturnDetail);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/saveSparePartsReturn")
    public ResponseEntity<?> saveSparePartsReturn(@RequestBody SparePartReturn sparePartReturn) {
        ApiResponse apiResponse = new ApiResponse();
        //sparePartReturn.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        sparePartReturn.setBranchId(userAuthentication.getBranchId());
        sparePartReturn.setCreatedBy(userAuthentication.getLoginId());
        sparePartReturn.setReturnDate(new Date());
        sparePartReturnRepository.save(sparePartReturn);
        
        sparePartReturn.getSparePartReturnItems().forEach( item -> {
	        
        	pickListRepository.updateStockDetails(userAuthentication.getBranchId(), 
    			item.getSparePartMaster().getItemNo(), 
    			item.getStoreMaster().getId(), 
    			item.getBinLocationMaster().getId(), 
    			"RETURN", 
    			item.getId(), 
    			item.getMrp(), 
    			item.getUnitPrice(), 
    			item.getSpegst(), 
    			item.getSpmgst(), 
    			item.getReturnQuantity(),
    			0,
    			userAuthentication.getLoginId());
        });
        
        apiResponse.setMessage("save Spare Parts Return");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    //table services

    @GetMapping("/searchByReturnNo")
    public ResponseEntity<?> searchByReturnNo(@RequestParam String returnNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("save Spare Parts Return");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.searchByReturnNo(returnNo, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByRequisitionNo")
    public ResponseEntity<?> searchByRequisitionNo(@RequestParam String requisitionNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchByRequisitionNo");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.searchByRequisitionNo(requisitionNo, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByJobCardNo")
    public ResponseEntity<?> searchByJobCardNo(@RequestParam String jobCardNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchByJobCardNo");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartReturnRepository.searchByJobCardNo(jobCardNo, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchPartReturn")
    public ResponseEntity<?> searchPartReturn(@RequestBody PartReturnSearchObject searchObject) {
    	
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("search Part Return");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<ReturnSearchResponse> result = sparePartReturnRepository.searchPartReturn(searchObject.getPartReturnNo(), searchObject.getRequisitionNo(),
                searchObject.getJobCardNo(), searchObject.getRequisitionPurpose(), searchObject.getReasonForReturn(),
                searchObject.getRequisitionFromDate(), searchObject.getRequisitionToDate(),
                searchObject.getIssueFromDate(), searchObject.getIssueToDate(),
                userAuthentication.getDealerId(),
                searchObject.getPage(), searchObject.getSize(),
                userAuthentication.getUsername());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    ///View Edit service
    @GetMapping("/getPartReturnById/{returnId}")
    public ResponseEntity<?> getPartReturnById(@PathVariable Long returnId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts return details by id");
        apiResponse.setStatus(HttpStatus.OK.value());
        PartReturnDetail partReturnDetail = new PartReturnDetail();
        partReturnDetail.setHeaderData(sparePartReturnRepository.getPartReturnDetailsById(returnId));
        partReturnDetail.setLineItems(sparePartReturnRepository.getPartIssueItemDetailsById(returnId));
        apiResponse.setResult(partReturnDetail);
        return ResponseEntity.ok(apiResponse);
    }
}
