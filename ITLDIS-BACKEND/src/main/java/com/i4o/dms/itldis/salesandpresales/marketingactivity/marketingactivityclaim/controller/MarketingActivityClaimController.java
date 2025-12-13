package com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.domain.ActivityClaimHead;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaim;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaimApproval;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto.MarketingActivityClaimEditRequestDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto.MarketingActivityClaimView;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto.SearchClaimDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.dto.SearchDto;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.repository.ActivityClaimHeadRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.repository.MarketingActivityClaimApprovalRepository;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.repository.MarketingActivityClaimRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityclaim.service.MarketingActivityClaimService;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.domain.MarketingActivityProposal;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.dto.ProposalApproval;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.activityproposal.domain.ProposalApprovalDto;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/marketingActivityClaim")
public class MarketingActivityClaimController {

    private static final Logger logger = LoggerFactory.getLogger(MarketingActivityClaimController.class);
    @Autowired
    private MarketingActivityClaimRepo marketingActivityClaimRepo;

    @Autowired
    private MarketingActivityProposalRepo marketingActivityProposalRepo;

    @Autowired
    private MarketingActivityClaimService marketingActivityClaimService;

    @Autowired
    private MarketingActivityClaimApprovalRepository marketingActivityClaimApprovalRepository;

//    @Autowired
//    private ActivityHeadRepo activityHeadRepo;
    
    @Autowired
    private ActivityClaimHeadRepo activityClaimHeadRepo;
    
    
    @Autowired
    private StorageService storageService;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private DesignationHierarchyRepository hierarchyRepository;

