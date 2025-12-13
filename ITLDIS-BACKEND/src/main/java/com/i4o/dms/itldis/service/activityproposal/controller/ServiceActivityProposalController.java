package com.i4o.dms.itldis.service.activityproposal.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.activityproposal.domain.ProposalApprovalDto;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.domain.ServiceActivityProposalApproval;
import com.i4o.dms.itldis.service.activityproposal.dto.SearchResponsiveServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.dto.SearchServiceActivityProposal;
import com.i4o.dms.itldis.service.activityproposal.dto.ServiceActivityCalculateActivityTypeDto;
import com.i4o.dms.itldis.service.activityproposal.dto.ServiceActivityProposalViewResponse;
import com.i4o.dms.itldis.service.activityproposal.dto.ServiceProposalApprovalDto;
import com.i4o.dms.itldis.service.activityproposal.repository.ServiceActivityProposalApprovalRepository;
import com.i4o.dms.itldis.service.activityproposal.repository.ServiceActivityProposalRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/activityProposal")
@Slf4j
public class ServiceActivityProposalController
{


    private Logger logger = LoggerFactory.getLogger(ServiceActivityProposal.class);

    @Autowired
    private ServiceActivityProposalRepo serviceActivityProposalRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DesignationHierarchyRepository hierarchyRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private ServiceActivityProposalApprovalRepository proposalApprovalRepository;


