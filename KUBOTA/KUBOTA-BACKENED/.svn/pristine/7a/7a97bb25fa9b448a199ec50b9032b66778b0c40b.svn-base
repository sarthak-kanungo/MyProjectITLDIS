package com.i4o.dms.kubota.service.activityclaim.controller;


import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityclaim.domain.MarketingActivityClaim;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.activityclaim.domain.ServiceActivityClaim;
import com.i4o.dms.kubota.service.activityclaim.domain.ServiceActivityClaimApproval;
import com.i4o.dms.kubota.service.activityclaim.dto.ActivityClaimResponseSearchDto;
import com.i4o.dms.kubota.service.activityclaim.dto.ActivityClaimSearchResponse;
import com.i4o.dms.kubota.service.activityclaim.dto.ServiceActivityClaimEditReqDto;
import com.i4o.dms.kubota.service.activityclaim.dto.ServiceActivityClaimViewDto;
import com.i4o.dms.kubota.service.activityclaim.repository.ServiceActivityClaimApprovalRepository;
import com.i4o.dms.kubota.service.activityclaim.repository.ServiceActivityClaimPhotoRepo;
import com.i4o.dms.kubota.service.activityclaim.repository.ServiceActivityClaimRepo;
import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposal;
import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposalHeads;
import com.i4o.dms.kubota.service.activityproposal.domain.ServiceActivityProposalSubActivity;
import com.i4o.dms.kubota.service.activityproposal.dto.ServiceProposalApprovalDto;
import com.i4o.dms.kubota.service.activityproposal.repository.ServiceActivityProposalHeadRepo;
import com.i4o.dms.kubota.service.activityproposal.repository.ServiceActivityProposalRepo;
import com.i4o.dms.kubota.service.activityproposal.repository.ServiceActivityProposalSubActivityRepo;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/activityClaim")
public class ServiceActivityClaimController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceActivityClaimController.class);
	
    @Autowired
    private ServiceActivityClaimRepo serviceActivityClaimRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ServiceActivityClaimPhotoRepo serviceActivityClaimPhotoRepo;

    @Autowired
    private ServiceActivityProposalRepo serviceActivityProposalRepo;

    @Autowired
    private ServiceActivityProposalHeadRepo serviceActivityProposalHeadRepo;

    @Autowired
    private ServiceActivityProposalSubActivityRepo serviceActivityProposalSubActivityRepo;

    @Autowired
    private ServiceActivityClaimApprovalRepository serviceActivityClaimApprovalRepository;


    @Autowired
    private DesignationHierarchyRepository hierarchyRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;
    
    @Autowired
    private ServiceActivityProposalHeadRepo activityProposalHeadsRepo;
    
    @Autowired
    private ServiceActivityProposalSubActivityRepo proposalSubActivityRepo;

    //Save Service Activity Claim With Photo
    @PostMapping(value = "/saveServiceActivityClaim", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveServiceActivityReport(@ModelAttribute("serviceActivityClaim") ServiceActivityClaim serviceActivityClaim) {
        serviceActivityClaim.setCreatedBy(userAuthentication.getLoginId());
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        serviceActivityClaim.setDealerMaster(dealerMaster);

        ServiceActivityProposal serviceActivityProposal = serviceActivityProposalRepo.findById(serviceActivityClaim.getServiceActivityProposalId()).get();
        List<Map<String, Object>> heads = serviceActivityClaim.getClaimHeads();//For Getting Heads
        List<Map<String, Object>> subActivity = serviceActivityClaim.getSubActivities();// For Getting Sub Activities

        heads.forEach(m -> {
            ServiceActivityProposalHeads serviceActivityProposalHeads = serviceActivityProposalHeadRepo.findById(Long.valueOf(m.get("id").toString())).get();

            serviceActivityProposalHeads.setActualClaimAmount(Double.valueOf(m.get("actualClaimAmount").toString()));
            MultipartFile headImage = (MultipartFile) m.get("image");
            String photo = headImage.getOriginalFilename();
            String headImageName = "activity_claim_head_" + System.currentTimeMillis() + "_" + photo;
            storageService.store(headImage, headImageName);
            serviceActivityProposalHeads.setHeadImage(headImageName);
            
            //Start_Suraj--11-01-2024
            serviceActivityProposalHeads.setVendorName(m.get("vendorName") != null ?  m.get("vendorName").toString() : null);
            serviceActivityProposalHeads.setBillNo(m.get("billNo") != null ? m.get("billNo").toString() : null);

            if(m.get("billDate") != null) {
        		try {
        			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    				Date billDate = dateFormat.parse((String)m.get("billDate"));
    				
    				serviceActivityProposalHeads.setBillDate(billDate);		
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}
        	}
            //End_Suraj--11-01-2024

            serviceActivityProposalHeads.setServiceActivityProposal(serviceActivityProposal);
        });

        if (serviceActivityProposal.getServiceMtActivityType().getId() == 4) {

            subActivity.forEach(s -> {
                ServiceActivityProposalSubActivity serviceActivityProposalSubActivity = serviceActivityProposalSubActivityRepo.findById(Long.valueOf(s.get("id").toString())).get();

                serviceActivityProposalSubActivity.setActualClaimAmount(Double.valueOf(s.get("actualClaimAmount").toString()));
                MultipartFile headImage = (MultipartFile) s.get("image");
                String photo1 = headImage.getOriginalFilename();
                String headImageName = "activity_claim_sub_activity_" + System.currentTimeMillis() + "_" + photo1;
                storageService.store(headImage, headImageName);
                serviceActivityProposalSubActivity.setSubActivityImage(headImageName);
                
                //Start_Suraj--19-01-2024
                serviceActivityProposalSubActivity.setVendorName(s.get("vendorName") != null ? s.get("vendorName").toString() : null);
                serviceActivityProposalSubActivity.setBillNo(s.get("billNo") != null ? s.get("billNo").toString() : null);
                
                if(s.get("billDate") != null) {
            		try {
            			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        				Date billDate = dateFormat.parse((String)s.get("billDate"));
        				
        				serviceActivityProposalSubActivity.setBillDate(billDate);	
        			} catch (ParseException e) {
        				e.printStackTrace();
        			}
            	}
                //End_Suraj--19-01-2024

                serviceActivityProposalSubActivity.setServiceActivityProposal(serviceActivityProposal);
            });
        }
        serviceActivityClaim.setServiceActivityProposal(serviceActivityProposal);
        serviceActivityClaimRepo.save(serviceActivityClaim);

        List<Map<String,Object>> hierarchyList = serviceActivityClaimApprovalRepository.getServiceActivityClaimApprovalHierarchyLevel(userAuthentication.getDealerId());
        
        List<ServiceActivityClaimApproval> approvals = new ArrayList<>();
        hierarchyList.forEach(designationHierarchy -> {
            ServiceActivityClaimApproval approval = new ServiceActivityClaimApproval();
            approval.setServiceActivityClaimId(serviceActivityClaim.getId());
            approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
            approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
            approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
            approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
            approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
            approval.setRejectedFlag('N');
            approvals.add(approval);
        });
        serviceActivityClaimApprovalRepository.saveAll(approvals);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service activity Claim save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    /*Save Service Activity Claim Without Photo*/
//    @PostMapping("/saveServiceActivityClaim")
//    public ResponseEntity<?> saveServiceActivityClaim(@RequestBody ServiceActivityClaim serviceActivityClaim)
//    {
//        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
//        serviceActivityClaim.setDealerEmployeeMaster(dealerEmployeeMaster);
//        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
//        serviceActivityClaim.setDealerMaster(dealerMaster);
//        serviceActivityClaimRepo.save(serviceActivityClaim);
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Service activity Claim save successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping("/getActivityNumberForActivityClaim")
    public ResponseEntity<?> getActivityNumberForActivityClaim(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());

//        Map<String, Object> map = serviceActivityClaimRepo.checkActivityNumber(activityNumber);
//        if (map.get("result").equals(false))
//        {
            List<Map<String,Object>> activityNo =serviceActivityClaimRepo.getActivityNumberForClaim(activityNumber,userAuthentication.getDealerId());
            apiResponse.setMessage("Create Service Activity Claim");
            apiResponse.setResult(activityNo);
//        } else {
//            apiResponse.setResult(map);
//            apiResponse.setMessage("Service Activity Claim Already Created");
//        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getHeaderDataForActivityClaim")
    public ResponseEntity<?> getHeaderDataForActivityClaim(@RequestParam Long activityNumberId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getHeaderData(activityNumberId)));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getHeadsDataForActivityClaim")
    public ResponseEntity<?> getHeadsDataForActivityClaim(@RequestParam Long activityNumberId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getHeadsData(activityNumberId)));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/serviceActivityClaimSearch")
    public ResponseEntity<?> serviceActivityClaimSearch(@RequestBody ActivityClaimResponseSearchDto activityClaimResponseSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        Long dealerId;
        dealerId = activityClaimResponseSearchDto.getDealerId()==null?userAuthentication.getDealerId():activityClaimResponseSearchDto.getDealerId();
        List<ActivityClaimSearchResponse> list = serviceActivityClaimRepo.searchActivityClaim(
                activityClaimResponseSearchDto.getFromDate(),
                activityClaimResponseSearchDto.getToDate(),
                activityClaimResponseSearchDto.getActivityClaimNumber(),
                activityClaimResponseSearchDto.getActivityClaimStatus(),
                activityClaimResponseSearchDto.getActivityNumber(),
                dealerId,
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                activityClaimResponseSearchDto.getPage(),
                activityClaimResponseSearchDto.getSize(),
                userAuthentication.getUsername(), 'N', activityClaimResponseSearchDto.getOrgHierId());
        
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Service Activity Claim Search List");
        apiResponse.setResult(list);
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getActivityClaimById/{id}")
    public ResponseEntity<?> getActivityClaimById(@PathVariable Long id) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Claim by id details");

        Map<String, Object> header = serviceActivityClaimRepo.getHeaderDataForView(id);
        List<Map<String, Object>> heads = serviceActivityClaimRepo.getHeadsDataForView(id);
        List<Map<String, Object>> subActivities = serviceActivityClaimRepo.getSubActivitiesForView(id);
        List<Map<String, Object>> reportPhotos = serviceActivityClaimRepo.getViewReportPhoto(id);
        List<Map<String, Object>> approvalDetails=null;
        if(userAuthentication.getDealerId()==null){
        	approvalDetails = serviceActivityClaimApprovalRepository.getApprovalHierDetails(id, userAuthentication.getUsername());
        }
        
        ServiceActivityClaimViewDto serviceActivityClaimViewDto = new ServiceActivityClaimViewDto();

        serviceActivityClaimViewDto.setActivityClaimHeaderData(header);
        serviceActivityClaimViewDto.setActivityClaimHeads(heads);
        serviceActivityClaimViewDto.setSubActivities(subActivities);
        serviceActivityClaimViewDto.setReportPhotos(reportPhotos);
        serviceActivityClaimViewDto.setApprovalDetails(approvalDetails);
        
        apiResponse.setResult(serviceActivityClaimViewDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityClaimNumberForSearch")
    public ResponseEntity<?> getActivityClaimNumberForSearch(@RequestParam String claimNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Claim Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getClaimNumberForSearch(userAuthentication.getUsername(), claimNumber)));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityNumberForSearch")
    public ResponseEntity<?> getActivityNumberForSearch(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Proposal Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getActivityNumberForSearch(userAuthentication.getUsername(), activityNumber)));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityReportPhotosByProposalId")
    public ResponseEntity<?> getActivityReportPhotosByProposalId(@RequestParam Long activityNumberId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Report Photos get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getActivityReportPhotos(activityNumberId)));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSubActivityForActivityClaim")
    public ResponseEntity<?> getSubActivityForActivityClaim(@RequestParam Long activityProposalId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Sub Activity Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityClaimRepo.getSubActivityForClaim(activityProposalId)));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/approveServiceActivityClaim")
    public ResponseEntity<?> approveServiceActivityClaim(@RequestBody ServiceProposalApprovalDto serviceProposalApproval) {
        ApiResponse apiResponse = new ApiResponse();
        String status = serviceActivityClaimApprovalRepository
                .approveServiceActivityClaim(serviceProposalApproval.getServiceActivityClaimId(),
                        userAuthentication.getKubotaEmployeeId(), serviceProposalApproval.getRemark(),userAuthentication.getUsername(), serviceProposalApproval.getApprovalType(), serviceProposalApproval.getApprovedAmount());
        
        if(serviceProposalApproval.getSubActivityDetails()!=null){
        	serviceProposalApproval.getSubActivityDetails().forEach(dtl -> {
        		serviceActivityClaimApprovalRepository.updateSubActiivty(dtl.getClaimApprovedAmount(), dtl.getRemark(), dtl.getId());
        	});
        }
        if(serviceProposalApproval.getHeadDetails()!=null){
        	serviceProposalApproval.getHeadDetails().forEach(dtl -> {
        		serviceActivityClaimApprovalRepository.updateHeads(dtl.getClaimApprovedAmount(), dtl.getRemark(), dtl.getId());
        	});
        }
        apiResponse.setMessage(status);
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @return
     */
    @GetMapping(value = "/getClaimPendingForApproval")
    public ResponseEntity<?> getClaimPendingForApproval(){
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        
        List<Map<String, Object>> result = serviceActivityClaimRepo.getClaimPendingForApproval(userAuthentication.getUsername());
        apiResponse.setResult(result);
        
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @param ServiceActivityClaimEditReqDto
     * @return
     */
    @PostMapping(value = "/editServiceActivityClaim")
    public ResponseEntity<?> editServiceActivityClaim(@ModelAttribute ServiceActivityClaimEditReqDto activityClaimEditReqDto) {
    	try {
    		ApiResponse<?> apiResponse = new ApiResponse<>();
        	
        	List<ServiceActivityProposalHeads> activityClaimHeads = new ArrayList<>();
        	List<ServiceActivityProposalSubActivity> proposalSubActivities = new ArrayList<>();
        	
        	if(!activityClaimEditReqDto.getClaimStatus().equalsIgnoreCase("Approved")) {
        		
        		activityClaimEditReqDto.getActivityProposalHeads().forEach(head -> {
        			ServiceActivityProposalHeads activityHead = activityProposalHeadsRepo.getOne(head.getId());
                	
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
        		

        		//saving changes to the cliamHead list
        		activityProposalHeadsRepo.saveAll(activityClaimHeads);
        		
        		if(activityClaimEditReqDto.getActivityProposalSubActivities() != null && activityClaimEditReqDto.getActivityTypeId() == 4) {
        			
        			activityClaimEditReqDto.getActivityProposalSubActivities().forEach(subActivity -> {
            			ServiceActivityProposalSubActivity proposalSubActivity = proposalSubActivityRepo.getOne(subActivity.getId());
            			
            			proposalSubActivity.setVendorName(subActivity.getVendorName());
            			proposalSubActivity.setBillNo(subActivity.getBillNo());
            			
                    	if(subActivity.getBillDateTran() != null) {
                    		try {
                            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                				Date billDate = dateFormat.parse(subActivity.getBillDateTran());
                				proposalSubActivity.setBillDate(billDate);		
                			} catch (ParseException e) {
                				e.printStackTrace();
                			}
                    	}
                    	
                        MultipartFile subActivityImage = subActivity.getImage();
                    	if(subActivityImage != null){
                    		//deleting existing file stored already
                        	String imageName = proposalSubActivity.getSubActivityImage();
                        	storageService.deleteExistingFile(imageName);
                    		
                        	//saving updated image
            	            String photo = subActivityImage.getOriginalFilename();
            	            String subActivityImageName = "activity_claim_sub_activity_" + System.currentTimeMillis() + "_" + photo;
            	            storageService.store(subActivityImage, subActivityImageName);
            	            proposalSubActivity.setSubActivityImage(subActivityImageName);
                        }
                    	
                    	//saving changes to the subActivity list
                    	proposalSubActivities.add(proposalSubActivity);
            		});
        			
            		proposalSubActivityRepo.saveAll(proposalSubActivities);
        		}
        		
        		apiResponse.setMessage("Service Activity Claim Updated Successfully.");
                apiResponse.setStatus(HttpStatus.OK.value());
                return ResponseEntity.ok(apiResponse);
        	}
        	else {
        		apiResponse.setMessage("Cann't edit as Claim is already approved!");
                apiResponse.setStatus(HttpStatus.CONFLICT.value());
                return ResponseEntity.ok(apiResponse);
        	}
		} catch(Exception e) {
			logger.info("/editServiceActivityClaim Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!" + e.getMessage());
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
    }
    
}
