package com.i4o.dms.kubota.service.mrc.service.ServiceMrcImpl;

import com.i4o.dms.kubota.accpac.domain.AccPacInvoicePartDetails;
import com.i4o.dms.kubota.accpac.repository.AccPacInvoiceMachineDetailRepository;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.mrc.domain.ServiceMrc;
import com.i4o.dms.kubota.service.mrc.domain.ServiceMrcPhotos;
import com.i4o.dms.kubota.service.mrc.repository.MrcRepository;
import com.i4o.dms.kubota.service.mrc.repository.ServiceMrcCheckpointRepo;
import com.i4o.dms.kubota.service.mrc.repository.ServiceMrcPhotoRepository;
import com.i4o.dms.kubota.service.mrc.service.ServiceMrcService;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class ServiceMrcImpl implements ServiceMrcService {

    private Logger logger = LoggerFactory.getLogger(ServiceMrcImpl.class);

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private MrcRepository mrcRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private ServiceMrcPhotoRepository serviceMrcPhotoRepository;

    @Autowired
    private ServiceMrcCheckpointRepo serviceMrcCheckpointRepo;

    @Autowired
    private AccPacInvoiceMachineDetailRepository invoiceMachineDetailRepository;

    @Transactional
    @Override
    public void saveServiceMrc(ServiceMrc serviceMrc, List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();

        if (serviceMrc.getDraftFlag())
        {
            Long serviceMrc1 = mrcRepository.
                    findByMachineMasterAndDealerId(userAuthentication.getDealerId(),
                            serviceMrc.getAccPacInvoicePartDetails().getId());
            if (serviceMrc1 != null)
            {
                apiResponse.setMessage("MRC Already Done for the selected Chassis No.");
                apiResponse.setStatus(HttpStatus.OK.value());
                //return apiResponse;
            }
        }
        serviceMrc.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
       // serviceMrc.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
       // serviceMrc.setMrcDate(new Date());
        ServiceMrc serviceMrc1 = mrcRepository.save(serviceMrc);

        if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                ServiceMrcPhotos serviceMrcPhoto = new ServiceMrcPhotos();
                String mrcPhoto = m.getOriginalFilename();
                String photoName = "service_mrc" + System.currentTimeMillis() + "_" + mrcPhoto;
                storageService.store(m, photoName);
                serviceMrcPhoto.setFileName(photoName);
                serviceMrcPhoto.setServiceMrcId(serviceMrc1);
                serviceMrcPhotoRepository.save(serviceMrcPhoto);
            });

        }

        if (!serviceMrc.getDraftFlag()) {
            AccPacInvoicePartDetails accPacInvoicePartDetails = invoiceMachineDetailRepository
                    .getOne(serviceMrc.getAccPacInvoicePartDetails().getId());
            accPacInvoicePartDetails.setMrcDoneFlag(true);
            invoiceMachineDetailRepository.save(accPacInvoicePartDetails);
        }

        serviceMrc.getServiceMrcChassisCheckpointInfSet().forEach(s ->{
            s.getServiceMrcChassisCheckpointId().setServiceMrc(serviceMrc1);
            serviceMrcCheckpointRepo.save(s);
        });
        //return apiResponse;
    }
}

