package com.i4o.dms.kubota.service.machinereinstallation.controller;


import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.machineinstallation.dto.DiSearchDto;
import com.i4o.dms.kubota.service.machineinstallation.dto.DiViewDto;
import com.i4o.dms.kubota.service.machinereinstallation.domain.ServiceMachineReinstallation;
import com.i4o.dms.kubota.service.machinereinstallation.dto.RiSearchDto;
import com.i4o.dms.kubota.service.machinereinstallation.dto.RiSearchResponse;
import com.i4o.dms.kubota.service.machinereinstallation.dto.RiViewDto;
import com.i4o.dms.kubota.service.machinereinstallation.repository.ServiceMachineReinstallationRepository;
import com.i4o.dms.kubota.service.machinereinstallation.service.ServiceRiImpl;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/reInstallation")

public class ServiceReinstallationController {

    @Autowired
    private ServiceRiImpl serviceRiImpl;

    @Autowired
    private ServiceMachineReinstallationRepository serviceMachineReinstallationRepo;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @PostMapping
    public ResponseEntity<?> addReInstallation(@Valid @RequestBody ServiceMachineReinstallation serviceMachineReinstallation) {
        ApiResponse apiResponse = serviceRiImpl.saveRi(serviceMachineReinstallation);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchRi")
    public ResponseEntity<?> searchRi(@RequestBody RiSearchDto riSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        
        List<RiSearchResponse> result = serviceMachineReinstallationRepo.searchRi(riSearchDto.getFromDate(),
                riSearchDto.getToDate(),riSearchDto.getSeries(),
                riSearchDto.getServiceStaffName(),riSearchDto.getReInstallationNo(),riSearchDto.getDealerId(),
                riSearchDto.getDealerEmployeeId(),riSearchDto.getKubotaEmployeeId(),riSearchDto.getManagementAccess(),
                riSearchDto.getPage(),riSearchDto.getSize(),userAuthentication.getUsername(), 'N', 0l);
        
        apiResponse.setMessage("ReInstallation Search List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0)
        	count = result.get(0).getRecordCount();
        apiResponse.setCount(count);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/reInstallationNumberAuto")
    public ResponseEntity<?> reInstallationNumberAuto(@RequestParam("reInstallationNumber") String reInstallationNumber)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> number = serviceMachineReinstallationRepo.FindByReInstallationNumberContaining(reInstallationNumber,userAuthentication.getUsername());
        apiResponse.setMessage("Reinstallation Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(number);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getDetailsByChassisNo")
    public ResponseEntity<?> getDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> number = serviceMachineReinstallationRepo.getDetailsByChassisNo(chassisNo, userAuthentication.getUsername());
        apiResponse.setMessage("Details get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(number);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/chassisNoAuto")
    public ResponseEntity<?> chassisNoAuto(@RequestParam("chassisNo") String chassisNo,
                                           @RequestParam("seriesId") Long seriesId,
                                           @RequestParam(required = false) String chassisId
    )
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String, Object>> no=serviceMachineReinstallationRepo.findByChassisNoContaining(userAuthentication.getBranchId(),
        		chassisNo,seriesId,chassisId);
        apiResponse.setMessage("chassis no get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return  ResponseEntity.ok(apiResponse);

    }
//    Set<Department> newList = list
//            .stream()
//            .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Department::getId))));


    @GetMapping(value = "/getRiById/{id}")
    public ResponseEntity<?> getRiById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("ReInstallation Details");
        apiResponse.setStatus(HttpStatus.OK.value());

        RiViewDto riViewDto = new RiViewDto();
        riViewDto.setRiViewHeaderData(serviceMachineReinstallationRepo.riViewGetHeaderData(id));

        List<Map<String, Object>> checkpointListByRiId =
                serviceMachineReinstallationRepo.riViewGetCheckpointListByDiId(id);

        List<Map<String, Object>> machineDetailsListByDiId =
                serviceMachineReinstallationRepo.riViewGetMachineDetailsListByDiId(id);

        List<Map<String, Object>> representativeListByDiId =
                serviceMachineReinstallationRepo.riViewGetRepresentativeListByDiId(id);

        riViewDto.setReInstallationCheckpointList(checkpointListByRiId);
        riViewDto.setReInstallationMachineDetailsList(machineDetailsListByDiId);
        riViewDto.setReInstallationRepresentativeDetailsList(representativeListByDiId);

        apiResponse.setResult(riViewDto);
        return ResponseEntity.ok(apiResponse);
    }
}


