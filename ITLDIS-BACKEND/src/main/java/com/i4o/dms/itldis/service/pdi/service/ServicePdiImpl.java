package com.i4o.dms.itldis.service.pdi.service;


import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.pdi.domain.ServicePdi;
import com.i4o.dms.itldis.service.pdi.repository.PdiRepository;
import com.i4o.dms.itldis.service.pdi.repository.ServiceCheckpointRepo;
import com.i4o.dms.itldis.utils.ApiResponse;

@Service
public class ServicePdiImpl {

    @Autowired
    private PdiRepository pdiRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private ServiceCheckpointRepo serviceCheckpointRepo;

    @Transactional
    public ApiResponse savePdi(ServicePdi servicePdi) {
        ApiResponse apiResponse = new ApiResponse();
        if (servicePdi.getDraftFlag()) {
            Long servicePdi1 = pdiRepository.
                    findByMachineMasterAndDealerId(userAuthentication.getDealerId(),
                            servicePdi.getMachineInventory().getId());
            if (servicePdi1 != null) {
                apiResponse.setMessage("PDI Already Done for the selected Chassis No.");
                apiResponse.setStatus(HttpStatus.OK.value());
                return apiResponse;
            }
        }

        servicePdi.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        servicePdi.setPdiDate(new Date());
        servicePdi.setCreatedBy(userAuthentication.getLoginId());
        servicePdi.setCreatedDate(new Date());
        if(servicePdi.getId()!=null){
        	servicePdi.setModifiedBy(userAuthentication.getLoginId());
        	servicePdi.setModifiedDate(new Date());
        }
        ServicePdi servicePdi1 = pdiRepository.save(servicePdi);

        servicePdi.getServicePdiChassisCheckpointInfoSet()
                .forEach(s -> {
                            s.getChassisCheckpointId().setServicePdi(servicePdi1);
                            serviceCheckpointRepo.save(s);
                        }
                );
        apiResponse.setMessage(servicePdi.getDraftFlag() ? "PDI saved successfully." :"PDI submitted successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return apiResponse;
    }
}
