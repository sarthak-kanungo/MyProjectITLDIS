package com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaint;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.domain.MachineDescripancyComplaintApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.dto.MachineDescripancyComplaintSearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.dto.MachineDescripancyComplaintViewDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.dto.ResponseSearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository.MachineDescripancyComplaintApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.repository.MachineDescripancyComplaintRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.machinedescripancycomplaint.service.MachineDescripancyComplaintService;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.domain.SchemeClaimApproval;
import com.i4o.dms.itldis.salesandpresales.schemes.claim.dto.IncentiveClaimApprovalRequest;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/purchase/machineDescripancyComplaint")
public class MachineDescripancyComplaintController {

    @Autowired
    private MachineDescripancyComplaintRepository machineDescripancyComplaintRepository;

    @Autowired
    private MachineDescripancyComplaintApprovalRepository machineDescripancyComplaintApprovalRepository;

    @Autowired
    private MachineDescripancyComplaintService machineDescripancyComplaintService;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;


    @PostMapping(value = "/saveMachineDescripancyComplaint")
    public ResponseEntity<?> saveMachineDescripancyComplaint(@RequestBody MachineDescripancyComplaint machineDescripancyComplaint) {
        ApiResponse apiResponse = new ApiResponse();
        machineDescripancyComplaint.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        machineDescripancyComplaint.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        if(machineDescripancyComplaint.getFiles()!=null){
        	machineDescripancyComplaint.getFiles().forEach(file -> {
        		file.setFileName(file.getFile().getOriginalFilename()+"_MCD_"+System.currentTimeMillis());
        	});
        }
        
        MachineDescripancyComplaint complaint=machineDescripancyComplaintService.saveMachineDescripancyComplaint(machineDescripancyComplaint);
        
        List<MachineDescripancyComplaintApproval> approvals = new ArrayList<>();
        machineDescripancyComplaintApprovalRepository.getApprovalHierarchyLevel(userAuthentication.getDealerId())
                 .forEach(designationHierarchy -> {
                	 MachineDescripancyComplaintApproval approval = new MachineDescripancyComplaintApproval();
                     approval.setComplaintId(complaint.getComplaintId());
                     approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                     approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                     approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                     approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                     approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                     approval.setRejectedFlag('N');
                     approvals.add(approval);
                 });
        machineDescripancyComplaintApprovalRepository.saveAll(approvals);
		
        apiResponse.setMessage("Successfully Saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(complaint);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestBody MachineDescripancyComplaintSearchDto searchDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("machine descripancy complaint get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<ResponseSearchDto> result = machineDescripancyComplaintRepository.searchBy(searchDto.getComplaintNumber(), searchDto.getComplaintStatus(), searchDto.getDmsGrnNumber(), searchDto.getChassisNo(),searchDto.getFromDate(), searchDto.getToDate(), searchDto.getPage(), searchDto.getSize(), userAuthentication.getUsername());
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count=result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getMachineDescripancyComplaintById/{complaintId}")
    public ResponseEntity<?> searchGrnNumber(@PathVariable("complaintId") Long complaintId) {
        ApiResponse apiResponse = new ApiResponse();
        MachineDescripancyComplaintViewDto dto = machineDescripancyComplaintRepository.findByComplaintId(complaintId);
        
        if(dto!=null){
        	if(userAuthentication.getDealerId()==null){
            	dto.setApprovalDetails(machineDescripancyComplaintApprovalRepository.getApprovalHierDetails(complaintId, userAuthentication.getUsername()));
            }
            apiResponse.setMessage("Machine Descripancy Complaint get successfully.");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(dto);
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchChassisNumber")
    public ResponseEntity<?> searchChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("chassis number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.searchChassisNumber(chassisNumber, userAuthentication.getBranchId()));
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/approveMachineDescripancyComplaint")
    public ResponseEntity<?> approveMachineDescripancyComplaint(@RequestBody IncentiveClaimApprovalRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(machineDescripancyComplaintApprovalRepository.approveClaim(request.getClaimId(), userAuthentication.getKubotaEmployeeId(), request.getKaiRemarks(), userAuthentication.getUsername(), request.getApprovalStatus()));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getDetailsByChassisNumber")
    public ResponseEntity<?> getDetailsByChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("details get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.getDetailsByChassisNumber(chassisNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchItemNumber")
    public ResponseEntity<?> searchItemNumber(@RequestParam String itemNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("items get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.searchItemNumber(itemNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDetailsByItemId/{itemId}")
    public ResponseEntity<?> getDetailsByItemId(@PathVariable("itemId") Long itemId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("item details get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.getDetailsByItemId(itemId));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchComplaintNumber")
    public ResponseEntity<?> getComplaintNumber(@RequestParam String complaintNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("complaint number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.getComplaintNumber(complaintNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getComplaintStatus")
    public ResponseEntity<?> getComplaintStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("complaint status get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.getComplaintStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getComplaintType")
    public ResponseEntity<?> getComplaintType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("complaint status get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.getComplaintType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchExistingChassisNumber")
    public ResponseEntity<?> searchExistingChassisNumber(@RequestParam String chassisNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("chassis numbers get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.searchExistingChassisNumber(chassisNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchGrnNumber")
    public ResponseEntity<?> searchGrnNumber(@RequestParam String grnNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("grn numbers get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(machineDescripancyComplaintRepository.searchGrnNumber(grnNumber));
        return ResponseEntity.ok(apiResponse);
    }


}
