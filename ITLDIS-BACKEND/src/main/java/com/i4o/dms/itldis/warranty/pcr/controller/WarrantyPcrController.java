package com.i4o.dms.itldis.warranty.pcr.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.common.sys.controller.SysLookupRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.masters.warranty.repository.WarrantyMtPartFailureCodeRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardDetailedExcelResponseDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchDto;
import com.i4o.dms.itldis.service.jobcard.dto.JobcardSearchResponseDto;
import com.i4o.dms.itldis.service.jobcard.repository.ServiceJobCardRepo;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcr;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcrApproval;
import com.i4o.dms.itldis.warranty.pcr.domain.WarrantyPcrPhotos;
import com.i4o.dms.itldis.warranty.pcr.dto.JobCardDto;
import com.i4o.dms.itldis.warranty.pcr.dto.PcrApprovalDto;
import com.i4o.dms.itldis.warranty.pcr.dto.PcrViewDto;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrDetailsExcelResponse;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrResponseDto;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrSearchDto;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrSummaryExcelResponse;
import com.i4o.dms.itldis.warranty.pcr.dto.WarrantyPcrViewDto;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrApprovalRepository;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrPhotosRepo;
import com.i4o.dms.itldis.warranty.pcr.repository.WarrantyPcrRepo;
import com.i4o.dms.itldis.warranty.pcr.service.WarrantyPcrService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/pcr")
public class WarrantyPcrController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
	private SysLookupRepo sysLookupRepo;
	
	@Autowired
	private EnquiryRepo enquiryRepo;
	
    @Autowired
    private WarrantyPcrRepo warrantyPcrRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private WarrantyMtPartFailureCodeRepo warrantyMtPartFailureCodeRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private WarrantyPcrService warrantyPcrService;

    @Autowired
    private WarrantyPcrApprovalRepository warrantyPcrApprovalRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private ServiceJobCardRepo serviceJobCardRepo;


    @Autowired
    private DesignationHierarchyRepository designationHierarchyRepository;
    
    @Autowired
    private StorageService storageService;
    
    @Autowired
    private WarrantyPcrPhotosRepo warrantyPcrPhotosRepo;



    @GetMapping("/dropDownFieldCondition")
    public ResponseEntity<?> dropDownFieldCondition() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.dropDownFieldCondition());
        apiResponse.setMessage("drop down field condition");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dropDownCropCondition")
    public ResponseEntity<?> dropDownCropCondition() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.dropDownCropCondition());
        apiResponse.setMessage("drop down crop condition");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/dropDownImplCategory")
    public ResponseEntity<?> dropDownImplCategory() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(sysLookupRepo.findByLookuptypecode("WA_IMPL_CTG"));
        apiResponse.setMessage("Implementation Category");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/dropDownFailureType")
    public ResponseEntity<?> dropDownFailureType(@RequestParam Long  serviceJobcardId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.dropDownFailureType(serviceJobcardId));
        apiResponse.setMessage("drop down failure type");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/saveWarrantyPcr", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveWarrantyPcr(@RequestPart(value = "warrantyPcr") WarrantyPcr warrantyPcr, @RequestPart(value = "multipartFileList",required = false) List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = warrantyPcrService.saveWarrantyPcr(warrantyPcr,multipartFileList);

        apiResponse.setMessage("PCR Submitted Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


//    @PostMapping(value = "/saveWarrantyPcrTest")
//    public ResponseEntity<?> saveWarrantyPcr(@RequestBody WarrantyPcr warrantyPcr) {
//        ApiResponse apiResponse = new ApiResponse();
//        long dealerId = 2;
//        long dealerEmployeeId = 4;
//        warrantyPcr.setDealerMaster(dealerMasterRepo.getOne(dealerId));
//        warrantyPcr.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(dealerEmployeeId));
//        warrantyPcr.setLastModifiedBy(dealerEmployeeMasterRepo.getOne(dealerEmployeeId));
//        warrantyPcr.setStatus(warrantyPcr.getDraftFlag() ? "Draft" : "Open");
//        List<SparePartRequisitionItem> spItemlist = new ArrayList<>();
//        warrantyPcr.getSparePartRequisitionItemList().forEach(sparePartRequisitionItem -> {
//            SparePartRequisitionItem sparePartRequisitionItem1 = sparePartRequisitionItemRepo.getOne(sparePartRequisitionItem.getId());
//            // sparePartRequisitionItem1.setApprovedQuantity(sparePartRequisitionItem.getApprovedQuantity());
//            sparePartRequisitionItem1.setWarrantyMtPartFailureCode(warrantyMtPartFailureCodeRepo.findById(sparePartRequisitionItem.getWarrantyMtPartFailureCode().getId()).get());
//            spItemlist.add(sparePartRequisitionItem1);
//        });
//        //warrantyPcrRepo.save(warrantyPcr);
//        warrantyPcr.getSparePartRequisitionItem().addAll(spItemlist);
//        WarrantyPcr warrantyPcr1 = warrantyPcrRepo.save(warrantyPcr);
//
//        apiResponse.setMessage("warranty pcr saved");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//    }

    @GetMapping("/pcrEnableGoodwill/{id}")
    public ResponseEntity<?> pcrEnableGoodwill(@PathVariable Long id, @RequestParam("enableType")String enableType){
    	ApiResponse apiResponse = new ApiResponse();
    	
    	if(enableType.equalsIgnoreCase("service"))
    		warrantyPcrRepo.enableServiceGoodWill(userAuthentication.getKubotaEmployeeId(),userAuthentication.getLoginId(), id);
    	else
    		warrantyPcrRepo.enableSalesGoodWill(userAuthentication.getKubotaEmployeeId(), userAuthentication.getLoginId(), id);
    	
        apiResponse.setMessage("PCR GoodWill Enabled Successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/addWarrantyPcr")
    public ResponseEntity<?> addWarrantyPcr(@RequestBody WarrantyPcr warrantyPcr) {
        ApiResponse apiResponse = new ApiResponse();
        warrantyPcr.setBranchId(userAuthentication.getBranchId());
        warrantyPcr.setCreatedBy(userAuthentication.getLoginId());
        if(warrantyPcr.getId()!=null){
        	warrantyPcr.setLastModifiedBy(userAuthentication.getLoginId());
        	warrantyPcr.setLastModifiedDate(new Date());
        }
        warrantyPcrRepo.save(warrantyPcr);
        if (warrantyPcr.getStatus().equals("Open")) {
            List<WarrantyPcrApproval> warrantyPcrApprovals = new ArrayList<>();
            warrantyPcrApprovalRepository.getWarrantyPcrApprovalHierarchyLevel(userAuthentication.getDealerId())
                    .forEach(designationHierarchy -> {
                        WarrantyPcrApproval approval = new WarrantyPcrApproval();
                        approval.setWarrantyPcrId(warrantyPcr.getId());
                        approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                        approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                        approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                        approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                        approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                        approval.setRejectedFlag('N');
                        warrantyPcrApprovals.add(approval);
                    });
            warrantyPcrApprovalRepository.saveAll(warrantyPcrApprovals);
        }
        apiResponse.setMessage("PCR submitted Successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value="pcrSpecialApproval")
    public ResponseEntity<?> pcrSpecialApproval(@RequestParam Long pcrId, @RequestParam String remark) {
        ApiResponse apiResponse = new ApiResponse();
       
        List<WarrantyPcrApproval> warrantyPcrApprovals = new ArrayList<>();
        warrantyPcrApprovalRepository.getWarrantyPcrSpecialApprovalHierarchyLevel(userAuthentication.getUsername())
                .forEach(designationHierarchy -> {
                    WarrantyPcrApproval approval = new WarrantyPcrApproval();
                    approval.setWarrantyPcrId(pcrId);
                    approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                    approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                    approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                    approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                    approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                    approval.setRejectedFlag('N');
                    warrantyPcrApprovals.add(approval);
                });
        warrantyPcrApprovalRepository.saveAll(warrantyPcrApprovals);
      
        warrantyPcrRepo.updatePcrStatus(userAuthentication.getLoginId(), pcrId, remark);
        
        apiResponse.setMessage("PCR Special Approval submitted Successfully ");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/searchWarrantyPcr")
    public ResponseEntity<?> warrantyPcrSearch(@RequestBody WarrantyPcrSearchDto warrantyPcr) {
        ApiResponse apiResponse = new ApiResponse();
        List<WarrantyPcrResponseDto> warrantyPcrResponseDtoList = warrantyPcrRepo.warrantyPcrSearch(userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId()==null?warrantyPcr.getDealerId():userAuthentication.getDealerId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		warrantyPcr.getPcrNo(),
                warrantyPcr.getStatus(), warrantyPcr.getPcrFromDate(), warrantyPcr.getPcrToDate(), warrantyPcr.getJobCardNo(),
                warrantyPcr.getChassisNo(), warrantyPcr.getEngineNo(), warrantyPcr.getJobCardFromDate(),
                warrantyPcr.getJobCardToDate(), 
                warrantyPcr.getPartNo(),
                warrantyPcr.getProduct(),
                warrantyPcr.getSeries(),
                warrantyPcr.getMachineModel(),
                warrantyPcr.getSubModel(),
                warrantyPcr.getVariant(),
                warrantyPcr.getPage(), warrantyPcr.getSize(),'N',
                warrantyPcr.getOrgHierarchyId(),
                userAuthentication.getUsername(),
                warrantyPcr.getBranch(),
                warrantyPcr.getState());

        Long count = 0l;

        if(warrantyPcrResponseDtoList!=null && warrantyPcrResponseDtoList.size()>0){
        	count = warrantyPcrResponseDtoList.get(0).getTotalCount();
        }
        apiResponse.setResult(warrantyPcrResponseDtoList);
        apiResponse.setCount(count);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("search warranty pcr");
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/jobCardForPcr/{id}")
    public ResponseEntity<?> jobCardForPcr(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        JobCardDto jobCardDto = new JobCardDto();
        jobCardDto.setJobCardViewDto(warrantyPcrRepo.jobCardForPcr(id));
        jobCardDto.setJobCardPartDto(warrantyPcrRepo.jobCardPartDetailsForPcr(id));
        jobCardDto.setLabourCharge(warrantyPcrRepo.getLobourChargeInfo(id));
        jobCardDto.setOutSideLabourCharge(warrantyPcrRepo.getJobCodeInfo(id));
        Map<String,Object> warrantyPeriod = warrantyPcrRepo.getWarrantyPeriodDetail(jobCardDto.getJobCardViewDto().getChassisNo());
        if(warrantyPeriod!=null){
        	jobCardDto.setWarrantyPeriod(warrantyPeriod);
        }
        apiResponse.setMessage("job card data ");
        apiResponse.setResult(jobCardDto);
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/serviceHistory/{jobCardId}")
    public ResponseEntity<?> serviceHistory(@PathVariable Long jobCardId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.jobCardHistoryForPcr(jobCardId));

        apiResponse.setMessage("warranty pcr history");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/warrantyPcrView")
    public ResponseEntity<?> warrantPcrView(@RequestParam String pcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyPcrViewDto warrantyPcrViewDto= warrantyPcrRepo.findByPcrNo(pcrNo);
        warrantyPcrViewDto.setCreateWcr(warrantyPcrRepo.isCreateWcr(pcrNo,"PCR"));
        PcrViewDto pcrViewDto=new PcrViewDto();
        pcrViewDto.setWarrantyPcrViewDto(warrantyPcrViewDto);
        String wcrNo = warrantyPcrRepo.getWcrNo(warrantyPcrViewDto.getId());
        warrantyPcrViewDto.setWcrNo(wcrNo);
        pcrViewDto.setWarrantyPart(warrantyPcrRepo.getJobCardPartWarrantyInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setLabourCharge(warrantyPcrRepo.getLobourChargeInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setOutSideLabourCharge(warrantyPcrRepo.getJobCodeInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        if(userAuthentication.getDealerId()==null){
        	pcrViewDto.setApprovalDetails(warrantyPcrApprovalRepository.getApprovalHierDetails(warrantyPcrViewDto.getId(), userAuthentication.getUsername()));
        }
        pcrViewDto.setGoodwillEnable(warrantyPcrRepo.warrantyGoodwillEnable(warrantyPcrViewDto.getId(), userAuthentication.getUsername()));
        pcrViewDto.setFiles(warrantyPcrRepo.getFilesInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        Map<String,Object> warrantyPeriod = warrantyPcrRepo.getWarrantyPeriodDetail(warrantyPcrViewDto.getServiceJobCard().getMachineInventory().getChassisNo());
        if(warrantyPeriod!=null){
        	pcrViewDto.setWarrantyPeriod(warrantyPeriod);
        }
        apiResponse.setResult(pcrViewDto);
        apiResponse.setMessage("warranty pcr view");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/warrantyPcrGoodwill")
    public ResponseEntity<?> warrantyPcrGoodwill(@RequestParam String pcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyPcrViewDto warrantyPcrViewDto= warrantyPcrRepo.findByPcrNo(pcrNo);
        String wcrNo = warrantyPcrRepo.getWcrNo(warrantyPcrViewDto.getId());
        warrantyPcrViewDto.setWcrNo(wcrNo);
        PcrViewDto pcrViewDto=new PcrViewDto();
        pcrViewDto.setWarrantyPcrViewDto(warrantyPcrViewDto);
        pcrViewDto.setWarrantyPart(warrantyPcrRepo.getJobCardPartWarrantyInfoGoodwill(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setLabourCharge(warrantyPcrRepo.getLobourChargeInfoGoodwill(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setOutSideLabourCharge(warrantyPcrRepo.getJobCodeInfoGoodwill(warrantyPcrViewDto.getServiceJobCard().getId()));
        apiResponse.setResult(pcrViewDto);
        apiResponse.setMessage("warranty pcr goodwill");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/updatePcrFromJobcard")
    public ResponseEntity<?> warrantyPcrView(@RequestParam String pcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        WarrantyPcrViewDto warrantyPcrViewDto= warrantyPcrRepo.findByPcrNo(pcrNo);
        PcrViewDto pcrViewDto=new PcrViewDto();
        pcrViewDto.setWarrantyPcrViewDto(warrantyPcrViewDto);
        pcrViewDto.setWarrantyPart(warrantyPcrRepo.getJobCardPartWarrantyInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setLabourCharge(warrantyPcrRepo.getLobourChargeInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        pcrViewDto.setOutSideLabourCharge(warrantyPcrRepo.getJobCodeInfo(warrantyPcrViewDto.getServiceJobCard().getId()));
        Map<String,Object> warrantyPeriod = warrantyPcrRepo.getWarrantyPeriodDetail(warrantyPcrViewDto.getServiceJobCard().getMachineInventory().getChassisNo());
        if(warrantyPeriod!=null){
        	pcrViewDto.setWarrantyPeriod(warrantyPeriod);
        }
        apiResponse.setResult(pcrViewDto);
        apiResponse.setMessage("PCR updated successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }



    @PostMapping("/approveWarrantyPcr")
    public ResponseEntity<?> approveWarrantyPcr(@RequestBody PcrApprovalDto pcrApprovalDto) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(warrantyPcrService.approvedPcr(pcrApprovalDto));
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/warrantyPcrViewTest")
    public ResponseEntity<?> warrantPcrViewTest(@RequestParam String pcrNo) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("warranty pcr history");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }
//     public ResponseEntity<?> approveWarrantyPcr(@RequestBody PcrApprovalDto pcrApprovalDto) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage(warrantyPcrService.approvedPcr(pcrApprovalDto));
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return ResponseEntity.ok(apiResponse);
//
//    }

    @GetMapping("/autoCompletePcrNo")
    public ResponseEntity<?> autoCompletePcrNo(@RequestParam String pcrNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.autoCompletePcrNo(pcrNo,userAuthentication.getUsername()));
        apiResponse.setMessage("auto complete pcr no");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/dropDownPcrStatus")
    public ResponseEntity<?> dropDownPcrStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(warrantyPcrRepo.dropDownPcrStatus());
        apiResponse.setMessage("drop down pcr status");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);

    }


    @PostMapping("/updatePcr")
    public ResponseEntity<?> updatePcr(@RequestPart WarrantyPcr warrantyPcr,@RequestPart List<MultipartFile> multipartFileList ,@RequestPart List<MultipartFile> multipartVideoList) {
        ApiResponse apiResponse = new ApiResponse();
        /*List<SparePartRequisitionItem> spItemlist = new ArrayList<>();
        WarrantyPcr warrantyPcr1=warrantyPcrRepo.getOne(warrantyPcr.getId());*/
        /*warrantyPcr.getServiceJobCard().getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
            SparePartRequisitionItem sparePartRequisitionItem1 = sparePartRequisitionItemRepo.getOne(sparePartRequisitionItem.getId());

                sparePartRequisitionItem1.setPcrQuantity(sparePartRequisitionItem.getPcrQuantity());
                sparePartRequisitionItem1.setWarrantyMtPartFailureCode(warrantyMtPartFailureCodeRepo.findById(sparePartRequisitionItem.getWarrantyMtPartFailureCode().getId()).get());
                spItemlist.add(sparePartRequisitionItem1);

        });*/

        //warrantyPcr1.setDealerRemarks(warrantyPcr.getDealerRemarks());

        /*if(spItemlist!=null) {
            warrantyPcr1.getServiceJobCard().getSparePartRequisitionItem().addAll(spItemlist);
        }*/
        /*ServiceJobCard serviceJobCard=serviceJobCardRepo.getOne(warrantyPcr.getServiceJobCard().getId());
        serviceJobCard.setSparePartRequisitionItem(spItemlist);
        warrantyPcr.setServiceJobCard(serviceJobCard);
        warrantyPcr1.setAllowVideoUpload(false);
        warrantyPcr1.setStatus("Open");
        warrantyPcr1.setLastModifiedBy(userAuthentication.getLoginId());
        warrantyPcr1.setLastModifiedDate(new Date());
        
       warrantyPcrRepo.save(warrantyPcr1);
       */
        
        
        
        warrantyPcrRepo.updatePcr(userAuthentication.getLoginId(), warrantyPcr.getId(), warrantyPcr.getDealerRemarks());
        
       if(warrantyPcr.getServiceJobCard().getSparePartRequisitionItem()!=null){
	        warrantyPcr.getServiceJobCard().getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
	            sparePartRequisitionItemRepo.updatePartPcrQty(sparePartRequisitionItem.getPcrQuantity(), warrantyPcr.getId(), sparePartRequisitionItem.getWarrantyMtPartFailureCode().getId(), sparePartRequisitionItem.getId()); 
	        });
       }
       if(warrantyPcr.getServiceJobCard().getLabourCharge()!=null){
	        warrantyPcr.getServiceJobCard().getLabourCharge().forEach(labour -> {
	        	sparePartRequisitionItemRepo.updateLabourPcrAmount(labour.getPcrAmount(), warrantyPcr.getId(), labour.getId());
			});
       }        
       if(warrantyPcr.getServiceJobCard().getOutsideJobCharge()!=null){
	        warrantyPcr.getServiceJobCard().getOutsideJobCharge().forEach(outsidecharge -> {
	        	sparePartRequisitionItemRepo.updateOutsideChargePcrAmount(outsidecharge.getPcrAmount(), warrantyPcr.getId(), outsidecharge.getId());
			});
       }
       
       if(warrantyPcr.getServiceJobCard().getId()!=null) {
        String result=   warrantyPcrRepo.updateQtyUpdateFlag(warrantyPcr.getServiceJobCard().getId());
       }
       
       if (warrantyPcr.getStatus()!=null && warrantyPcr.getStatus().equals("Open")) {
    	    List<WarrantyPcrApproval> oldapproval = warrantyPcrApprovalRepository.findBywarrantyPcrId(warrantyPcr.getId());
	       	if(oldapproval!=null){
	       		oldapproval.forEach(approval -> {
	       			if(approval.getApprovedDate()==null){
	       				approval.setApprovedDate(new Date());
	       				approval.setRemark("Auto Close");
	       				warrantyPcrApprovalRepository.save(approval);
	       			}
	       		});
	       	}
       }

      	
       List<WarrantyPcrApproval> warrantyPcrApprovals = new ArrayList<>();
       warrantyPcrApprovalRepository.getWarrantyPcrApprovalHierarchyLevel(userAuthentication.getDealerId())
               .forEach(designationHierarchy -> {
                   WarrantyPcrApproval approval = new WarrantyPcrApproval();
                   approval.setWarrantyPcrId(warrantyPcr.getId());
                   approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                   approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                   approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                   approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                   approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                   approval.setRejectedFlag('N');
                   warrantyPcrApprovals.add(approval);
               });
       warrantyPcrApprovalRepository.saveAll(warrantyPcrApprovals);
       
       
       if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
           multipartFileList.forEach(m -> {
               WarrantyPcrPhotos warrantyPcrPhotos = new WarrantyPcrPhotos();
               String pcrPhoto = m.getOriginalFilename();
               String photoName = "PCR" + System.currentTimeMillis() + "_" + pcrPhoto;
               storageService.store(m, photoName);
               warrantyPcrPhotos.setFileName(photoName);
               warrantyPcrPhotos.setWarrantyPcr(warrantyPcr);
               warrantyPcrPhotosRepo.save(warrantyPcrPhotos);

           });
       }
       
       if (multipartVideoList.size() <= 5 && !multipartVideoList.isEmpty()) {
    	   multipartVideoList.forEach(m -> {
               WarrantyPcrPhotos warrantyPcrPhotos = new WarrantyPcrPhotos();
               String pcrVideo = m.getOriginalFilename();
               String videoName = "PCR" + System.currentTimeMillis() + "_" + pcrVideo;
               storageService.store(m, videoName);
               warrantyPcrPhotos.setFileName(videoName);
               warrantyPcrPhotos.setWarrantyPcr(warrantyPcr);
               warrantyPcrPhotosRepo.save(warrantyPcrPhotos);

           });
       }
       
       apiResponse.setMessage("PCR update successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       return ResponseEntity.ok(apiResponse);

    }
    
    @PostMapping("/downloadPcrExcelReport")
    public ResponseEntity<InputStreamResource> pcrExcelReport(@RequestBody WarrantyPcrSearchDto pcrExcel, HttpServletResponse response) throws IOException{
    	Integer size = Integer.MAX_VALUE-1;
        List<WarrantyPcrSummaryExcelResponse> summary = warrantyPcrRepo.warrantyreport(userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId()==null?pcrExcel.getDealerId():userAuthentication.getDealerId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		pcrExcel.getPcrNo(),pcrExcel.getStatus(), pcrExcel.getPcrFromDate(), pcrExcel.getPcrToDate(), pcrExcel.getJobCardNo(),
        		pcrExcel.getChassisNo(), pcrExcel.getEngineNo(), pcrExcel.getJobCardFromDate(),pcrExcel.getJobCardToDate(), pcrExcel.getPartNo(),
        		pcrExcel.getProduct(),pcrExcel.getSeries(),pcrExcel.getMachineModel(),pcrExcel.getSubModel(),pcrExcel.getVariant(),
        		pcrExcel.getBranch(),'N',pcrExcel.getOrgHierarchyId(),userAuthentication.getUsername(),"summary", pcrExcel.getState());
        
        List<WarrantyPcrDetailsExcelResponse> details = warrantyPcrRepo.warrantyReportDetails(userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId()==null?pcrExcel.getDealerId():userAuthentication.getDealerId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		pcrExcel.getPcrNo(),pcrExcel.getStatus(), pcrExcel.getPcrFromDate(), pcrExcel.getPcrToDate(), pcrExcel.getJobCardNo(),
        		pcrExcel.getChassisNo(), pcrExcel.getEngineNo(), pcrExcel.getJobCardFromDate(),pcrExcel.getJobCardToDate(), pcrExcel.getPartNo(),
        		pcrExcel.getProduct(),pcrExcel.getSeries(),pcrExcel.getMachineModel(),pcrExcel.getSubModel(),pcrExcel.getVariant(),
        		pcrExcel.getBranch(),'N',pcrExcel.getOrgHierarchyId(),userAuthentication.getUsername(),"", pcrExcel.getState());

		
    	ByteArrayInputStream in = ExcelCellGenerator.warrantyPcrExcelReport(summary,details);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "Warranty_Pcr_Report_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    	return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }


    @GetMapping("/getDealerStates")
    public ResponseEntity<?> getStatesEnuiry(@RequestParam(required=false) Long dealerId){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get States data");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getStatesEnuiry(0l, userAuthentication.getUsername(), dealerId));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getPcrNumberByJobCardId")		//Suraj 11-10-2022
    public ResponseEntity<?> getPcrNumber(@RequestParam Long jobCardId) {
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get PCR Number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(warrantyPcrRepo.getPcrNumberByJobCardId(jobCardId));
        return ResponseEntity.ok(apiResponse);
    }
    
    
    @GetMapping(value = "/getPcrPendingForApproval")
    public ResponseEntity<?> getPcrPendingForApproval(){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		ApiResponse<?> apiResponse = warrantyPcrService.getPcrPendingForApproval();
    		apiResponse.setMessage("Get PCR Pending For Approval Details Success");
			apiResponse.setStatus(HttpStatus.OK.value());
			return ResponseEntity.ok(apiResponse);
			
    	}catch(Exception e){
    		ApiResponse<?> apiResponse = new ApiResponse<>();
			System.out.println(e.getMessage());
			logger.info("/getPcrPendingForApproval Exception: " + e.getMessage());
			apiResponse.setMessage("There is some error occured");
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
    	}
    }
    
}
