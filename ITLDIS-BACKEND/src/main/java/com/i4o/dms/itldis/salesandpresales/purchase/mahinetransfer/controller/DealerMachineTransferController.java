package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto.MachineTransferApprovalDto;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto.MachineTransferList;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.Dto.MachineTransferSearchDto;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.domain.DealerMachineTransfer;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.domain.DealerMachineTransferApproval;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.repository.DealerMachineTransferApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.repository.DealerMachineTransferRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/purchaseOrder")
public class DealerMachineTransferController {

    @Autowired
    private DealerMachineTransferRepository dealerMachineTransferRepository;
    
    @Autowired
    private DealerMachineTransferApprovalRepository dealerMachineTransferApprovalRepository;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping(value = "addDealerMachineTransfer")
    public ResponseEntity<?> addDealerMachineTransfer(@RequestBody DealerMachineTransfer dealerMachineTransfer) {
        ApiResponse apiResponse = new ApiResponse();
        dealerMachineTransfer.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        dealerMachineTransfer.setDealerEmployeeId(userAuthentication.getDealerEmployeeId());
        dealerMachineTransfer.setCreatedBy(userAuthentication.getLoginId());
        DealerMachineTransfer t1 = dealerMachineTransferRepository.save(dealerMachineTransfer);
                
        List<Map<String,Object>> list = dealerMachineTransferApprovalRepository.getMachineTransferApprovalHierarchyLevel(userAuthentication.getDealerId());
        
        List<DealerMachineTransferApproval> approvalsHier = new ArrayList<>();
        list.forEach(designationHierarchy -> {
        	DealerMachineTransferApproval approval = new DealerMachineTransferApproval();
                    approval.setMachineTransferId(t1.getId());
                    approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                    approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                    approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                    approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                    approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                    approval.setRejectedFlag('N');
                    approvalsHier.add(approval);
                });
        dealerMachineTransferApprovalRepository.saveAll(approvalsHier);

        apiResponse.setMessage("save details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(t1);
        
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "searchCoDealer")
    public ResponseEntity<?> searchCoDealer(@RequestParam Long id, @RequestParam String code, @RequestParam String dealerCode) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get co dealer");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getCoDealer(id, code, dealerCode));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "getItemInMachineTransfer")
    public ResponseEntity<?> getItemInMachineTransfer(@RequestParam String itemNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get co dealer");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getItemInMachineTransfer(itemNumber));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping(value = "getMachineTransferStatus")
    public ResponseEntity<?> getMachineTransferStatus() {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get machine transfer status ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getMachineTransferStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchMachineTransferRequest")
    public ResponseEntity<?> getEnquirySearch(@RequestBody MachineTransferSearchDto machineTransferSearchDto) {

        Map<String, Object> map = new HashMap<>();
        System.out.println(machineTransferSearchDto.getRequestFromDate()+"..."+machineTransferSearchDto.getRequestToDate());
        List<MachineTransferList> machineTransferDtos= dealerMachineTransferRepository.searchMachineTransfer(userAuthentication.getManagementAccess(),userAuthentication.getDealerId(),userAuthentication.getKubotaEmployeeId(),userAuthentication.getDealerEmployeeId(),machineTransferSearchDto.getRequestNumber(), machineTransferSearchDto.getRequestStatus(), machineTransferSearchDto.getProduct(), machineTransferSearchDto.getSeries(), machineTransferSearchDto.getModel(), machineTransferSearchDto.getSubModel(), machineTransferSearchDto.getVariant(), machineTransferSearchDto.getItemNumber(), machineTransferSearchDto.getRequestFromDate(), machineTransferSearchDto.getRequestToDate(),userAuthentication.getUsername(), machineTransferSearchDto.getPage(), machineTransferSearchDto.getSize());
        Integer count= 0;
        if(machineTransferDtos!=null && machineTransferDtos.size()>0){
        	count = machineTransferDtos.get(0).getTotalCount();
        }
        ApiResponse apiResponse = new ApiResponse();
        map.put("message", "get machine transfer dto");
        map.put("status", HttpStatus.OK.value());
        map.put("result",machineTransferDtos);
        map.put("count",count);

        return ResponseEntity.ok(map);
    }



    @GetMapping(value = "searchRequestNumber")
    public ResponseEntity<?> searchRequestNumber(@RequestParam String requestNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get request number ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.searchReceiptNumber(requestNumber, userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "findByRequestNumber")
    public ResponseEntity<?> findByRequestNumber(@RequestParam String requestNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("find by request number ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.findByRequestNumber(requestNumber));
        return ResponseEntity.ok(apiResponse);
    }

    
    @GetMapping(value = "machineTransferApprovalDetails")
    public ResponseEntity<?> machineTransferApprovalDetails(@RequestParam String requestNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("find by request number ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferApprovalRepository.machineTransferApprovalDetails(requestNumber));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "getDealerCodeAndDealerNameAndGstNo")
    public ResponseEntity<?> getDealerCodeAndDealerNameAndGstNo() {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("find by request number ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getDealerCodeAndDealerName(userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "getTrasnferFromDealerCode")
    public ResponseEntity<?> getTrasnferFromDealerCode(@RequestParam String code) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get dealer code and name  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getTrasnferFromDealerCode(userAuthentication.getDealerId(),code));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "getRequestNumberAfterSubmitMTR")
    public ResponseEntity<?> getRequestNumberAfterSubmitMTR(@RequestParam(required = true) Long id) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Request number after submit machine transfer request ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getRequestNumberAfterSubmitMachineTransfer(id));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "machineTransferApproval")
    public ResponseEntity<?> machineTransferApproval(@RequestBody MachineTransferApprovalDto machineDto) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("machine transfer request approval");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferApprovalRepository.machineTransferApproval(machineDto.getRequestNo(), machineDto.getApprovalType(), machineDto.getRemarks(), userAuthentication.getKubotaEmployeeId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value =  "getRequestForAllotment")
    public ResponseEntity<?> getRequestForAllotment(@RequestParam(required = true) String requestNo) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Request number for allotment");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerMachineTransferRepository.getRequestForAllotment(requestNo, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value =  "getTransferRequestDetails")
    public ResponseEntity<?> getTransferRequestDetailsForAllotment(@RequestParam(required = true) String requestNo) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get Request details for allotment");
        apiResponse.setStatus(HttpStatus.OK.value());
        Map<String,Object> map = new HashMap<>();
        map.put("DealerDetails",dealerMachineTransferRepository.getRequestDealerDetailsForAllotment(requestNo, userAuthentication.getDealerId()));
        map.put("MachineDetails",dealerMachineTransferRepository.getRequestMachineDetailsForAllotment(requestNo, userAuthentication.getDealerId(), userAuthentication.getBranchId()));
        apiResponse.setResult(map);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "getCsbNoToTransfer")
    public ResponseEntity<?> getCsbNoToTransfer(@RequestParam(required = true) String csbNo, @RequestParam(required = true) String model){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get CSB for Transfer");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String,Object>> map = dealerMachineTransferRepository.getCsbNoToTransfer( csbNo, model, userAuthentication.getDealerId() );
        apiResponse.setResult(map);
    	return ResponseEntity.ok(apiResponse);    
    }
}
