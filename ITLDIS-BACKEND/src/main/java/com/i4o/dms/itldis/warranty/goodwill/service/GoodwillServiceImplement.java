package com.i4o.dms.itldis.warranty.goodwill.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwill;
import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwillApproval;
import com.i4o.dms.itldis.warranty.goodwill.domain.WarrantyGoodwillPhoto;
import com.i4o.dms.itldis.warranty.goodwill.dto.GoodwillApprovalDto;
import com.i4o.dms.itldis.warranty.goodwill.repository.WarrantyGoodwillApprovalRepo;
import com.i4o.dms.itldis.warranty.goodwill.repository.WarrantyGoodwillRepo;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class GoodwillServiceImplement implements GoodwillService{

    @Autowired
    SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    WarrantyGoodwillRepo warrantyGoodwillRepo;

    @Autowired
    StorageService storageService;

    @Autowired
    UserAuthentication userAuthentication;
    
    @Autowired
    WarrantyGoodwillApprovalRepo warrantyGoodwillApprovalRepo;


    
    @Override
    @Transactional
    public String approvedGwl(GoodwillApprovalDto approvalDto) {
        String status;
        
        //if (approvalDto.getApprovalStatus().equalsIgnoreCase("approve")) {
        	if(approvalDto.getApprovalParts()!=null){
        		approvalDto.getApprovalParts()
                    .forEach(pcrApprovalPartsDto -> {
                    	
                    	Map<String, Object> obj = sparePartRequisitionItemRepo.getPartPrice(pcrApprovalPartsDto.getId());
                    	
                    	Integer qty = pcrApprovalPartsDto.getApprovedQuantity()==null?0:pcrApprovalPartsDto.getApprovedQuantity();
                    	Double perc = pcrApprovalPartsDto.getGwApprovedPercent()==null?0d:pcrApprovalPartsDto.getGwApprovedPercent();
                    	
                    	Double approvedAmount = (obj.get("spmrp")==null?0d:(Double)obj.get("spmrp")) * qty ;
                    	approvedAmount =  (approvedAmount * perc)/100; 	//Added Suraj--16-10-2023
                    	
                    	Double gstPerc = (obj.get("igst_percent")==null?0d:(Double)obj.get("igst_percent"));
                		if(pcrApprovalPartsDto.getPriceType()!=null){
                			if(pcrApprovalPartsDto.getPriceType().equals("EO")){
                				approvedAmount = (obj.get("spegst")==null?0d:(Double)obj.get("spegst")) * qty ;
                				//approvedAmount = approvedAmount * (1+gstPerc/100);
                				approvedAmount = ((approvedAmount * (1 + gstPerc / 100)) * perc) / 100;	//Suraj--12-01-2023
                			}else if(pcrApprovalPartsDto.getPriceType().equals("MO")){
                				approvedAmount = (obj.get("spmgst")==null?0d:(Double)obj.get("spmgst")) * qty ;
                				//approvedAmount = approvedAmount * (1+gstPerc/100);
                				approvedAmount = ((approvedAmount * (1 + gstPerc / 100)) * perc) / 100;	//Suraj--12-01-2023
                			}
                		}
                		
//                		approvedAmount =  (approvedAmount * perc)/100; 	//Hidden Suraj--16-10-2023
                				
                    	sparePartRequisitionItemRepo.updateGoodwillPartApproveQty(qty, perc, approvedAmount, pcrApprovalPartsDto.getId(), pcrApprovalPartsDto.getPriceType());
                    });
        	}
        	if(approvalDto.getApprovalLabours()!=null){
        		approvalDto.getApprovalLabours()
                    .forEach(pcrApprovalPartsDto -> {
                    	sparePartRequisitionItemRepo.updateGoodwillLabourApproveAmount(pcrApprovalPartsDto.getApprovedAmount(), pcrApprovalPartsDto.getId());
                    });
        	}
        	if(approvalDto.getApprovalOutsideCharges()!=null){
        		approvalDto.getApprovalOutsideCharges()
                    .forEach(pcrApprovalPartsDto -> {
                    	sparePartRequisitionItemRepo.updateGoodwillOutsideChargeApproveAmount(pcrApprovalPartsDto.getApprovedAmount(), pcrApprovalPartsDto.getId());
                    });
        	}
        //}
        
        status = warrantyGoodwillApprovalRepo.approve(approvalDto.getWarrantyGwlId(),
                userAuthentication.getKubotaEmployeeId(), approvalDto.getKaiRemarks(), userAuthentication.getUsername(), approvalDto.getApprovalStatus(), approvalDto.getBudgetConsumed());
        
        return status;
    }
    
    @Override
    public ApiResponse saveGoodwill(WarrantyGoodwill warrantyGoodwill, List<MultipartFile> multipartFileList) {

        ApiResponse apiResponse=new ApiResponse();
        List<WarrantyGoodwillPhoto> warrantyGoodwillPhotoList=new ArrayList<>();
        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                WarrantyGoodwillPhoto warrantyGoodwillPhoto = new WarrantyGoodwillPhoto();
                String goodwillPhoto = m.getOriginalFilename();
                String photoName = "wwarranty_goodwill_photo" + System.currentTimeMillis() + "_" + goodwillPhoto;
                storageService.store(m, photoName);
                warrantyGoodwillPhoto.setFileName(photoName);
                warrantyGoodwillPhoto.setFileType("warranty goodwill photos");
                warrantyGoodwillPhoto.setWarrantyGoodwill(warrantyGoodwill);
                warrantyGoodwillPhotoList.add(warrantyGoodwillPhoto);
            });
        }
        if(warrantyGoodwillPhotoList!=null){
            warrantyGoodwill.setWarrantyGoodwillPhoto(warrantyGoodwillPhotoList);
        }

        warrantyGoodwillRepo.save(warrantyGoodwill);
            
        if(warrantyGoodwill.getSparePartRequisitionItem()!=null && warrantyGoodwill.getSparePartRequisitionItem().size()>0){
        	
	        warrantyGoodwill.getSparePartRequisitionItem().forEach(item -> {
	        	sparePartRequisitionItemRepo.updatePartGoodwillClaimQty(item.getGwClaimQuantity(), warrantyGoodwill.getId(), item.getPriceType(), item.getId());
	        });
	        
        }
        if(warrantyGoodwill.getLabourCharge()!=null && warrantyGoodwill.getLabourCharge().size()>0){
        	warrantyGoodwill.getLabourCharge().forEach(item -> {
        		sparePartRequisitionItemRepo.updateLabourGoodwillClaimAmount(item.getGoodwillAmount(), warrantyGoodwill.getId(), item.getId());
	        });
	        
        }
        if(warrantyGoodwill.getOutsideJobCharge()!=null && warrantyGoodwill.getOutsideJobCharge().size()>0){
        	
	        warrantyGoodwill.getOutsideJobCharge().forEach(item -> {
	        	sparePartRequisitionItemRepo.updateOutsideChargeGoodwillClaimAmount(item.getGoodwillAmount(), warrantyGoodwill.getId(), item.getId());
	        });
	        
        }
        
        if (warrantyGoodwill.getStatus().equals("Open")) {
            List<WarrantyGoodwillApproval> warrantyPcrApprovals = new ArrayList<>();
            warrantyGoodwillApprovalRepo.getWarrantyGoodwillApprovalHierarchyLevel(userAuthentication.getDealerId(), warrantyGoodwill.getWarrantyPcr().getId())
                    .forEach(designationHierarchy -> {
                        WarrantyGoodwillApproval approval = new WarrantyGoodwillApproval();
                        approval.setWarrantyGwlId(warrantyGoodwill.getId());
                        approval.setApproverLevelSeq((Integer)designationHierarchy.get("approver_level_seq"));
                        approval.setDesignationLevelId((BigInteger)designationHierarchy.get("designation_level_id"));
                        approval.setGrpSeqNo((Integer)designationHierarchy.get("grp_seq_no"));
                        approval.setIsfinalapprovalstatus((Character)designationHierarchy.get("isFinalApprovalStatus"));
                        approval.setApprovalStatus((String)designationHierarchy.get("approvalStatus"));
                        approval.setRejectedFlag('N');
                        warrantyPcrApprovals.add(approval);
                    });
            warrantyGoodwillApprovalRepo.saveAll(warrantyPcrApprovals);
        }
        
        apiResponse.setMessage("warranty goodwill created successfully");
        apiResponse.setStatus(HttpStatus.OK.value());


        return apiResponse;
    }
}

