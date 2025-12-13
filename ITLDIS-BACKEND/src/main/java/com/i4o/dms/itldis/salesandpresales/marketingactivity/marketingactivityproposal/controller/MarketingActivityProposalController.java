package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.controller;


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.i4o.dms.itldis.configurations.Constants;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposalApproval;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto.MarketingActivityProposalViewDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto.ProposalApproval;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto.ResponseSearchDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto.SearchDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository.ActivityHeadRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/marketingActivityProposal")
public class MarketingActivityProposalController {

    @Autowired
    private MarketingActivityProposalRepo marketingActivityProposalRepo;

    @Autowired
    private MarketingActivityProposalApprovalRepository marketingActivityProposalApprovalRepository;

    @Autowired
    private ActivityHeadRepo activityHeadRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    
    @Autowired
    EnquiryRepo enquiryRepo; 

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private DesignationHierarchyRepository hierarchyRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @GetMapping(value="/getClaimableAmount")
    public ResponseEntity<?> getClaimableAmount(@RequestParam Integer activityTypeId, @RequestParam Double maxLimit, @RequestParam Double budget){
    	ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Claimable Amount get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getClaimableAmount(activityTypeId, budget, maxLimit));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value="/getOrgLevelByHODept")
    public ResponseEntity<?> getOrgLevelByHODept(@RequestParam String deptCode){
    	ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Organisation Level get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getOrgLevelByHODept(deptCode));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value="/getOrgLevelHierForParent")
    public ResponseEntity<?> getOrgLevelHierForParent(@RequestParam Integer levelId, @RequestParam Integer hierId){
    	ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Organisation Hierarchy get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        System.out.println(userAuthentication.getUsername()+":"+levelId+":"+hierId);
        
        apiResponse.setResult(marketingActivityProposalRepo.getOrgLevelHierForParent(userAuthentication.getUsername(), levelId, hierId));
        return ResponseEntity.ok(apiResponse);
    }		
    		
    //In Use
    @Transactional
    @PostMapping(value = "/saveMarketingActivityProposal")
    public ResponseEntity<?> saveMarketingActivityProposal(@Valid @RequestBody MarketingActivityProposal marketingActivityProposal) {
        ApiResponse<MarketingActivityProposal> apiResponse = new ApiResponse<>();
        List<MarketingActivityProposalApproval> approvalList = new ArrayList<>();
        if(marketingActivityProposal.getActivityCategory()==2){
        	marketingActivityProposal.setActivityStatus(Constants.WAITING_FOR_APPROVAL);
        }else{
        	marketingActivityProposal.setActivityStatus(Constants.APPROVED);
        }
        marketingActivityProposal.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        marketingActivityProposal.setCreatedDate(new Date());
        marketingActivityProposal.setActivityCreationDate(new Date());
        marketingActivityProposal.setCreatedBy(userAuthentication.getLoginId());
        System.out.println("marketingActivityProposal.getNumberOfDays : "+marketingActivityProposal.getNumberOfDays());
        marketingActivityProposalRepo.save(marketingActivityProposal);
        if(marketingActivityProposal.getActivityCategory()==2){
	        marketingActivityProposalApprovalRepository
	                .getMarketingActivityProposalApprovalHierarchyLevel(
	                        marketingActivityProposal.getDealerMaster().getId())
	                .forEach(map -> {
	                    MarketingActivityProposalApproval approval = new MarketingActivityProposalApproval();
	                    approval.setMarketingActivityProposalId(marketingActivityProposal.getActivityProposalId());
	                    approval.setApproverLevelSeq((Integer)map.get("approver_level_seq"));
	                    approval.setDesignationLevelId((BigInteger)map.get("designation_level_id"));
	                    approval.setGrpSeqNo((Integer)map.get("grp_seq_no"));
	                    approval.setIsfinalapprovalstatus((Character)map.get("isFinalApprovalStatus"));
	                    approval.setApprovalStatus((String)map.get("approvalStatus"));
	                    approval.setRejectedFlag('N');
	                    approvalList.add(approval);
	                });
	        marketingActivityProposalApprovalRepository.saveAll(approvalList);
        }
        apiResponse.setMessage("Marketing activity proposal successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setId(marketingActivityProposal.getActivityProposalId());
        return ResponseEntity.ok(apiResponse);
    }

    //In Use(Used For Search)
    @GetMapping(value = "/searchActivityNumber")
    public ResponseEntity<?> searchActivityNumber(@RequestParam(required = false) String activityNumber, @RequestParam String functionality) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("activity number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        System.out.println(userAuthentication.getUsername() +":"+
        					activityNumber+":"+
        					userAuthentication.getDealerId()+":"+
        	                userAuthentication.getKubotaEmployeeId()+":"+
        	                userAuthentication.getDealerEmployeeId()+":"+
        	                userAuthentication.getManagementAccess()+":"+
        	                functionality);
        
        apiResponse.setResult(marketingActivityProposalRepo
        			.searchActivityNumber(userAuthentication.getUsername(),
        					activityNumber,userAuthentication.getDealerId(),
        	                userAuthentication.getKubotaEmployeeId(),
        	                userAuthentication.getDealerEmployeeId(),
        	                userAuthentication.getManagementAccess(),
        	                functionality
        	           )
        	);
        return ResponseEntity.ok(apiResponse);
    }

    //In Use(Used For Search)
    @GetMapping(value = "/searchactivitytype")
    public ResponseEntity<?> searchActivityType() {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("activity type get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.searchActivityType());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/proposaldetailsbyactivitynumber")
    public ResponseEntity<?> proposaldetailsbyactivitynumber(@RequestParam("activityNumber") String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.proposalDetailsByActivityNumber(activityNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value="/getDealerCodeAutoComplete")
    public ResponseEntity<?> getDealerCodeAutoComplete(@RequestParam("dealerCode") String dealerCode){
    	ApiResponse apiResponse = new ApiResponse();
    	apiResponse.setMessage("Dealers get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        String usercode = userAuthentication.getUsername();
        apiResponse.setResult(dealerMasterRepo.getDealerCodeAutoComplete(dealerCode, usercode));
    	return ResponseEntity.ok(apiResponse);
    }
    //Not Used
//    @GetMapping(value = "/activityProposalById/{id}")
//    public ResponseEntity<?> activityProposalById(@PathVariable("id") Long id) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("activfity proposal get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.findByActivityProposalId(id));
//        return ResponseEntity.ok(apiResponse);
//
//    }

    //Not Used
//    @GetMapping(value = "/activityProposalById1/{id}")
//    public ResponseEntity<?> activityProposalById1(@PathVariable("id") Long id) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("activity proposal get 11111 successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.getOne(id));
//        return ResponseEntity.ok(apiResponse);
//    }

    //Not Used
    /*  @GetMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestParam(required = false) String activityNumber,
                                      @RequestParam(required = false) String activityType,
                                      @RequestParam(required = false) String activityStatus,
                                      @RequestParam(required = false) String fromDate,
                                      @RequestParam(required = false) String toDate,
                                      @RequestParam(required = false, defaultValue = "0") Integer page,
                                      @RequestParam(required = false, defaultValue = "10") Integer size) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("marketing activity get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.searchBy(activityNumber, activityType, activityStatus, fromDate, toDate, page, size));
        apiResponse.setCount(marketingActivityProposalRepo.searchByCount(activityNumber, activityType, activityStatus, fromDate, toDate, page, size));
        return ResponseEntity.ok(apiResponse);
    }*/


    //In Use
    @PostMapping(value = "/marketingActivityProposalSearch")
    public ResponseEntity<?> searchBy(@RequestBody SearchDto searchDto) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("marketing activity get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        
        List<ResponseSearchDto> list =  marketingActivityProposalRepo
        .searchBy(userAuthentication.getUsername(),
        		searchDto.getActivityNumber(),
                searchDto.getActivityStatus(),
                searchDto.getActivityType(),
                searchDto.getFromDate(),
                searchDto.getToDate(),
                searchDto.getDealerId()==null?userAuthentication.getDealerId():searchDto.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchDto.getPage(),
                searchDto.getSize(),
                searchDto.getHierId(),
                searchDto.getState());
        
        apiResponse.setResult(list);

        apiResponse.setCount(list!=null && list.size()>0?list.get(0).getRecordCount():0L);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/updateGracePeriod")
    public ResponseEntity<?> updateGracePeriod(@RequestParam Integer activityId){
    	 ApiResponse<Object> apiResponse = new ApiResponse<>();
         apiResponse.setMessage("Grace Period update successfully.");
         apiResponse.setStatus(HttpStatus.OK.value());
         marketingActivityProposalRepo.updateGracePeriod(activityId);
         return ResponseEntity.ok(apiResponse);
    }

    //Commented By Tejas 18th Feb.
//    @GetMapping(value = "/activityProposalById/{id}")
//    public ResponseEntity<?> activityProposalById(@PathVariable("id") Long id) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("activity proposal get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.findByActivityProposalId(id));
//        return ResponseEntity.ok(apiResponse);
//    }

//    @GetMapping("/searchEnquiryForProposal")
//    public ResponseEntity<?> searchEnquiryForProposal(@RequestParam String enquiryNumber,
//                                                      @RequestParam Long dealerId) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("enquiry get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.searchEnquiryForProposal(enquiryNumber, dealerId));
//        return ResponseEntity.ok(apiResponse);
//    }

//    @GetMapping("/getEnquiryForActivityPurpose")
//    public ResponseEntity<?> getEnquiryForActivityPurpose(@RequestParam("activityNumber") String activityNumber) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("enquiry get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.getEnquiryForActivityPurpose(activityNumber));
//        return ResponseEntity.ok(apiResponse);
//    }

//    @GetMapping("/getEnquiryForActivityPurpose/{id}")
//    public ResponseEntity<?> getEnquiryForActivityPurposeById(@PathVariable("id") Long id) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("enquiry get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.getEnquiryForActivityPurposeById(id));
//        return ResponseEntity.ok(apiResponse);
//    }

//    @GetMapping("/getById/{id}")
//    public ResponseEntity<?> getById(@PathVariable("id") Long activityProposalId) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("proposal get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityProposalRepo.getHeaderDataById(activityProposalId));
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping("/activityProposalGroupApproval")
    public ResponseEntity<?> activityProposalGroupApproval(@Valid @RequestBody List<ProposalApproval> proposalList) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        ResponseEntity<?> entity = null;
        String appStatus = proposalList.get(0).getApprovalStatus();
        proposalList.stream().filter(proposalApproval -> proposalApproval.getIsSelect()!=null && proposalApproval.getIsSelect()).forEach(proposalApproval -> {
        	marketingActivityProposalApprovalRepository
            .approveMarketingActivityProposal(proposalApproval.getActivityProposalId(),
                   userAuthentication.getKubotaEmployeeId(),proposalApproval.getRemark(),userAuthentication.getUsername(),proposalApproval.getApprovalStatus(),proposalApproval.getApprovedAmount());
        });
        apiResponse.setMessage("Activity Proposals have been " + appStatus);
        apiResponse.setStatus(HttpStatus.OK.value());
        entity = ResponseEntity.ok(apiResponse);
        return entity;
    }


    @PostMapping("/approveMarketingActivityProposal")
    public ResponseEntity<?> approveMarketingActivityProposal(@Valid @RequestBody ProposalApproval proposalApproval) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        ResponseEntity<?> entity = null;
        String designationLevel = "";//kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId()).getDesignationHierarchy().getHierarchy();
        logger.info("designationLevel:"+designationLevel);

        /*try{
        MarketingActivityProposalApproval approval = marketingActivityProposalApprovalRepository
                .findByMarketingActivityProposalAndKubotaEmployeeMasterId(proposalApproval.getActivityProposalId(), proposalApproval.getUserId());

        if (approval != null) {*/
         System.out.println(proposalApproval.toString());
            String status = marketingActivityProposalApprovalRepository
                    .approveMarketingActivityProposal(proposalApproval.getActivityProposalId(),
                           userAuthentication.getKubotaEmployeeId(),proposalApproval.getRemark(),userAuthentication.getUsername(),proposalApproval.getApprovalStatus(),proposalApproval.getApprovedAmount());
            logger.info("status "+status);
//            List<ActivityHead> activityHeads = new ArrayList<>();
//            proposalApproval.getActivityHeads().forEach(activityHead -> {
//                ActivityHead approvedHead = activityHeadRepo.getOne(activityHead.getId());
//             //   approvedHead.setApprovedAmount(activityHead.getApprovedAmount());
//                activityHeads.add(approvedHead);
//            });
//            activityHeadRepo.saveAll(activityHeads);
            /*Optional<MarketingActivityProposal> marketingActivityProposal=
                    marketingActivityProposalRepo.findById(proposalApproval.getActivityProposalId());
            marketingActivityProposal.ifPresent(marketingActivityProposal1 -> {
                        marketingActivityProposal1.setApprovedAmount(proposalApproval.getApprovedAmount());
                        marketingActivityProposalRepo.save(marketingActivityProposal1);
                    }
                    );*/
            apiResponse.setMessage(status);
            apiResponse.setResult(status);
            apiResponse.setStatus(HttpStatus.OK.value());
            entity = ResponseEntity.ok(apiResponse);
       /* }
        }catch (Exception e) {
            apiResponse.setMessage("UnAuthorised");
            apiResponse.setResult("Not authorised user to approve proposal");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            entity =  ResponseEntity.badRequest().body(apiResponse);
        }*/
        return entity;
    }

/*    @Deprecated
    @PostMapping("/approveActivityProposal")
    public ResponseEntity<?> approveActivityProposal(@RequestBody ProposalApproval proposalApproval) {
        ApiResponse apiResponse = new ApiResponse();
        String status = marketingActivityProposalApprovalRepository.approveMarketingActivityProposal(proposalApproval.getActivityProposalId(),
                proposalApproval.getUserId(), proposalApproval.getRemark());
        if (status.equalsIgnoreCase(FAIL)) {
            apiResponse.setMessage("Not authorised user to approve proposal");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(apiResponse);
        }
        apiResponse.setMessage(status);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }*/

    @GetMapping("/getZonalAndRegionalManagerForDealer")
    public ResponseEntity<?> getEnquiryForActivityPurposeById() {

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("enquiry get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getZonalAndRegionalmanagerForDealer(userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Tejas Code:-
    @GetMapping("/getHeadByActivityType")
    public ResponseEntity<?> getHeadByActivityType(Long activityTypeId) {

        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Heads Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getHeadsByActivityType(activityTypeId));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getEnquirySourceBySourcePurpose")
    public ResponseEntity<?> getEnquirySourceBySourcePurpose(Long sourcePurposeId) {

        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Enquiry Source Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getEnquirySourceByPurpose(sourcePurposeId, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getMaxAllowedBudget")
    public ResponseEntity<?> getMaxAllowedBudget(@RequestParam(required = false) String activityTypeId,
    		@RequestParam(required = false) String fromDate,
    		@RequestParam(required = false) String toDate) {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse();
        apiResponse.setMessage("Max Allowed Budget Get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        if(fromDate!=null && !fromDate.equals("")){
        	String[] s = fromDate.split("-");
        	fromDate = s[2]+"-"+s[1]+"-"+s[0];
        }
        if(toDate!=null && !toDate.equals("")){
        	String[] s = toDate.split("-");
        	toDate = s[2]+"-"+s[1]+"-"+s[0];
        }
        
        System.out.println(fromDate+":"+toDate+": dealerId :"+userAuthentication.getDealerId()+" : activityTypeId :"+activityTypeId);
        
        apiResponse.setResult(marketingActivityProposalRepo.getMaximumLimitByActivityType(activityTypeId, userAuthentication.getDealerId(),
        		fromDate, toDate));
        
        return ResponseEntity.ok(apiResponse);
    }

//    @PostMapping("/saveMarketingActivityProposal")
//    public ResponseEntity<?> saveMarketingActivityProposal(@RequestBody MarketingActivityProposal marketingActivityProposal)
//    {
//        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
//        marketingActivityProposal.setDealerEmployeeMaster(dealerEmployeeMaster);
//        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
//        marketingActivityProposal.setDealerMaster(dealerMaster);
//        MarketingActivityProposal marketingActivityProposal1=marketingActivityProposalRepo.save(marketingActivityProposal);
//
//        logger.info("Service saved------->>>>>"+marketingActivityProposal1);
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Marketing Activity Proposal save successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping(value = "/getEnquiryDetailsByEnquiryNumber")
    public ResponseEntity<?> getEnquiryDetailsByEnquiryNumberAutocomplete(@RequestParam Long enquiryNumberId) {
        ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Enquiry Details Get Successfully..");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.getEnquiryDetails(enquiryNumberId));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getEnquiryNumberAutocomplete")
    public ResponseEntity<?> getEnquiryNumberAutocomplete(@RequestParam String enquiryNumber) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Enquiry Number Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryNumberName(enquiryNumber,
        		userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId(),
        		userAuthentication.getBranchId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getUsername(),
        		"ACT_PROPOSAL")
        	);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getActivityProposalNumberForSearch")
    public ResponseEntity<?> getActivityProposalNumberForSearch(@RequestParam String activityNumber,@RequestParam String functionality) {
        ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Marketing Activity Proposal Number Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityProposalRepo.searchActivityNumber(
        		userAuthentication.getUsername(),
				activityNumber,
				userAuthentication.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getManagementAccess(),
                functionality
                )
        	);
        return ResponseEntity.ok(apiResponse);
    }

//Esc@rt@s2020
    @GetMapping(value = "/getMarketingActivityById/{id}")
    public ResponseEntity<?> getMarketingActivityById(@PathVariable Long id) {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ApiResponse<MarketingActivityProposalViewDto> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Marketing Activity Proposal By Id Details");

        Map<String, Object> header = marketingActivityProposalRepo.getViewHeaderData(id);
        List<Map<String, Object>> heads = marketingActivityProposalRepo.getViewHeadsData(id);
        List<Map<String, Object>> enquiries = marketingActivityProposalRepo.getViewEnquiryData(id);
        Map<String, Object> dealerInfo = marketingActivityProposalRepo.getViewDealerInfo(id);
        List<Map<String, Object>> approvalHierDetails = marketingActivityProposalRepo.getApprovalHierDetails(id, userAuthentication.getUsername());
        
        MarketingActivityProposalViewDto marketingActivityProposalViewDto= new MarketingActivityProposalViewDto();
        if(header!=null){
        	Map<String, Object> reqData = marketingActivityProposalRepo.getCountOfProposal(id);	//Suraj--10-01-2023
        	int noOfProposalsOfDealer = (int)reqData.get("count");	//Suraj--10-01-2023
        	int dealerCreatedMonths = (int)reqData.get("dealerCreatedMonths");	//Suraj--19-01-2023
        	
        	System.out.println(header.get("activityTypeId")+":"+df.format((Date)header.get("fromDate"))+":"+ df.format((Date)header.get("toDate")));
        	Map<String,Object> map = marketingActivityProposalRepo.getMaximumLimitByActivityType(String.valueOf(header.get("activityTypeId")),userAuthentication.getDealerId(), df.format((Date)header.get("fromDate")), df.format((Date)header.get("toDate")));
        	if(map!=null){
        		Double claimableAmount = (Double)map.get("claimableAmount");
        		Double maxAllowedBudget = (Double)map.get("maximumLimit");
        		Double proposedBudget = (Double)header.get("proposedBudget");
        		if(claimableAmount==null)claimableAmount=0.0;
        		if(maxAllowedBudget==null)maxAllowedBudget=0.0;
        		if(proposedBudget==null)proposedBudget=0.0;
        		Map<String, Object> newheader = new HashMap<String, Object>();
        		newheader.putAll(header);
        		newheader.put("claimableAmount", claimableAmount);
        		newheader.put("maxAllowedBudget", maxAllowedBudget);
        		System.out.println(((BigInteger)header.get("activityTypeId")).intValue()+":"+ proposedBudget+":"+ maxAllowedBudget);
        		claimableAmount = marketingActivityProposalRepo.getClaimableAmount(((BigInteger)header.get("activityTypeId")).intValue(), proposedBudget, maxAllowedBudget);
        		newheader.put("claimableAmount", claimableAmount);
        		
        		// Suraj_Start--24-01-2023
				Double approvedAmunt = (Double) header.get("approvedAmount");
				if (userAuthentication.getKubotaEmployeeId() != null && approvedAmunt == 0.0
						&& !((String) header.get("activityType")).equalsIgnoreCase("ACP BOARD")) {
					
					newheader.put("approvedAmount",
							noOfProposalsOfDealer <= 6 && dealerCreatedMonths <= 6 ? (claimableAmount * 70) / 100
									: (claimableAmount * 50) / 100);
				}
        		// Suraj_End--24-01-2023
        		
        		marketingActivityProposalViewDto.setActivityProposalHeaderData(newheader);
        	}else{
        		// Suraj_Start--24-01-2023
				Double approvedAmunt = (Double) header.get("approvedAmount");
				if (userAuthentication.getKubotaEmployeeId() != null && approvedAmunt == 0.0
						&& !((String) header.get("activityType")).equalsIgnoreCase("ACP BOARD")) {
					
					Double claimableAmount = (Double) header.get("claimableAmount");
					header.put("approvedAmount",
							noOfProposalsOfDealer <= 6 && dealerCreatedMonths <= 6 ? (claimableAmount * 70) / 100
									: (claimableAmount * 50) / 100);
				}
				// Suraj_End--24-01-2023
        		
        		marketingActivityProposalViewDto.setActivityProposalHeaderData(header);		
        	}
        }
        
        marketingActivityProposalViewDto.setActivityProposalHeads(heads);
        marketingActivityProposalViewDto.setActivityProposalEnquiries(enquiries);
        marketingActivityProposalViewDto.setDealerInfo(dealerInfo);
        marketingActivityProposalViewDto.setApprovalHierDetails(approvalHierDetails);
        apiResponse.setResult(marketingActivityProposalViewDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getProposalsPendingForApproval")
    public ResponseEntity<?> getProposalsPendingForApproval(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List<Map<String, Object>> result = marketingActivityProposalRepo.getProposalsPendingForApproval(userAuthentication.getUsername());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
    }
}
