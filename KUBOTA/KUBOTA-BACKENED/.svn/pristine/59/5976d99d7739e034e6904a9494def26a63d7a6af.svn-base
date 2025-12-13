package com.i4o.dms.kubota.spares.partrequisition.controller;

import static com.i4o.dms.kubota.configurations.Constants.DRAFT;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

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

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.kubota.service.jobcard.repository.ServiceJobCardRepo;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartIssue;
import com.i4o.dms.kubota.spares.partrequisition.dto.IssueSearchResponse;
import com.i4o.dms.kubota.spares.partrequisition.dto.PartIssueRequisitionDetail;
import com.i4o.dms.kubota.spares.partrequisition.dto.PartIssueSearchObject;
import com.i4o.dms.kubota.spares.partrequisition.repository.SparePartIssueRepository;
import com.i4o.dms.kubota.spares.picklist.repository.PickListRepository;
import com.i4o.dms.kubota.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/partissue")
@Slf4j
public class SparePartIssueController {

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private PickListRepository pickListRepository;
    
    @Autowired
    private SparePartIssueRepository sparePartIssueRepository;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private ServiceJobCardRepo serviceJobCardRepo;

    @Transactional
    @PostMapping("/saveSparePartIssue")
    public ResponseEntity<?> saveSparePartIssue(@RequestBody SparePartIssue sparePartIssue) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("save Spare Part Issue");
        apiResponse.setStatus(HttpStatus.OK.value());
        if(sparePartIssue.getId()!=null){
        	sparePartIssue.setLastModifiedBy(userAuthentication.getLoginId());
        	sparePartIssue.setLastModifiedDate(new Date());
        }
        sparePartIssue.setCreatedBy(userAuthentication.getLoginId());
        sparePartIssue.setBranchId(userAuthentication.getBranchId());
        
        if (sparePartIssue.getJobCardNo()!=null && !sparePartIssue.getPartIssueStatus().equalsIgnoreCase(DRAFT)){
            //ServiceJobCard serviceJobCard = serviceJobCardRepo.getOne(sparePartIssue.getJobCardNo().getId());
            //serviceJobCard.setPartIssueFlag(true);
            //serviceJobCardRepo.save(serviceJobCard);
        	serviceJobCardRepo.updatePastIssue(sparePartIssue.getJobCardNo().getId());
        }
        sparePartIssueRepository.save(sparePartIssue);
        
