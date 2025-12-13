package com.i4o.dms.kubota.service.machinereinstallation.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.machinereinstallation.domain.ServiceMachineReinstallation;
import com.i4o.dms.kubota.service.machinereinstallation.repository.ServiceMachineReinstallationRepository;
import com.i4o.dms.kubota.service.machinereinstallation.repository.ServiceRiCheckpointRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@Service
public class ServiceRiImpl {


    @Autowired
    private ServiceMachineReinstallationRepository serviceMachineReinstallationRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private ServiceRiCheckpointRepo serviceRiCheckpointRepo;

    @Transactional
    public ApiResponse saveRi(ServiceMachineReinstallation serviceMachineReinstallation) {
        ApiResponse apiResponse = new ApiResponse();
        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
        serviceMachineReinstallation.setDealerEmployee(dealerEmployeeMaster);
        serviceMachineReinstallation.setBranchId(userAuthentication.getBranchId());
        serviceMachineReinstallation.setCreatedBy(userAuthentication.getLoginId());
        serviceMachineReinstallation.setCreatedOn(new Date());
        serviceMachineReinstallation.setReInstallationDate(new Date());
        
        ServiceMachineReinstallation serviceMachineReinstallation1 = serviceMachineReinstallationRepository.save(serviceMachineReinstallation);

            serviceMachineReinstallation.getServiceRiChassisCheckpointInfo()
                    .forEach(s -> {
                                s.getRiChassisCheckpointId().setServiceMachineReinstallation(serviceMachineReinstallation1);
                                serviceRiCheckpointRepo.save(s);
                            }
                    );

        apiResponse.setMessage(serviceMachineReinstallation.getDraftFlag() ? "Reinstallation saved successfully." : "Reinstallation submitted successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());

        return apiResponse;
   }
}
