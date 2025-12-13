package com.i4o.dms.itldis.warranty.kaiinspectionsheet.service;

import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.jobcard.domain.LabourJobCharges;
import com.i4o.dms.itldis.service.jobcard.domain.OutsideJobCharge;
import com.i4o.dms.itldis.service.jobcard.repository.LabourChargeRepo;
import com.i4o.dms.itldis.service.jobcard.repository.OutsideChargeRepo;
import com.i4o.dms.itldis.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.itldis.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain.KaiInspectionSheet;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.domain.KaiInspectionSheetPhoto;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.repository.KaiInspectionSheetPhotoRepo;
import com.i4o.dms.itldis.warranty.kaiinspectionsheet.repository.KaiInspectionSheetRepo;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaignPhoto;
import com.i4o.dms.itldis.warranty.warrantyclaimrequest.repository.WarrantyWcrApprovalRepo;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;


@Service
@Slf4j
public class KaiInspectionSheetImpl implements KaiInspectionSheetService{

    @Autowired
    private StorageService storageService;

    @Autowired
    private KaiInspectionSheetPhotoRepo kaiInspectionSheetPhotoRepo;

    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private KaiInspectionSheetRepo kaiInspectionSheetRepo;
    
    @Autowired
    private WarrantyWcrApprovalRepo warrantyWcrApprovalRepo;
    
    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private LabourChargeRepo labourChargeRepo;

    @Autowired
    private OutsideChargeRepo outsideChargeRepo;


    @Override
    @Transactional
    public ApiResponse saveKaiInspectionSheet(KaiInspectionSheet kaiInspectionSheet, List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse=new ApiResponse();
        
        kaiInspectionSheet.setInspectionDate(new Date());
        kaiInspectionSheet.setCreatedBy(userAuthentication.getLoginId());
        kaiInspectionSheet.setInspectionBy(userAuthentication.getKubotaEmployeeId());
        
        kaiInspectionSheetRepo.save(kaiInspectionSheet);
        String status = "Reject";
        List<String> s = new ArrayList<>();
        s.add(status);
        if(kaiInspectionSheet.getSparePartRequisitionItem()!=null && kaiInspectionSheet.getSparePartRequisitionItem().size()>0){
        	if(kaiInspectionSheet.getWarrantyWcr().getWcrType().equals("PCR")){
	        	kaiInspectionSheet.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
	        		if(sparePartRequisitionItem.getClaimApprovedQuantity()!=null && sparePartRequisitionItem.getClaimApprovedQuantity()>0){
	        			s.set(0,"Approve");
	        		}
		            sparePartRequisitionItemRepo.updateWarrantyClaimPartApprovedPcr(
		            		sparePartRequisitionItem.getClaimApprovedAmount(), 
		            		sparePartRequisitionItem.getClaimApprovedQuantity(), 
		            		sparePartRequisitionItem.getClaimable(),
		            		sparePartRequisitionItem.getInspectionRemark(),
		            		sparePartRequisitionItem.getKeyPartNumber(),
		            		sparePartRequisitionItem.getId()); 
		        });
        	}else{
        		kaiInspectionSheet.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
        			if(sparePartRequisitionItem.getClaimApprovedQuantity()!=null && sparePartRequisitionItem.getClaimApprovedQuantity()>0){
	        			s.set(0,"Approve");
	        		}
		            sparePartRequisitionItemRepo.updateWarrantyClaimPartApprovedGoodwill(
		            		sparePartRequisitionItem.getClaimApprovedAmount(), 
		            		sparePartRequisitionItem.getClaimApprovedQuantity(), 
		            		sparePartRequisitionItem.getClaimable(),
		            		sparePartRequisitionItem.getInspectionRemark(),
		            		sparePartRequisitionItem.getKeyPartNumber(),
		            		sparePartRequisitionItem.getId()); 
		        });
        	}
        }else{
        	s.set(0,"Approve");
        }
        if(kaiInspectionSheet.getLabourJobCharge()!=null){
        	if(kaiInspectionSheet.getWarrantyWcr().getWcrType().equals("PCR")){
	        	kaiInspectionSheet.getLabourJobCharge().forEach(labour -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimLabourApprovedPcr(
		        			labour.getClaimApprovedAmount(),
		        			labour.getClaimable(),
		        			labour.getInspectionRemark(),
		        			labour.getId());
				});
        	}else{
        		kaiInspectionSheet.getLabourJobCharge().forEach(labour -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimLabourApprovedGoodwill(
		        			labour.getClaimApprovedAmount(),
		        			labour.getClaimable(),
		        			labour.getInspectionRemark(),
		        			labour.getId());
				});
        	}
        }        
        if(kaiInspectionSheet.getOutsideJobCharge()!=null){
        	if(kaiInspectionSheet.getWarrantyWcr().getWcrType().equals("PCR")){
	        	kaiInspectionSheet.getOutsideJobCharge().forEach(outsidecharge -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimOutsideChargeApprovedPcr(
		        			outsidecharge.getClaimApprovedAmount(), 
		        			outsidecharge.getClaimable(),
		        			outsidecharge.getInspectionRemark(),
		        			outsidecharge.getId());
				});
        	}else{
        		kaiInspectionSheet.getOutsideJobCharge().forEach(outsidecharge -> {
		        	sparePartRequisitionItemRepo.updateWarrantyClaimOutsideChargeApprovedGoodwill(
		        			outsidecharge.getClaimApprovedAmount(), 
		        			outsidecharge.getClaimable(),
		        			outsidecharge.getInspectionRemark(),
		        			outsidecharge.getId());
				});
        	}
        }

        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                KaiInspectionSheetPhoto kaiInspectionSheetPhoto=new KaiInspectionSheetPhoto();
                String photo = m.getOriginalFilename();
                String photoName = "kai_inspection_sheet" + System.currentTimeMillis() + "_" + photo;
                storageService.store(m, photoName);
                kaiInspectionSheetPhoto.setFileName(photoName);
               kaiInspectionSheetPhoto.setKaiInspectionSheet(kaiInspectionSheet);
                kaiInspectionSheetPhotoRepo.save(kaiInspectionSheetPhoto);

            });
        }
        status = s.get(0);
        if(kaiInspectionSheet.getActionType()!=null && kaiInspectionSheet.getActionType().equals("hold")){
        	status = "hold";
        }
        System.out.println("Status KAI ----"+status);
        warrantyWcrApprovalRepo.approveWcr(kaiInspectionSheet.getWarrantyWcr().getId(), userAuthentication.getKubotaEmployeeId(), kaiInspectionSheet.getClaimFinalRemark(), userAuthentication.getUsername(), status);
        
        apiResponse.setMessage("KAI inspection sheet saved successfully with reference of warranty claim and delivery challan");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult("KAI inspection sheet saved successfully");
        return apiResponse;
    }
}
