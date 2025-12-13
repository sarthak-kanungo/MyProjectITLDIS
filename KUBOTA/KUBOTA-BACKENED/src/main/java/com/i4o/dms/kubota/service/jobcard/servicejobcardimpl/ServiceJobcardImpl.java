package com.i4o.dms.kubota.service.jobcard.servicejobcardimpl;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.StoreMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobcardPhotos;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobcardRetroMapping;
import com.i4o.dms.kubota.service.jobcard.repository.ServiceJobCardRepo;
import com.i4o.dms.kubota.service.jobcard.repository.ServiceJobcardPhotosRepo;
import com.i4o.dms.kubota.service.mrc.domain.ServiceMrc;
import com.i4o.dms.kubota.service.mrc.domain.ServiceMrcPhotos;
import com.i4o.dms.kubota.service.psc.domain.ServicePsc;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartRequisition;
import com.i4o.dms.kubota.spares.partrequisition.repository.SparePartRequisitionItemRepo;
import com.i4o.dms.kubota.spares.partrequisition.repository.SparePartRequisitionRepo;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
public class ServiceJobcardImpl implements ServiceJobcardService {


    @Autowired
    private ServiceJobCardRepo serviceJobCardRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private SparePartRequisitionRepo sparePartRequisitionRepo;


    @Autowired
    private SparePartRequisitionItemRepo sparePartRequisitionItemRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ServiceJobcardPhotosRepo serviceJobcardPhotosRepo;


    @Transactional
    @Override
    public ApiResponse saveJobCard(ServiceJobCard serviceJobCard, MultipartFile fsCouponPage1, 
    		MultipartFile fsCouponPage2, MultipartFile hourMeterPhoto, MultipartFile chassisPhoto,
    		MultipartFile signedJobcard, MultipartFile retroAcknowledgementForm) 
    {
        ApiResponse apiResponse = new ApiResponse();
       if(!serviceJobCardRepo.isJobCardCreated(0L)) {

           serviceJobCard.setBranchId(userAuthentication.getBranchId());
           serviceJobCard.setCreatedBy(userAuthentication.getLoginId());
           if (serviceJobCard.getJobCardDate() == null) {
               serviceJobCard.setJobCardDate(new Date());
           }
           
           //Suraj-24-04-2023-START
           List<ServiceJobcardRetroMapping> jobcardRetros = new ArrayList<>();
           if(serviceJobCard.getJobcardRetroMappings() != null) {
        	   for(ServiceJobcardRetroMapping item: serviceJobCard.getJobcardRetroMappings()) {
        		   ServiceJobcardRetroMapping jobCardRetro = item;
        		   jobCardRetro.setCreatedBy(userAuthentication.getLoginId());
        		   jobCardRetro.setMachineInventory(serviceJobCard.getMachineInventory());
        		   jobcardRetros.add(jobCardRetro);
        	   }
           }
           serviceJobCard.setJobcardRetroMappings(jobcardRetros);
           //Suraj-24-04-2023-END

           List<ServiceJobcardPhotos> photoList = new ArrayList<>();
           if (fsCouponPage1 != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = fsCouponPage1.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(fsCouponPage1, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("free service coupon 1");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           if (fsCouponPage2 != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = fsCouponPage2.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(fsCouponPage2, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("free service coupon 2");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           if (hourMeterPhoto != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = hourMeterPhoto.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(hourMeterPhoto, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("hour meter ");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           if (chassisPhoto != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = chassisPhoto.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(chassisPhoto, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("chassis");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           
           //Suraj-24-04-2023-START
           if (signedJobcard != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = signedJobcard.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(signedJobcard, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("Signed Job Card");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           if (retroAcknowledgementForm != null) {
               ServiceJobcardPhotos serviceJobcardPhotos = new ServiceJobcardPhotos();
               String jobcardPhoto = retroAcknowledgementForm.getOriginalFilename();
               String photoName = "JC" + System.currentTimeMillis() + "_" + jobcardPhoto;
               storageService.store(retroAcknowledgementForm, photoName);
               serviceJobcardPhotos.setFileName(photoName);
               serviceJobcardPhotos.setFileType("Retrofitment Acknowledgement Form");
               serviceJobcardPhotos.setServiceJobCard(serviceJobCard);
               photoList.add(serviceJobcardPhotos);
           }
           //Suraj-24-04-2023-END
           
           serviceJobCard.setServiceJobcardPhotos(photoList);
           if (serviceJobCard.getDraftFlag()) {
               serviceJobCard.setStatus("Draft");
               serviceJobCardRepo.save(serviceJobCard);
               apiResponse.setStatus(HttpStatus.OK.value());
               apiResponse.setMessage("job card save successfully  ");
               return apiResponse;
           }
           serviceJobCard.setStatus("Open");
           if(serviceJobCard.getJobCardDate()==null)serviceJobCard.setJobCardDate(new Date());
           ServiceJobCard serviceJobCard1 = serviceJobCardRepo.save(serviceJobCard);
           
           //Creating Spare Part Requisition
           serviceJobCardRepo.createSparePartRequistion(serviceJobCard1.getId());

           apiResponse.setMessage("Job card submitted successfully ");
           apiResponse.setStatus(HttpStatus.OK.value());
       }else{
           apiResponse.setMessage("job card already created  ");
           apiResponse.setStatus(HttpStatus.OK.value());
       }

        return apiResponse;
    }



//    private SparePartRequisition saveRequisition(ServiceJobCard serviceJobCard) {
//        SparePartRequisition sparePartRequisition = new SparePartRequisition();
//        sparePartRequisition.setRequisitionPurpose(serviceJobCard.getPlaceOfService()
//                .equalsIgnoreCase("Dealer Workshop") ? "Workshop" : "Field");
////        sparePartRequisition.setServiceJobCard(serviceJobCard);
//        sparePartRequisition.setRequisitionDate(new Date());
//        sparePartRequisition.setSparePartRequisitionItem(serviceJobCard.getSparePartRequisitionItem());
//        sparePartRequisition.setPartRequisitionStatus("Submitted");
//        sparePartRequisition.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
//        sparePartRequisition.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
//        return sparePartRequisitionRepo.save(sparePartRequisition);
//    }

//    private Boolean checkRequisition(ServiceJobCard serviceJobCard) {
//        AtomicReference<Boolean> recFlag = new AtomicReference<>(false);
//        serviceJobCard.getSparePartRequisitionItem().forEach(sparePartRequisitionItem -> {
//            if (sparePartRequisitionItem.getCategory().equalsIgnoreCase("FOC") ||
//                    sparePartRequisitionItem.getCategory().equalsIgnoreCase("Warranty")){
//                recFlag.set(true);
//            }
//
//        });
//        return recFlag.get();
//    }


}