    @PostMapping("/saveServiceActivityProposal")
    public ResponseEntity<?> saveServiceActivityProposal(@RequestBody ServiceActivityProposal serviceActivityProposal) {
        serviceActivityProposal.setCreatedBy(userAuthentication.getLoginId());
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        serviceActivityProposal.setDealerMaster(dealerMaster);
        ServiceActivityProposal serviceActivityProposal1 = serviceActivityProposalRepo.save(serviceActivityProposal);

        log.info("Service saved------->>>>>"+serviceActivityProposal1);
        
        if (!serviceActivityProposal.getDraftFlag()) {
            
            List<ServiceActivityProposalApproval> approvalList = new ArrayList<>();
            proposalApprovalRepository.getServiceActivityProposalApprovalHierarchyLevel(userAuthentication.getDealerId())
                    .forEach(designationHierarchy -> {
                    	ServiceActivityProposalApproval approval = new ServiceActivityProposalApproval();
                        approval.setServiceActivityProposalId(serviceActivityProposal1.getId());
                        approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                        approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                        approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                        approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                        approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                        approval.setRejectedFlag('N');
                        approvalList.add(approval);
                    });
            proposalApprovalRepository.saveAll(approvalList);

        }
        log.info("Service saved------->>>>>" + serviceActivityProposal1);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service activity save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/calculationForActivityType")
    public ResponseEntity<?> calculationForServiceCamp(@RequestBody ServiceActivityCalculateActivityTypeDto serviceActivityCalculateActivityTypeDto)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service activity camp calculate successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceActivityProposalRepo.getMAxAllowedBudget(serviceActivityCalculateActivityTypeDto.getProposedBudget(),
                serviceActivityCalculateActivityTypeDto.getTargetedNumbers(),
                serviceActivityCalculateActivityTypeDto.getActivityTypeId(),
                serviceActivityCalculateActivityTypeDto.getTotalSubActivity()));
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping(value = "/serviceActivityProposalSearch")
    public ResponseEntity<?>serviceActivityProposalSearch(@RequestBody SearchServiceActivityProposal searchServiceActivityProposal){
        logger.info("search proposal ===========>"+searchServiceActivityProposal.toString());
        ApiResponse apiResponse=new ApiResponse();
        
        List<SearchResponsiveServiceActivityProposal> list = serviceActivityProposalRepo.searchServiceActivityProposal(
                searchServiceActivityProposal.getActivityProposalFromDate(),
                searchServiceActivityProposal.getActivityProposalToDate(),
                searchServiceActivityProposal.getActivityNumber(),
                searchServiceActivityProposal.getActivityStatus(),
                searchServiceActivityProposal.getActivityType(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchServiceActivityProposal.getPage(),
                searchServiceActivityProposal.getSize(),
                userAuthentication.getUsername(),
                'N',
                searchServiceActivityProposal.getOrgHierId());		//Suraj_02-11-2023
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Service Activity Proposal Search List");
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityProposalSearchForListing")
    public ResponseEntity<?> getMrcSearchForMrcListing(@RequestParam String searchString) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service Activity Proposal listing get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityProposalRepo.getActivityProposalSearchForActivityProposalListing(searchString,userAuthentication.getUsername())));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getServiceActivityProposalByActivityNumber")
    public ResponseEntity<?> getServiceActivityProposalById(@RequestParam("activityNumber") String activityNumber) {

        ApiResponse<ServiceActivityProposalViewResponse> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service activity proposal details");
        ServiceActivityProposalViewResponse response = serviceActivityProposalRepo.findByActivityNumber(activityNumber);
        if(response!=null){
        	String remark = serviceActivityProposalRepo.getLastKaiRemark(response.getId());
        	response.setKaiRemark(remark);
        }
        apiResponse.setResult(response);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityNumberByActivityTypeForJobCard")
    public ResponseEntity<?> getActivityNumberByActivityTypeForJobCard(@RequestParam Long activityTypeId,@RequestParam String activityNumber)
    {
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service Activity Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceActivityProposalRepo.getActivityNumberByActivityType(activityTypeId,activityNumber,userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getServiceActivityProposalStatus")
    public ResponseEntity<?> getServiceActivityProposalStatus() {
        ApiResponse< List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service Activity Proposal Status get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceActivityProposalRepo.getServiceActivityProposalStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/approveServiceActivityProposal")
    public ResponseEntity<?> approveServiceActivityProposal(@RequestBody ServiceProposalApprovalDto serviceProposalApproval) {
        ApiResponse apiResponse = new ApiResponse();
        String status = proposalApprovalRepository
                .approveServiceActivityProposal(serviceProposalApproval.getServiceActivityProposalId(),
                        userAuthentication.getKubotaEmployeeId(), serviceProposalApproval.getRemark(),userAuthentication.getUsername(), 
//                      serviceProposalApproval.getApprovedFlag()?"Approve":"Reject", 
                        serviceProposalApproval.getApprovedFlag(), 	//Suraj-18/10/2022
                        serviceProposalApproval.getApprovedAmount());
        apiResponse.setMessage(status);
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @return
     */
    @GetMapping(value = "/getProposalPendingForApproval")
    public ResponseEntity<?> getProposalPendingForApproval(){
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        
        List<Map<String, Object>> result = serviceActivityProposalRepo.getProposalPendingForApproval(userAuthentication.getUsername());
        apiResponse.setResult(result);
        
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @param proposalList
     * @return
     */
    @PostMapping("/activityProposalGroupApproval")
    public ResponseEntity<?> activityProposalGroupApproval(@Valid @RequestBody List<ProposalApprovalDto> proposalList) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        
        
        List<ProposalApprovalDto> list = proposalList.stream()
        	.filter(proposalApproval -> proposalApproval.getIsSelect() != null && proposalApproval.getIsSelect())
        	.collect(Collectors.toList());
        
        String appStatus = "";
        for(ProposalApprovalDto proposalApproval: list){
    		appStatus = proposalApproval.getApprovalStatus();
    		proposalApprovalRepository.approveServiceActivityProposal(
    				proposalApproval.getActivityProposalId(),
            		userAuthentication.getKubotaEmployeeId(), 
            		proposalApproval.getRemark(),
                    userAuthentication.getUsername(), 
                    proposalApproval.getApprovalStatus(),
                    proposalApproval.getApprovedAmount());
        }
        
        
        apiResponse.setMessage("Service Activity Proposals have been " + appStatus);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
}
