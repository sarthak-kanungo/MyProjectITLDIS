package com.i4o.dms.kubota.service.psc.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerEmployeeMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.psc.domain.ServicePsc;
import com.i4o.dms.kubota.service.psc.repository.PscRepository;
import com.i4o.dms.kubota.service.psc.repository.PscServiceCheckpointRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@Service

public class ServicePscImpl {

    @Autowired
    private PscRepository pscRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private PscServiceCheckpointRepo serviceCheckpointRepo;

    @Transactional
    public ApiResponse savePsc(ServicePsc servicePsc){
    	Long branchId = userAuthentication.getBranchId();
        ApiResponse apiResponse = new ApiResponse();
        if (servicePsc.getDraftFlag()) {
            Long servicePsc1 = pscRepository.
                    findByMachineMasterAndDealerId(branchId,
                            servicePsc.getMachineInventory().getId());
            if (servicePsc1 != null) {
                apiResponse.setMessage("PSC Already Done for the selected Chassis No.");
                apiResponse.setStatus(HttpStatus.OK.value());
                return apiResponse;
            }
        }
        servicePsc.setBranchId(branchId);
        servicePsc.setCreatedBy(userAuthentication.getLoginId());
        servicePsc.setCreatedDate(new Date());
        servicePsc.setPscDate(new Date());
        ServicePsc servicePsc1=pscRepository.save(servicePsc);

        servicePsc.getServicePscChassisCheckpointInfo()
                .forEach(s-> {
                    s.getChassisCheckpointId().setServicePsc(servicePsc1);
                    serviceCheckpointRepo.save(s); }
                );
        apiResponse.setMessage(servicePsc.getDraftFlag() ? "PSC saved successfully." : "PSC submitted successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return apiResponse;
    }

}
//    @Transactional
//    public ApiResponse savePdi(ServicePdi servicePdi){
//        ApiResponse apiResponse = new ApiResponse();
//        if (servicePdi.getDraftFlag()) {
//            Long servicePdi1 = pdiRepository.
//                    findByMachineMasterAndDealerId(userAuthentication.getDealerId(),
//                            servicePdi.getMachineInventory().getId());
//            if (servicePdi1 != null) {
//                apiResponse.setMessage("PDI Already Done for the selected Chassis No.");
//                apiResponse.setStatus(HttpStatus.OK.value());
//                return apiResponse;
//            }
//        }
//        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
//        servicePdi.setDealerEmployeeMaster(dealerEmployeeMaster);
//        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
//        servicePdi.setDealerMaster(dealerMaster);
//        ServicePdi servicePdi1=pdiRepository.save(servicePdi);
//
//        servicePdi.getServicePdiChassisCheckpointInfoSet()
//                .forEach(s-> {
//                    s.getChassisCheckpointId().setServicePdi(servicePdi1);
//                    serviceCheckpointRepo.save(s); }
//                );
//        apiResponse.setMessage(servicePdi.getDraftFlag() ? "PDI saved successfully." : "PDI submitted successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        return apiResponse;
//    }
//}