    /*@PostMapping(value = "/saveClaim")
    public ResponseEntity<?> saveClaim(@RequestBody MarketingActivityClaim marketingActivityClaim){
        Base64Util base64Util=new Base64Util();
        MultipartFile file=base64Util.base64ToMutlipartFile(marketingActivityClaim.getGstInvoiceImage());
        String claimGSTImage=file.getOriginalFilename();
        System.out.println("====================>"+claimGSTImage);
        String gstImage = "activity_claim_gst" + System.currentTimeMillis() + "_" + claimGSTImage;
        storageService.store(base64Util.base64ToMutlipartFile(marketingActivityClaim.getGstInvoiceImage()), gstImage);
        marketingActivityClaim.setGstInvoiceImage(gstImage);
        System.out.println("====================>"+gstImage);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("marketing activity claim successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.save(marketingActivityClaim));
        return ResponseEntity.ok(apiResponse);
    }*/

//    @PostMapping("/addClaim")
//    public ResponseEntity<?> addClaim(@RequestBody MarketingActivityClaim claim) {
//        ApiResponse apiResponse = new ApiResponse();
//        claim.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
//        claim.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
//        apiResponse.setResult(marketingActivityClaimRepo.save(claim));
//        List<MarketingActivityClaimApproval> approvalList = new ArrayList<>();
//        marketingActivityClaimApprovalRepository
//                .getMarketingActivityClaimApprovalHierarchyLevel(
//                        claim.getDealerMaster().getId())
//                .forEach(hierarchyId -> {
//                    MarketingActivityClaimApproval approval = new MarketingActivityClaimApproval();
//                    DesignationHierarchy hierarchy = hierarchyRepository.getOne(hierarchyId);
//                    approval.setDesignationHierarchy(hierarchy);
//                    approval.setMarketingActivityClaim(claim);
//                    approval.setApprovalStatus(Constants.PENDING_AT + hierarchy.getHierarchy());
//                    approvalList.add(approval);
//                });
//        marketingActivityClaimApprovalRepository.saveAll(approvalList);
//        apiResponse.setMessage("activity number get successfully.");
//        apiResponse.setResult(marketingActivityClaimRepo.save(claim));
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//
//    }

//    @PostMapping(value = "/saveClaim", consumes = {"multipart/form-data"})
//    public ResponseEntity<?> saveClaim(@ModelAttribute("marketingActivityClaim") MarketingActivityClaim marketingActivityClaim) {
//
//        MultipartFile gstImage = marketingActivityClaim.getGstInvoiceDocument();
//        String photoName = gstImage.getOriginalFilename();
//        String name = "activity_claim_" + System.currentTimeMillis() + "_" + photoName;
//        storageService.store(gstImage, name);
//        marketingActivityClaim.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
//        marketingActivityClaim.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
//        marketingActivityClaim.setGstInvoiceImage(photoName);
//        List<ActivityHead> claimHeads = new ArrayList<>();
//        List<Map<String, Object>> heads = marketingActivityClaim.getClaimHeads();
//
//        MarketingActivityProposal marketingActivityProposal = marketingActivityProposalRepo.findByActivityProposal(marketingActivityClaim.getMarketingActivityProposalId());
//
//            heads.forEach(h -> {
//            ActivityHead head = new ActivityHead();
//            head.setId(Long.valueOf(h.get("id").toString()));
//            head.setHeadName(h.get("headName").toString());
//            head.setAmount(Double.valueOf(h.get("amount").toString()));
//            //head.setApprovedAmount(Double.valueOf(h.get("approvedAmount").toString()));
//            head.setActualClaimAmount(Double.valueOf(h.get("actualClaimAmount").toString()));
//            MultipartFile headImage = (MultipartFile) h.get("image");
//            String photo = headImage.getOriginalFilename();
//            String headImageName = "activity_claim_head_" + System.currentTimeMillis() + "_" + photo;
//            System.out.println("Head Image Name=============>" + headImageName);
//            storageService.store(headImage, headImageName);
//            head.setHeadImage(headImageName);
//            head.setMarketingActivityProposal(marketingActivityProposal);
//            claimHeads.add(head);
//        });
//        marketingActivityProposal.setActivityHeads(claimHeads);
//        MarketingActivityProposal map = marketingActivityProposalRepo.save(marketingActivityProposal);
//        marketingActivityClaim.setMarketingActivityProposal(map);
//        marketingActivityClaimRepo.save(marketingActivityClaim);
//
//        //claim approval
//        List<MarketingActivityClaimApproval> approvalList = new ArrayList<>();
//        marketingActivityClaimApprovalRepository
//                .getMarketingActivityClaimApprovalHierarchyLevel(
//                        marketingActivityClaim.getDealerMaster().getId())
//                .forEach(hierarchyId -> {
//                    MarketingActivityClaimApproval approval = new MarketingActivityClaimApproval();
//                    DesignationHierarchy hierarchy = hierarchyRepository.getOne(hierarchyId);
//                    approval.setDesignationHierarchy(hierarchy);
//                    approval.setMarketingActivityClaim(marketingActivityClaim);
//                    approval.setApprovalStatus(Constants.PENDING_AT + hierarchy.getHierarchy());
//                    approvalList.add(approval);
//                });
//        marketingActivityClaimApprovalRepository.saveAll(approvalList);
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("marketing activity claim successfully saved.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }

    //New Service Created
//    @GetMapping(value = "/searchActivityNumberForClaim")
//    public ResponseEntity<?> searchActivityNumberForClaim(@RequestParam("activityNumber") String activityNumber) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("activity number get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityClaimRepo.searchActivityNumberForClaim(activityNumber,userAuthentication.getDealerId()));
//        return ResponseEntity.ok(apiResponse);
//    }


    //Wrong Service--->New Service Created
//    @GetMapping(value = "/getDetailsByActivityNumber")
//    public ResponseEntity<?> getDetailsByActivityNumber(@RequestParam("activityNumber") String activityNumber) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("details get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityClaimService.getMarketingActivityClaimDetailsByActivityNumber(activityNumber));
//        return ResponseEntity.ok(apiResponse);
//    }

    //In Use
    @GetMapping(value = "/searchClaimNumber")
    public ResponseEntity<?> searchClaimNumber(@RequestParam("claimNumber") String claimNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("claim number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.searchClaimNumber(claimNumber, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }


    //In Use
    @GetMapping(value = "/searchActivityNumber")
    public ResponseEntity<?> searchActivityNumber(@RequestParam("activityNumber") String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.searchActivityNumber(activityNumber,userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/searchClaimStatus")
    public ResponseEntity<?> searchClaimStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("status get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.searchClaimStatus());
        return ResponseEntity.ok(apiResponse);
    }

    //In Use
    @GetMapping(value = "/getActivityEffectiveness")
    public ResponseEntity<?> getActivityEffectiveness() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity effectiveness get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.getActivityEffectiveness());
        return ResponseEntity.ok(apiResponse);
    }

    //In Use
    @PostMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestBody SearchClaimDto searchDto) {

        ApiResponse apiResponse = new ApiResponse();
        
        List<SearchDto> list = marketingActivityClaimRepo.searchBy(searchDto.getClaimNumber(),
                searchDto.getActivityType(),
                searchDto.getActivityNumber(),
                searchDto.getClaimStatus(),
                searchDto.getFromDate(),
                searchDto.getToDate(),
                userAuthentication.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getManagementAccess(),
                searchDto.getPage(),
                searchDto.getSize(),
                userAuthentication.getUsername(),
                'N',0L, searchDto.getState());
        
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setMessage("claim  get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(list);
        apiResponse.setCount(count);


        return ResponseEntity.ok(apiResponse);
    }

    //Very Complicated Can't Understand Also Not Working
//    @GetMapping(value = "/getClaimById/{claimId}")
//    public ResponseEntity<?> getClaimById(@PathVariable("claimId") Long claimId) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("claim get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityClaimService.getClaimById(claimId));
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping("/activityClaimGroupApproval")
    public ResponseEntity<?> activityClaimGroupApproval(@Valid @RequestBody List<ProposalApproval> proposalList) {
        ApiResponse<Object> apiResponse = new ApiResponse<>();
        ResponseEntity<?> entity = null;
        
        List<ProposalApproval> list = proposalList.stream()
            	.filter(proposalApproval -> proposalApproval.getIsSelect() != null && proposalApproval.getIsSelect())
            	.collect(Collectors.toList());
        
        String appStatus = "";
        for(ProposalApproval proposalApproval: list){
    		appStatus = proposalApproval.getApprovalStatus();
    		marketingActivityClaimApprovalRepository.approveMarketingActivityClaim(
    				proposalApproval.getActivityClaimId(),
            		userAuthentication.getKubotaEmployeeId(), 
            		proposalApproval.getRemark(),
                    userAuthentication.getUsername(), 
                    proposalApproval.getApprovalStatus(),
                    proposalApproval.getApprovedAmount());
    		marketingActivityClaimApprovalRepository.updateApprovedAmount(proposalApproval.getApprovedAmount(), proposalApproval.getActivityClaimId());
        }
        
//        String appStatus = proposalList.get(0).getApprovalStatus();
//        proposalList.stream().filter(proposalApproval -> proposalApproval.getIsSelect()!=null && proposalApproval.getIsSelect()).forEach(proposalApproval -> {
//        	marketingActivityClaimApprovalRepository
//            .approveMarketingActivityClaim(proposalApproval.getActivityClaimId(),
//                   userAuthentication.getKubotaEmployeeId(),proposalApproval.getRemark(),userAuthentication.getUsername(),proposalApproval.getApprovalStatus(),proposalApproval.getApprovedAmount());
//        	 marketingActivityClaimApprovalRepository.updateApprovedAmount(proposalApproval.getApprovedAmount(), proposalApproval.getActivityClaimId());
//    
//        });
        apiResponse.setMessage("Marketing Activity Claim have been " + appStatus);
        apiResponse.setStatus(HttpStatus.OK.value());
        entity = ResponseEntity.ok(apiResponse);
        return entity;
    }

    
    @PostMapping("/approveMarketingActivityClaim")
    @Transactional
    public ResponseEntity<?> approveMarketingActivityClaim(@Valid @RequestBody ProposalApproval proposalApproval) {
        ApiResponse apiResponse = new ApiResponse();
        /*if (marketingActivityClaimApprovalRepository
                .findByMarketingActivityClaimAndKubotaEmployeeId(proposalApproval.getActivityClaimId(), userAuthentication.getKubotaEmployeeId()) != null) {
            apiResponse.setMessage("Already claim approved by this user");
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(apiResponse);
        }*/
        
        String status = marketingActivityClaimApprovalRepository
                .approveMarketingActivityClaim(proposalApproval.getActivityClaimId(),
                        userAuthentication.getKubotaEmployeeId(), proposalApproval.getRemark(),userAuthentication.getUsername(), proposalApproval.getApprovalStatus(), proposalApproval.getApprovedAmount());

        marketingActivityClaimApprovalRepository.updateApprovedAmount(proposalApproval.getApprovedAmount(), proposalApproval.getActivityClaimId());
//        List<ActivityClaimHead> activityHeads = new ArrayList<>();

//        Optional<MarketingActivityClaim> marketingActivityClaim = marketingActivityClaimRepo
//                .findById(proposalApproval.getActivityClaimId());
        
//        marketingActivityClaim.ifPresent(marketingActivityClaim1 -> {
//            marketingActivityClaim1.setTotalApprovedAmount(proposalApproval.getApprovedAmount());
//                    marketingActivityClaimRepo.save(marketingActivityClaim1);
//                }
//        );
        //proposalApproval.getActivityHeads().forEach(approvedHead -> {
        // ActivityHead activityHead = activityHeadRepo.getOne(approvedHead.getId());
        //activityHead.setActualClaimAmount(approvedHead.getActualClaimAmount());
        // activityHeads.add(activityHead);
        // });
        //activityClaimHeadRepo.saveAll(activityHeads);
        apiResponse.setMessage(status);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }


    //Code By Tejas:-
    //////////////////////////////////////////////////////////////////////////////////////////////


    //    Save Service Activity Claim With Image
    @PostMapping(value = "/saveMarketingActivityClaim", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveMarketingActivityClaim(@ModelAttribute("serviceActivityClaim") MarketingActivityClaim marketingActivityClaim) {
    	
    	
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        marketingActivityClaim.setDealerMaster(dealerMaster);

        MarketingActivityProposal marketingActivityProposal = marketingActivityProposalRepo.findById(marketingActivityClaim.getMarketingActivityProposalId()).get();

        List<ActivityClaimHead> claimHeads = new ArrayList<>();
        List<Map<String, Object>> heads = marketingActivityClaim.getClaimHeads();//For Getting Heads

        heads.forEach(m -> {
        	ActivityClaimHead activityHead = new ActivityClaimHead();

            activityHead.setActualClaimAmount(Double.valueOf(m.get("actualClaimAmount").toString()));
            activityHead.setGstAmount(Double.valueOf(m.get("gstAmount").toString()));
            activityHead.setGstPercent(Double.valueOf(m.get("gstPercent").toString()));
            activityHead.setHeadName(m.get("headName").toString());
            
            //Start-Suraj--10-01-2023
            activityHead.setVendorName((String)m.get("vendorName"));
            activityHead.setBillNo((String)m.get("billNo"));
            
            if(m.get("billDate") != null) {
        		try {
        			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				Date billDate = dateFormat.parse((String)m.get("billDate"));
    				
    				activityHead.setBillDate(billDate);	
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}
        	}
            //End-Suraj--10-01-2023
            
            MultipartFile headImage =  m.get("image") == null ? null : (MultipartFile) m.get("image");
        	if(headImage != null){
	            String photo = headImage.getOriginalFilename();
	            String headImageName = "activity_claim_head_" + System.currentTimeMillis() + "_" + photo;
	            storageService.store(headImage, headImageName);
	            activityHead.setHeadImage(headImageName);
	            activityHead.setMarketingActivityClaim(marketingActivityClaim);
            }
        	claimHeads.add(activityHead);
           
        });
        marketingActivityClaim.setClaimHeadList(claimHeads);
        marketingActivityClaim.setMarketingActivityProposal(marketingActivityProposal);
        marketingActivityClaim.setCreatedBy(userAuthentication.getLoginId());
        marketingActivityClaimRepo.save(marketingActivityClaim);
        //claim approval

        List<MarketingActivityClaimApproval> approvalList = new ArrayList<>();
        marketingActivityClaimApprovalRepository
                .getMarketingActivityClaimApprovalHierarchyLevel(
                        marketingActivityClaim.getDealerMaster().getId())
                .forEach(designationHierarchy -> {
                    MarketingActivityClaimApproval approval = new MarketingActivityClaimApproval();
                    approval.setClaimId(marketingActivityClaim.getClaimId());
                    approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                    approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                    approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                    approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                    approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                    approval.setRejectedFlag('N');
                    approvalList.add(approval);
                });
        marketingActivityClaimApprovalRepository.saveAll(approvalList);
        ApiResponse<MarketingActivityClaim> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Marketing Activity Claim Save Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    //Save Marketing Activity Claim Without Image
//    @PostMapping("/saveMarketingActivityClaim")
//    public ResponseEntity<?> saveMarketingActivityClaim(@RequestBody MarketingActivityClaim marketingActivityClaim)
//    {
//        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
//        marketingActivityClaim.setDealerEmployeeMaster(dealerEmployeeMaster);
//        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
//        marketingActivityClaim.setDealerMaster(dealerMaster);
//        marketingActivityClaimRepo.save(marketingActivityClaim);
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Marketing Activity Claim Save Successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }


    @GetMapping(value = "/searchActivityNumberForClaim")
    public ResponseEntity<?> searchActivityNumberForClaim(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());

        List<Map<String, Object>> map = marketingActivityClaimRepo.checkActivityNumber(userAuthentication.getDealerId(),activityNumber);
        /*if (map.get("result").equals(false)) {
            List<Map<String, Object>> activityNo = marketingActivityClaimRepo.searchActivityNumberForClaim(activityNumber, userAuthentication.getDealerId());
            
        } else {
            apiResponse.setResult(map);
            apiResponse.setMessage("Marketing Activity Claim Already Created");
        }*/
        apiResponse.setMessage("Create Marketing Activity Claim");
        apiResponse.setResult(map);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getActivityClaimHeaderData")
    public ResponseEntity<?> getActivityClaimHeaderData(@RequestParam Long activityNumberId) {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Activity Header Data Get Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.getActivityClaimHeaderData(activityNumberId));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getActivityClaimHeads")
    public ResponseEntity<?> getActivityClaimHeads(@RequestParam Long activityNumberId) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Activity Heads Get Successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.getActivityClaimHeads(activityNumberId));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getMarketingActivityClaimById/{id}")
    public ResponseEntity<?> getMarketingActivityClaimById(@PathVariable Long id) {

        ApiResponse<MarketingActivityClaimView> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Marketing Activity Claim By Id Details");

        Map<String, Object> header = marketingActivityClaimRepo.getViewHeaderData(id);
        List<Map<String, Object>> heads = marketingActivityClaimRepo.getViewHeads(id);
        Map<String, Object> dealerInfo = marketingActivityClaimRepo.getDealerInfo(id);
        List<Map<String, Object>> reportImages = marketingActivityClaimRepo.getViewReportPhotos(id);
        List<Map<String, Object>> approvalDetails = null;
        if(userAuthentication.getDealerId()==null){
        	approvalDetails = marketingActivityClaimApprovalRepository.getApprovalHierDetails(id, userAuthentication.getUsername());
        }
        MarketingActivityClaimView marketingActivityClaimView = new MarketingActivityClaimView();

        marketingActivityClaimView.setActivityClaimHeaderData(header);
        marketingActivityClaimView.setActivityClaimHeads(heads);
        marketingActivityClaimView.setDealerInfo(dealerInfo);
        marketingActivityClaimView.setReportImages(reportImages);
        marketingActivityClaimView.setApprovalDetails(approvalDetails);
        	
        apiResponse.setResult(marketingActivityClaimView);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getGstDropDown")
    public ResponseEntity<?> getGstDropDown() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("GST Drop Down Values Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityClaimRepo.getGstDropDown());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getActivityReportPhotosByProposalId")
    public ResponseEntity<?> getActivityReportPhotosByProposalId(@RequestParam Long activityNumberId) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Marketing Activity Report Photos get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((marketingActivityClaimRepo.getActivityReportPhotos(activityNumberId)));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getClaimsPendingForApproval")
    public ResponseEntity<?> getClaimsPendingForApproval(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List<Map<String, Object>> result = marketingActivityClaimRepo.getClaimPendingForApproval(userAuthentication.getUsername());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @param marketingActivityClaim
     * @return
     */
    @PostMapping(value = "/editMarketingActivityClaim")
    public ResponseEntity<?> editMarketingActivityClaim(@ModelAttribute MarketingActivityClaimEditRequestDto marketingActivityClaim) {
    	try {
    		ApiResponse<?> apiResponse = new ApiResponse<>();
        	
        	List<ActivityClaimHead> activityClaimHeads = new ArrayList<>();
        	if(!marketingActivityClaim.getClaimStatus().equalsIgnoreCase("Approved") && marketingActivityClaim.getActivityClaimHeads() != null) {
        		
        		marketingActivityClaim.getActivityClaimHeads().forEach(head -> {
                	ActivityClaimHead activityHead = activityClaimHeadRepo.getOne(head.getId());
                	
                	activityHead.setActualClaimAmount(head.getActualClaimAmount());
                	activityHead.setGstAmount(head.getGstAmount());
                	activityHead.setGstPercent(head.getGstPercent());
                	activityHead.setVendorName(head.getVendorName());
                	activityHead.setBillNo(head.getBillNo());
                	
                	if(head.getBillDateTran() != null) {
                		try {
                			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            				Date billDate = dateFormat.parse(head.getBillDateTran());
            				activityHead.setBillDate(billDate);			
            			} catch (ParseException e) {
            				e.printStackTrace();
            			}
                	}
                	
					MultipartFile headImage = head.getImage();
					if(headImage != null){
						//deleting existing file stored already
						String imageName = activityHead.getHeadImage();
						storageService.deleteExistingFile(imageName);

						//saving updated image
						String photo = headImage.getOriginalFilename();
						String headImageName = "activity_claim_head_" + System.currentTimeMillis() + "_" + photo;
						storageService.store(headImage, headImageName);
						activityHead.setHeadImage(headImageName);
					}
                	
                	activityClaimHeads.add(activityHead);
                });
        		
        		//saving changes in the ClaimHead list
        		activityClaimHeadRepo.saveAll(activityClaimHeads);
        		
        		apiResponse.setMessage("Marketing Activity Claim Update Successfully");
                apiResponse.setStatus(HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);
        	}
        	else {
        		apiResponse.setMessage("Cann't edit as Claim is already approved!");
                apiResponse.setStatus(HttpStatus.CONFLICT.value());
                return ResponseEntity.ok(apiResponse);
        	}
		} catch (Exception e) {
			logger.info("/editMarketingActivityClaim Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!");
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
    }
    
}