package com.i4o.dms.itldis.warranty.retrofitmentcampaign.service;


import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.itldis.utils.excelmanager.util.ExcelImportManager;

import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaign;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaignChassisId;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaignPhoto;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentChassisInfo;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.dto.ChassisDetails;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.repository.WarrantyRetrofitmentCampaignPhotoRepo;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.repository.WarrantyRetrofitmentCampaignRepo;
import com.i4o.dms.itldis.warranty.retrofitmentcampaign.repository.WarrantyRetrofitmentChassisInfoRepo;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.HashSet;

@Service
@Slf4j
public class WarrantyRetrofitmentCampaignImpl implements WarrantyRetrofitmentCampaignService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
    @Autowired
    private ExcelImportManager excelImportManager;

    @Autowired
    private StorageService storageService;

    @Autowired
    private WarrantyRetrofitmentChassisInfoRepo warrantyRetrofitmentChassisInfoRepo;

    @Autowired
    private WarrantyRetrofitmentCampaignPhotoRepo warrantyRetrofitmentCampaignPhotoRepo;

    @Autowired
    private WarrantyRetrofitmentCampaignRepo warrantyRetrofitmentCampaignRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Transactional
    @Override
	public ApiResponse saveRetrofitmentCampaign(WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign,
			MultipartFile multipartFile, List<MultipartFile> multipartFileList)
			throws InvalidColumnException, IOException 
    {
        ApiResponse apiResponse=new ApiResponse();
//        warrantyRetrofitmentCampaign.setKubotaEmployeeMaster(kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId()));
//        warrantyRetrofitmentCampaign.setLastModifiedBy(kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId()));
        
        if(warrantyRetrofitmentCampaign.getId() == null) {
			warrantyRetrofitmentCampaign.setCreatedBy(userAuthentication.getLoginId());
        	warrantyRetrofitmentCampaign.setCreatedDate(new Date());
        	warrantyRetrofitmentCampaign.setRetrofitmentDate(new Date());
        }else {
        	warrantyRetrofitmentCampaign.setLastModifiedBy(userAuthentication.getLoginId());
        	warrantyRetrofitmentCampaign.setLastModifiedDate(new Date());
        }
        
        if(multipartFile.isEmpty()) {
        	NoSuchElementException ex = new NoSuchElementException("\"Excel file can't be null!\"");
        	logger.info("Save Retrofitment Campaign Exception: " + ex.getMessage());
        	ex.printStackTrace();
        	throw ex;
        }
        
        String dataFilePath = multipartFile.getOriginalFilename();
        String excelFileName = "warranty_retrofitment_campaign" + System.currentTimeMillis() + "_" + dataFilePath;
        storageService.store(multipartFile, excelFileName);
        warrantyRetrofitmentCampaign.setDataFilePath(excelFileName);
        
		WarrantyRetrofitmentCampaign waRetrofitmentCampaign = warrantyRetrofitmentCampaignRepo
				.save(warrantyRetrofitmentCampaign);

        InputStream in = multipartFile.getInputStream();
        excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                                multipartFile.getInputStream()
                        )
                )
        );
        List<ChassisDetails> chassisDetailList = Poiji.fromExcel(
                in,
                PoijiExcelType.XLS,
                ChassisDetails.class,
                PoijiOptions.PoijiOptionsBuilder
                        .settings()
                        .headerStart(0)
                        .build()
        );
        
        if(chassisDetailList.isEmpty()) {
        	NoSuchElementException ex = new NoSuchElementException("\"The Excel should not be blank!\"");
        	logger.info("Saving Retrofitment Campaign Exception: " + ex.getMessage());
        	ex.printStackTrace();
        	throw ex;
        }
        
        HashSet<ChassisDetails> chassisSet = new HashSet<>(chassisDetailList);	//It ensured uniqueness as I have imlemented hacode and equals method in ChassisDetails Class.
        chassisSet.forEach(chassisDetail -> {
            //MachineInventory machineInventory=null;//machineInventoryRepository.findByChassisNo(chassisDetails1.getChassisNo());
            if(chassisDetail.getChassisNo() != null) {
                WarrantyRetrofitmentChassisInfo warrantyRetrofitmentChassisInfo1 = new WarrantyRetrofitmentChassisInfo();
                WarrantyRetrofitmentCampaignChassisId campaignChassisId = new WarrantyRetrofitmentCampaignChassisId();
                
                campaignChassisId.setChassisNo(chassisDetail.getChassisNo());
                campaignChassisId.setWarrantyRetroFitmentCampaign(waRetrofitmentCampaign);
                
                warrantyRetrofitmentChassisInfo1.setWarrantyRetrofitmentCampaignChassisId(campaignChassisId);
                warrantyRetrofitmentChassisInfoRepo.save(warrantyRetrofitmentChassisInfo1);
            }
        });
        
        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                WarrantyRetrofitmentCampaignPhoto warrantyRetrofitmentCampaignPhoto=new WarrantyRetrofitmentCampaignPhoto();
                
                String retrofitmentPhoto = m.getOriginalFilename();
                String photoName = "warranty_retrofitment_campaign" + System.currentTimeMillis() + "_" + retrofitmentPhoto;
                
                storageService.store(m, photoName);
                
                warrantyRetrofitmentCampaignPhoto.setFileName(photoName);
                warrantyRetrofitmentCampaignPhoto.setFileType("retro fitment campaign photo");
                warrantyRetrofitmentCampaignPhoto.setWarrantyRetrofitmentCampaign(waRetrofitmentCampaign);
                
                warrantyRetrofitmentCampaignPhotoRepo.save(warrantyRetrofitmentCampaignPhoto);
            });
        }
        
        apiResponse.setMessage("Warranty retro fitment submitted successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult("Submitted");
        return apiResponse;
    }    

    public void saveExcelChassisDetail(MultipartFile multipartFile) throws  Exception{

        String dataFilePath = multipartFile.getOriginalFilename();
        String excelFileName = "warranty_retrofitment_campaign" + System.currentTimeMillis() + "_" + dataFilePath;
        storageService.store(multipartFile, excelFileName);
        
        InputStream in = multipartFile.getInputStream();
        excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                                multipartFile.getInputStream()
                        )
                )
        );
        List<ChassisDetails> chassisDetails = Poiji.fromExcel(
                in,
                PoijiExcelType.XLS,
                ChassisDetails.class,
                PoijiOptions.PoijiOptionsBuilder
                        .settings()
                        .headerStart(0)
                        .build()
        );
        
        HashSet<ChassisDetails> chassisSet = new HashSet<>(chassisDetails);
        chassisSet.forEach(chassisDetails1 -> {
            MachineInventory machineInventory=null;//machineInventoryRepository.findByChassisNo(chassisDetails1.getChassisNo());
            if(machineInventory!=null) {
                WarrantyRetrofitmentChassisInfo warrantyRetrofitmentChassisInfo1 = new WarrantyRetrofitmentChassisInfo();
                WarrantyRetrofitmentCampaignChassisId campaignChassisId=new WarrantyRetrofitmentCampaignChassisId();
                //campaignChassisId.setMachineInventory(machineInventory);
                campaignChassisId.setChassisNo(chassisDetails1.getChassisNo());
                //log.info(""+machineInventory.getChassisNo()+""+machineInventory.getId());
                campaignChassisId.setWarrantyRetroFitmentCampaign(warrantyRetrofitmentCampaignRepo.getOne(53L));
                warrantyRetrofitmentChassisInfo1.setWarrantyRetrofitmentCampaignChassisId(campaignChassisId);
                warrantyRetrofitmentChassisInfoRepo.save(warrantyRetrofitmentChassisInfo1);
            }
        });
    }


}
