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

import com.i4o.dms.itldis.masters.spares.SpareMtPartCategory;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisition;
import com.i4o.dms.itldis.spares.partrequisition.dto.PartRequisitionDetailDto;
import com.i4o.dms.itldis.spares.partrequisition.dto.RequisitionSearchObject;
import com.i4o.dms.itldis.spares.partrequisition.dto.RequisitionSearchResponse;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/spares/partrequisition")
@Slf4j
public class SparePartRequisitionController {
    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private SparePartRequisitionRepo sparePartRequisitionRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @PostMapping("/savePartRequisition")
    public ResponseEntity<?> saveSparesPartRequisition(@RequestBody SparePartRequisition partRequisition){
        ApiResponse apiResponse = new ApiResponse();
        partRequisition.setCreatedBy(userAuthentication.getLoginId());
        partRequisition.setBranchId(userAuthentication.getBranchId());
        partRequisition.setRequisitionDate(new Date());
        //partRequisition.setLastModifiedBy(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        partRequisition.setType("APR");
        //partRequisition.setRequisitionPurpose("Field");
        
        Long catgId = sparePartRequisitionRepo.getCategoryIdByName("Dealer Cost");
        SpareMtPartCategory category = new SpareMtPartCategory(); 
        category.setId(catgId);
        
        partRequisition.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
        		sparePartRequisitionItem.setCategory(category);
                sparePartRequisitionItem.setAmount(sparePartRequisitionItem.getPriceUnit() * sparePartRequisitionItem.getReqQuantity());
        });
        sparePartRequisitionRepo.save(partRequisition);
        apiResponse.setMessage("Parts requisition saved successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getRequisitionPurpose")
    public ResponseEntity<?> getRequisitionPurpose(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartRequisitionRepo.getRequisitionPurpose());
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchPartRequisitionNo")
    public ResponseEntity<?> searchPartRequisitionNo(@RequestParam String requisitionNo){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartRequisitionRepo.searchPartRequisitionNo(requisitionNo,userAuthentication.getUsername()));
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchJobCardNo")
    public ResponseEntity<?> searchJobCardNo(@RequestParam String jobCardNo){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartRequisitionRepo.searchJobCardNo(jobCardNo,userAuthentication.getUsername()));
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchPartRequisition")
    public ResponseEntity<?> searchPartRequisition(@RequestBody RequisitionSearchObject requisitionSearchObject){
        ApiResponse apiResponse = new ApiResponse();
        List<RequisitionSearchResponse> result = sparePartRequisitionRepo
        		.searchPartRequisition(requisitionSearchObject.getRequisitionNo(),requisitionSearchObject.getJobCardNo(),
	                requisitionSearchObject.getRequisitionPurpose(),requisitionSearchObject.getFromDate(),
	                requisitionSearchObject.getToDate(),userAuthentication.getBranchId(),userAuthentication.getDealerEmployeeId(),
	                requisitionSearchObject.getPage(),requisitionSearchObject.getSize(),userAuthentication.getUsername());
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setResult(result);
        apiResponse.setCount(count);
        apiResponse.setMessage("Search Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPartRequisitionById/{requisitionId}")
    public ResponseEntity<?> getPartRequisitionById(@PathVariable Long requisitionId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("searchGrn");
        apiResponse.setStatus(HttpStatus.OK.value());
        PartRequisitionDetailDto detailDto = new PartRequisitionDetailDto();
        detailDto.setSparePartRequisition(sparePartRequisitionRepo.findPartRequisitionById(requisitionId));
        detailDto.setSparePartRequisitionItem(sparePartRequisitionRepo.findPartRequisitionItemsById(requisitionId));
        apiResponse.setResult(detailDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchSparesPartItemNo")
    public ResponseEntity<?> searchSparesPartItemNo(@RequestParam String itemNo,@RequestParam String existingIds){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartRequisitionRepo.searchSparesPartItemNo(itemNo,existingIds,userAuthentication.getDealerId()));
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSparesPartItemNoDetails")
    public ResponseEntity<?> getSparesPartItemNoDetails(@RequestParam String itemNo){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sparePartRequisitionRepo.getSparesPartItemNoDetails(itemNo));
        apiResponse.setMessage("Parts requisition list");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

}
