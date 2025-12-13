package com.i4o.dms.kubota.warranty.logsheet.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheet;
import com.i4o.dms.kubota.warranty.logsheet.domain.WarrantyLogsheetPhotos;
import com.i4o.dms.kubota.warranty.logsheet.repository.WarrantyLogsheetPhotoRepo;
import com.i4o.dms.kubota.warranty.logsheet.repository.WarrantyLogsheetRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class WarrantyLogsheetImpl implements WarrantyLogsheetService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private WarrantyLogsheetRepo warrantyLogsheetRepo;

    @Autowired
    private WarrantyLogsheetPhotoRepo warrantyLogsheetPhotoRepo;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Override
    @Transactional
    public ApiResponse saveWarrantyLogsheet(WarrantyLogsheet warrantyLogsheet,List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse=new ApiResponse();
        if(warrantyLogsheet.getDraftFlag()){
        	warrantyLogsheet.setLogsheetNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis());
        }
        warrantyLogsheet.setStatus(warrantyLogsheet.getDraftFlag()?"Draft":"Submitted");
        warrantyLogsheet.setCreatedBy(userAuthentication.getLoginId());
        if(warrantyLogsheet.getId()!=null){
        	warrantyLogsheet.setLastModifiedBy(userAuthentication.getLoginId());
        	warrantyLogsheet.setLastModifiedDate(new Date());
        }
        WarrantyLogsheet warrantyLogsheet1=warrantyLogsheetRepo.save(warrantyLogsheet);
        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                WarrantyLogsheetPhotos warrantyLogsheetPhotos=new WarrantyLogsheetPhotos();
                String mrcPhoto = m.getOriginalFilename();
                String photoName = "warranty_logsheet" + System.currentTimeMillis() + "_" + mrcPhoto;
                storageService.store(m, photoName);
                warrantyLogsheetPhotos.setFileName(photoName);
                warrantyLogsheetPhotos.setWarrantyLogsheet(warrantyLogsheet1);
                warrantyLogsheetPhotoRepo.save(warrantyLogsheetPhotos);

            });
        }

        return apiResponse;
    }
}
