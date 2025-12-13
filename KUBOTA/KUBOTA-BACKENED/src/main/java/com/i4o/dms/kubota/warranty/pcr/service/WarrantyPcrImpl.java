package com.i4o.dms.kubota.warranty.pcr.service;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.DesignationHierarchyRepository;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.kubota.masters.warranty.repository.WarrantyMtPartFailureCodeRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.kubota.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcr;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrApproval;
import com.i4o.dms.kubota.warranty.pcr.domain.WarrantyPcrPhotos;
import com.i4o.dms.kubota.warranty.pcr.dto.PcrApprovalDto;
import com.i4o.dms.kubota.warranty.pcr.repository.WarrantyPcrApprovalRepository;
import com.i4o.dms.kubota.warranty.pcr.repository.WarrantyPcrPhotosRepo;
import com.i4o.dms.kubota.warranty.pcr.repository.WarrantyPcrRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.i4o.dms.kubota.configurations.Constants.ALREADY_APPROVED;
import static com.i4o.dms.kubota.configurations.Constants.FAIL;

@Service
@Slf4j
public class WarrantyPcrImpl implements WarrantyPcrService {


    @Autowired
    private WarrantyPcrRepo warrantyPcrRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private WarrantyMtPartFailureCodeRepo warrantyMtPartFailureCodeRepo;

    @Autowired
    private WarrantyPcrPhotosRepo warrantyPcrPhotosRepo;

    @Autowired
    private WarrantyPcrApprovalRepository warrantyPcrApprovalRepository;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private DesignationHierarchyRepository designationHierarchyRepository;

    @Transactional
    @Override
    public ApiResponse saveWarrantyPcr(WarrantyPcr warrantyPcr, List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();
        /*warrantyPcr.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        warrantyPcr.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        warrantyPcr.setLastModifiedBy(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        */
        warrantyPcr.setBranchId(userAuthentication.getBranchId());
        warrantyPcr.setCreatedBy(userAuthentication.getLoginId());
        if(warrantyPcr.getId()!=null){
        	warrantyPcr.setLastModifiedBy(userAuthentication.getLoginId());
        	warrantyPcr.setLastModifiedDate(new Date());
        }
        warrantyPcr.setStatus(warrantyPcr.getDraftFlag() ? "Draft" : "Open");
        WarrantyPcr warrantyPcr2 = warrantyPcrRepo.save(warrantyPcr);


        if(warrantyPcr.getServiceJobCard().getSparePartRequisitionItem()!=null){
	        warrantyPcr.getServiceJobCard().getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
	            sparePartRequisitionItemRepo.updatePartPcrQty(sparePartRequisitionItem.getPcrQuantity(), warrantyPcr2.getId(), sparePartRequisitionItem.getWarrantyMtPartFailureCode().getId(), sparePartRequisitionItem.getId()); 
	        });
        }
        if(warrantyPcr.getServiceJobCard().getLabourCharge()!=null){
	        warrantyPcr.getServiceJobCard().getLabourCharge().forEach(labour -> {
	        	sparePartRequisitionItemRepo.updateLabourPcrAmount(labour.getPcrAmount(), warrantyPcr2.getId(), labour.getId());
			});
        }        
        if(warrantyPcr.getServiceJobCard().getOutsideJobCharge()!=null){
	        warrantyPcr.getServiceJobCard().getOutsideJobCharge().forEach(outsidecharge -> {
	        	sparePartRequisitionItemRepo.updateOutsideChargePcrAmount(outsidecharge.getPcrAmount(), warrantyPcr2.getId(), outsidecharge.getId());
			});
        }
        
        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                WarrantyPcrPhotos warrantyPcrPhotos = new WarrantyPcrPhotos();
                String pcrPhoto = m.getOriginalFilename();
                String photoName = "PCR" + System.currentTimeMillis() + "_" + pcrPhoto;
                storageService.store(m, photoName);
                warrantyPcrPhotos.setFileName(photoName);
                warrantyPcrPhotos.setWarrantyPcr(warrantyPcr2);
                warrantyPcrPhotosRepo.save(warrantyPcrPhotos);

            });
        }
        if (warrantyPcr.getStatus().equals("Open")) {
        	
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
        return apiResponse;
    }

    @Override
    @Transactional
    public String approvedPcr(PcrApprovalDto pcrApprovalDto) {
        String status;
        
        //if (pcrApprovalDto.getApprovalStatus().equalsIgnoreCase("approve")) {
        	if(pcrApprovalDto.getPcrApprovalParts()!=null){
        		pcrApprovalDto.getPcrApprovalParts()
                    .forEach(pcrApprovalPartsDto -> {
                    	sparePartRequisitionItemRepo.updatePartApproveQty(pcrApprovalPartsDto.getApprovedQuantity(), pcrApprovalPartsDto.getId(),pcrApprovalPartsDto.getFailureCode().getId());
                    });
        		/* pcrApprovalPartsDto.getFailureCode().getId() is added by vinay to update failure code */
        	}
        	if(pcrApprovalDto.getPcrApprovalLabours()!=null){
        		pcrApprovalDto.getPcrApprovalLabours()
                    .forEach(pcrApprovalPartsDto -> {
                    	sparePartRequisitionItemRepo.updateLabourApproveAmount(pcrApprovalPartsDto.getApprovedAmount(), pcrApprovalPartsDto.getId());
                    });
        	}
        	if(pcrApprovalDto.getPcrApprovalOutsideCharges()!=null){
        		pcrApprovalDto.getPcrApprovalOutsideCharges()
                    .forEach(pcrApprovalPartsDto -> {
                    	sparePartRequisitionItemRepo.updateOutsideChargeApproveAmount(pcrApprovalPartsDto.getApprovedAmount(), pcrApprovalPartsDto.getId());
                    });
        	}
        //}
        
        status = warrantyPcrApprovalRepository.approveMarketingActivityProposal(pcrApprovalDto.getWarrantyPcrId(),
                userAuthentication.getKubotaEmployeeId(), pcrApprovalDto.getKaiRemarks(), 
                userAuthentication.getUsername(), pcrApprovalDto.getApprovalStatus(), 
                pcrApprovalDto.getAllowVideoUpload(), pcrApprovalDto.getManagementCheck(),
                pcrApprovalDto.getReason(),
                pcrApprovalDto.getRating(), pcrApprovalDto.getDelayReason());
        
        return status;
    }
    
    
    @Override
   	public ApiResponse<?> getPcrPendingForApproval() {
       	ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
       	apiResponse.setResult(warrantyPcrRepo.getPcrPendingForApproval(userAuthentication.getUsername()));
       	
       	return apiResponse;
       }
}
