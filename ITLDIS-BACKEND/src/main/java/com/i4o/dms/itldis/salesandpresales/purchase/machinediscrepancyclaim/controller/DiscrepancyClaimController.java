package com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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

import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository.MachineDescripancyComplaintRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaim;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.domain.MachineDiscrepancyClaimApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDescripancyClaimSaveDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.MachineDiscrepancyClaimViewDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.dto.SearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.MachineComplaintClaimMappingRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.MachineDiscrepancyClaimApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.MachineDiscrepancyClaimRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.repository.ResponseSearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinediscrepancyclaim.service.MachineDescripancyClaimService;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.domain.SchemeClaimApproval;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveClaimApprovalRequest;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/purchase/machineDescripancyClaim")
public class DiscrepancyClaimController {

    @Autowired
    private MachineDescripancyComplaintRepository machineDescripancyComplaintRepository;
    @Autowired
    private MachineDescripancyClaimService machineDescripancyClaimService;
    @Autowired
    private MachineDiscrepancyClaimRepository machineDiscrepancyClaimRepository;
    @Autowired
    private MachineDiscrepancyClaimApprovalRepository machineDiscrepancyClaimApprovalRepository;

    @Autowired
    private MachineComplaintClaimMappingRepo machineComplaintClaimMappingRepo;

    @Autowired
    UserAuthentication userAuthentication;

    @GetMapping(value="/getDiscrepancyComplaints/{id}")
    public ResponseEntity<?> getMachineDiscrepancyComplaints(@PathVariable("id") Long id){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Complaints fetched successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyClaimService.getMachineDescripancyClaim(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value="/saveDiscrepancyClaim")
    public ResponseEntity<?> saveDiscrepancyClaim(@RequestBody MachineDescripancyClaimSaveDto saveDto){
        ApiResponse apiResponse = new ApiResponse();
        MachineDiscrepancyClaim claim = machineDescripancyClaimService.saveMachineDescripancyClaim(saveDto);
        List<MachineDiscrepancyClaimApproval> approvals = new ArrayList<>();
        machineDiscrepancyClaimApprovalRepository.getApprovalHierarchyLevel(userAuthentication.getDealerId())
                 .forEach(designationHierarchy -> {
                	 MachineDiscrepancyClaimApproval approval = new MachineDiscrepancyClaimApproval();
                     approval.setClaimId(claim.getClaimId());
                     approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                     approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                     approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                     approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                     approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                     approval.setRejectedFlag('N');
                     approvals.add(approval);
                 });
        machineDiscrepancyClaimApprovalRepository.saveAll(approvals);
        apiResponse.setMessage("claim save successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(claim);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value="/searchClaimNumber")
    public ResponseEntity<?> getClaimNumber(@RequestParam("claimNumber") String claimNumber){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("claim number get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDiscrepancyClaimRepository.searchClaimNumber(claimNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value="/getClaimStatus")
    public ResponseEntity<?> getClaimStatus(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("claim status get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDiscrepancyClaimRepository.getClaimStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestBody SearchDto searchDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("machine discrepancy claim get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        Long count = 0l;
        List<ResponseSearchDto> result = machineDiscrepancyClaimRepository.searchBy(searchDto.getClaimNumber(), searchDto.getClaimStatus(),searchDto.getFromDate(), searchDto.getToDate(), searchDto.getPage(), searchDto.getSize(), userAuthentication.getUsername());
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setResult(result);
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getDetailsByClaimId/{claimId}")
    public ResponseEntity<?> getDetailsByClaimId(@PathVariable("claimId") Long claimId) {
        ApiResponse apiResponse = new ApiResponse();
        MachineDiscrepancyClaimViewDto dto = machineDescripancyClaimService.getMachineDiscrepancyById(claimId);
        if(dto!=null){
	        if(userAuthentication.getDealerId()==null){
	        	dto.setApprovalDetails(machineDiscrepancyClaimApprovalRepository.getApprovalHierDetails(dto.getClaimId(), userAuthentication.getUsername()));
	        }
	        apiResponse.setMessage("claim get successfully.");
	        apiResponse.setStatus(HttpStatus.OK.value());
	        apiResponse.setResult(dto);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getComplaintList/{claimId}")
    public ResponseEntity<?> getComplaintList(@PathVariable("claimId") Long claimId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("claim get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineComplaintClaimMappingRepo.getMachineDiscrepancyComplaintsByClaim());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/approveMachineDiscrepancyClaim")
    public ResponseEntity<?> approveMachineDiscrepancyClaim(@RequestBody IncentiveClaimApprovalRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(machineDiscrepancyClaimApprovalRepository.approveClaim(request.getClaimId(), userAuthentication.getKubotaEmployeeId(), request.getKaiRemarks(), userAuthentication.getUsername(), request.getApprovalStatus()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

}
