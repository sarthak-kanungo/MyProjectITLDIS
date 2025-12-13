package com.i4o.dms.kubota.service.machineinstallation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.machineinstallation.ServiceInstallationService;
import com.i4o.dms.kubota.service.machineinstallation.domain.ServiceInstallationPhotos;
import com.i4o.dms.kubota.service.machineinstallation.domain.ServiceMachineInstallation;
import com.i4o.dms.kubota.service.machineinstallation.repository.ServiceDiCheckpointRepo;
import com.i4o.dms.kubota.service.machineinstallation.repository.ServiceFiCheckpointRepo;
import com.i4o.dms.kubota.service.machineinstallation.repository.ServiceMachineInstallationPhotosRepo;
import com.i4o.dms.kubota.service.machineinstallation.repository.ServiceMachineInstallationRepository;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;

@Service
public class ServiceDiImpl implements ServiceInstallationService {

/*
    public class ServiceDiImpl  {
*/

    @Autowired
    private ServiceMachineInstallationRepository deliveryInstallationRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private ServiceDiCheckpointRepo serviceDiCheckpointRepo;
    @Autowired
    private ServiceFiCheckpointRepo serviceFiCheckpointRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ServiceMachineInstallationPhotosRepo serviceMachineInstallationPhotosRepo;

/*    @Transactional
    public ApiResponse saveDi(ServiceMachineInstallation serviceMachineInstallation) {
        ApiResponse apiResponse = new ApiResponse();

        if (serviceMachineInstallation.getDraftFlag()) {
            Long serviceDi1 = deliveryInstallationRepository.
                    findByMachineMasterAndDealerId(userAuthentication.getDealerId(),
                            serviceMachineInstallation.getMachineInventory().getId());
            if (serviceDi1 != null) {
                apiResponse.setMessage("Installation Already Done for the selected Chassis No.");
                apiResponse.setStatus(HttpStatus.OK.value());
                return apiResponse;
            }
        }
        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
        serviceMachineInstallation.setDealerEmployeeMaster(dealerEmployeeMaster);
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        serviceMachineInstallation.setDealerMaster(dealerMaster);
        ServiceMachineInstallation serviceMachineInstallation1 = deliveryInstallationRepository.save(serviceMachineInstallation);
        if (serviceMachineInstallation.getServiceDiChassisCheckpointInfo() != null) {
            serviceMachineInstallation.getServiceDiChassisCheckpointInfo()
                    .forEach(s -> {
                                s.getDiChassisCheckpointInfo().setServiceMachineInstallation(serviceMachineInstallation1);
                                serviceDiCheckpointRepo.save(s);
                            }
                    );
        } else {
            serviceMachineInstallation.getServiceFiChassisCheckpointInfo()
                    .forEach(s -> {
                                s.getFiChassisCheckpointInfo().setServiceMachineInstallation(serviceMachineInstallation1);
                                serviceFiCheckpointRepo.save(s);
                            }
                    );
        }

        apiResponse.setMessage(serviceMachineInstallation.getDraftFlag() ? "Installation saved successfully." : "Installation submitted successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());

        return apiResponse;
    }
}*/

    //photo
    @Transactional
    @Override
    public void saveServiceMachineInstallation(ServiceMachineInstallation serviceMachineInstallation, List<MultipartFile> multipartFileList) {

        ApiResponse apiResponse = new ApiResponse();

        if (serviceMachineInstallation.getDraftFlag()) {
            Long serviceDi1 = deliveryInstallationRepository.
                    findByMachineMasterAndDealerId(userAuthentication.getDealerId(),
                            serviceMachineInstallation.getMachineInventory().getId());
            if (serviceDi1 != null) {
                apiResponse.setMessage("Installation Already Done for the selected Chassis No.");
                apiResponse.setStatus(HttpStatus.OK.value());
            }
        }
        serviceMachineInstallation.setBranchId(userAuthentication.getBranchId());
        serviceMachineInstallation.setInstallationDate(new Date());
        serviceMachineInstallation.setCreatedOn(new Date());
        serviceMachineInstallation.setCreatedBy(userAuthentication.getLoginId());
        
        ServiceMachineInstallation serviceMachineInstallation1 = deliveryInstallationRepository.save(serviceMachineInstallation);

        if (serviceMachineInstallation.getServiceDiChassisCheckpointInfo() != null) {
            serviceMachineInstallation.getServiceDiChassisCheckpointInfo()
                    .forEach(s -> {
                                s.getDiChassisCheckpointInfo().setServiceMachineInstallation(serviceMachineInstallation1);
                                serviceDiCheckpointRepo.save(s);
                            }
                    );
        } else {
            serviceMachineInstallation.getServiceFiChassisCheckpointInfo()
                    .forEach(s -> {
                                s.getFiChassisCheckpointInfo().setServiceMachineInstallation(serviceMachineInstallation1);
                                serviceFiCheckpointRepo.save(s);
                            }
                    );
        }

        if (multipartFileList.size() <= 3 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                ServiceInstallationPhotos serviceInstallationPhotos = new ServiceInstallationPhotos();
                String installationPhoto = m.getOriginalFilename();
                String photoName = "service_machine_installation" + System.currentTimeMillis() + "_" + installationPhoto;
                storageService.store(m, photoName);
                serviceInstallationPhotos.setFileName(photoName);
                serviceInstallationPhotos.setServiceMachineInstallationId(serviceMachineInstallation1);
                serviceMachineInstallationPhotosRepo.save(serviceInstallationPhotos);
            });

        }
        apiResponse.setMessage(serviceMachineInstallation.getDraftFlag() ? "Installation saved successfully." : "Installation submitted successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
//        return apiResponse;
    }
}