        if(!sparePartIssue.getIssueAgainst().equalsIgnoreCase("API")){
	        sparePartIssue.getSparePartIssueItems().forEach( item -> {
	        
	        	pickListRepository.updateStockDetails(userAuthentication.getBranchId(), 
	    			item.getSparePartMaster().getItemNo(), 
	    			item.getStoreMaster().getId(), 
	    			item.getBinLocationMaster().getId(), 
	    			"ISSUE", 
	    			item.getId(), 
	    			item.getMrp(), 
	    			item.getUnitPrice(), 
	    			item.getSpegst(), 
	    			item.getSpmgst(), 
	    			0,
	    			item.getIssuedQuantity(),
	    			userAuthentication.getLoginId());
	        });
        }else{
        	
        }
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getRequisitionAgainstIssue")
    public ResponseEntity<?> getPartIssueAgainst() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartIssueRepository.getRequisitionAgainstIssue());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getIssueType")
    public ResponseEntity<?> getIssueType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartIssueRepository.getIssueType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchByDocumentForPartIssue")
    public ResponseEntity<?> searchPartRequisitionNoForPartIssue(@RequestParam String searchNo,
                                                                 @RequestParam String issueType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts requisition no search");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartIssueRepository.searchPartRequisitionNoForPartIssue(searchNo, issueType, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPartRequisitionDetailsById")
    public ResponseEntity<?> getPartRequisitionDetailsById(@RequestParam Long requisitionId, @RequestParam String issueAgainst, @RequestParam Long apiId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts requisition details by id");
        apiResponse.setStatus(HttpStatus.OK.value());
        PartIssueRequisitionDetail requisitionDetail = new PartIssueRequisitionDetail();
        requisitionDetail.setPartRequisition(sparePartIssueRepository.getPartRequisitionDetailsById(requisitionId));
        if (issueAgainst.equalsIgnoreCase("Stock"))
            requisitionDetail.setPartRequisitionItem(sparePartIssueRepository.getPartRequisitionItemDetailsById(requisitionId));
        else
            requisitionDetail.setPartRequisitionItem(sparePartIssueRepository.getPartRequisitionItemDetailsByIdFromApi(requisitionId,apiId));
        apiResponse.setResult(requisitionDetail);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPartIssueById/{issueId}")
    public ResponseEntity<?> getPartIssueById(@PathVariable Long issueId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Parts requisition details by id");
        apiResponse.setStatus(HttpStatus.OK.value());
        PartIssueRequisitionDetail requisitionDetail = new PartIssueRequisitionDetail();
        requisitionDetail.setPartRequisition(sparePartIssueRepository.getPartIssueDetailsById(issueId));
        requisitionDetail.setPartRequisitionItem(sparePartIssueRepository.getPartIssueItemDetailsById(issueId));
        apiResponse.setResult(requisitionDetail);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getIssueTo")
    public ResponseEntity<?> getIssueTo() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Issue To");
        apiResponse.setResult(sparePartIssueRepository.getIssueToList(userAuthentication.getDealerId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getDropDownCategory")
    public ResponseEntity<?> getDropDownCategory() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get DropDownCategory");
        apiResponse.setResult(sparePartIssueRepository.getPartCategoryList());
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    
    @GetMapping("/searchPartRequisitionNo")
    public ResponseEntity<?> searchPartRequisitionNo(@RequestParam String requisitionNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Issue To");
        apiResponse.setResult(sparePartIssueRepository.searchPartRequisitionNo(requisitionNo, userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchApiNo")
    public ResponseEntity<?> searchApiNo(@RequestParam String apiNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get job card no");
        apiResponse.setResult(sparePartIssueRepository.searchApiNo(apiNo, userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchJobCardNo")
    public ResponseEntity<?> searchJobCardNo(@RequestParam String jobCardNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get job card no");
        apiResponse.setResult(sparePartIssueRepository.searchJobCardNo(jobCardNo, userAuthentication.getUsername()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAvailableStockForPartIssue")
    public ResponseEntity<?> getAvailableStockForPartIssue(@RequestParam(required = false) Long apiId,
                                                           @RequestParam String itemNo,
                                                           @RequestParam(required = false)String category) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("part issue");
        if (apiId == null)
            apiResponse.setResult(sparePartIssueRepository.getAvailableStockForPartIssue(itemNo, userAuthentication.getBranchId(), category));
        else
            apiResponse.setResult(sparePartIssueRepository
                    .getAvailableStockFromAdvancedPartIssue(apiId, itemNo, userAuthentication.getBranchId()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

//    @GetMapping("/getAvailableStockFromAdvancedPartIssue")
//    public ResponseEntity<?> getAvailableStockFromAdvancedPartIssue(@RequestParam Long apiId, @RequestParam String itemNo) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("part issue");
//
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping("/searchPartIssue")
    public ResponseEntity<?> searchPartIssue(@RequestBody PartIssueSearchObject issueSearchObject) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("part issue");
        List<IssueSearchResponse> result = sparePartIssueRepository.searchPartIssue(issueSearchObject.getPartIssueNo(),
                issueSearchObject.getRequisitionNo(), 
                issueSearchObject.getJobCardNo(), 
                issueSearchObject.getRequisitionPurpose(),
                issueSearchObject.getRequisitionFromDate(), 
                issueSearchObject.getRequisitionToDate(),
                issueSearchObject.getIssueFromDate(), 
                issueSearchObject.getIssueToDate(),
                userAuthentication.getBranchId(),
                userAuthentication.getDealerEmployeeId(),
                issueSearchObject.getPage(), 
                issueSearchObject.getSize(),
                userAuthentication.getUsername());
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        apiResponse.setResult(result);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/checkLastIssueAgainstJobcard")
    public ResponseEntity<?> checkLastIssueAgainstJobcard(@RequestParam Long id) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setMessage("check last issue type on same jobcard");
		apiResponse.setResult(sparePartIssueRepository.checkLastIssueAgainstJobcard(id, userAuthentication.getBranchId()));
		apiResponse.setStatus(HttpStatus.OK.value());
		return ResponseEntity.ok(apiResponse);
	}
}