package com.i4o.dms.itldis.service.machineinstallation.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.salesandpresales.grn.repository.MachineInventoryRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.machineinstallation.ServiceInstallationService;
import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceMachineInstallation;
import com.i4o.dms.itldis.service.machineinstallation.dto.DiSearchDto;
import com.i4o.dms.itldis.service.machineinstallation.dto.DiSearchResponse;
import com.i4o.dms.itldis.service.machineinstallation.dto.DiViewDto;
import com.i4o.dms.itldis.service.machineinstallation.repository.ServiceMachineInstallationRepository;
import com.i4o.dms.itldis.service.machineinstallation.service.ServiceDiImpl;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/deliveryInstallation")
public class ServiceDeliveryInstallationController {

    @Autowired
    private ServiceMachineInstallationRepository deliveryInstallationRepository;

    @Autowired
    private MachineInventoryRepository machineInventoryRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private ServiceDiImpl serviceDiImpl;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    //photo upload
    @Autowired
    private ServiceInstallationService serviceInstallationService;

/*    @PostMapping
    public ResponseEntity<?> addDeliveryInstallation(@Valid @RequestBody ServiceMachineInstallation serviceMachineInstallation) {
            ApiResponse apiResponse = serviceDiImpl.saveDi(serviceMachineInstallation);
            return ResponseEntity.ok(apiResponse);
    }*/

    //photo upload
    @PostMapping(value = "/addDeliveryInstallation", consumes = {"multipart/form-data"})
    public ResponseEntity<?> addDeliveryInstallation(@RequestPart(value = "serviceMachineInstallation") ServiceMachineInstallation serviceMachineInstallation,
                                                     @RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList) {

        serviceInstallationService.saveServiceMachineInstallation(serviceMachineInstallation, multipartFileList);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Machine Installation save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/chassisNoAuto")
    public ResponseEntity<?> chassisNoAuto(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String, Object>> no=deliveryInstallationRepository.findByChassisNoContaining(userAuthentication.getBranchId(),chassisNo);
        apiResponse.setMessage("chassis no get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return  ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getDetailsByChassisNo")
    public ResponseEntity<?> getDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        if(deliveryInstallationRepository.checkChassisNumberInDraft(chassisNo,userAuthentication.getBranchId()).containsKey("isDraftFlag")==Boolean.TRUE) {
            apiResponse.setMessage("Chassis Number is already in draft mode");
            apiResponse.setStatus(HttpStatus.OK.value());
        } else{
            Map<String, Object> chassisNoDetails = deliveryInstallationRepository.getDetailsByChassisNo(chassisNo,userAuthentication.getBranchId());
            apiResponse.setMessage("Details get successfully");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(chassisNoDetails);
        }

        return ResponseEntity.ok(apiResponse);
    }

   @GetMapping("/representativeTypeDropdown")
    public ResponseEntity<?> representativeTypeDropdown()
   {
       ApiResponse apiResponse = new ApiResponse();
       List<Map<String, Object>> installation = deliveryInstallationRepository.getRepresentativeTypeDropdown();
       apiResponse.setMessage("representative Type get Successfully");
       apiResponse.setStatus(HttpStatus.OK.value());
       apiResponse.setResult(installation);
       return ResponseEntity.ok(apiResponse);
   }

    @GetMapping("/installationNumberAuto")
    public ResponseEntity<?> installationNumberAuto(@RequestParam("installationNumber") String installationNumber)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> number = deliveryInstallationRepository.FindByInstallationNumberContaining(installationNumber,userAuthentication.getUsername());
        apiResponse.setMessage("installation Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(number);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/serviceStaffNameAuto")
    public ResponseEntity<?> serviceStaffNameAuto(@RequestParam("serviceStaffName") String serviceStaffName)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> number = deliveryInstallationRepository.FindByServiceStaffNameContaining(serviceStaffName,userAuthentication.getDealerId());
        apiResponse.setMessage("Service Staff Name get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(number);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchDi")
    public ResponseEntity<?> searchDi(@RequestBody DiSearchDto diSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        
        List<DiSearchResponse> result = deliveryInstallationRepository.searchDi(diSearchDto.getChassisNo(),
                diSearchDto.getInstallationNo(),diSearchDto.getInstallationType(),
                diSearchDto.getFromDate(),diSearchDto.getToDate(), diSearchDto.getDealerId() ,
                userAuthentication.getDealerEmployeeId(), userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(), 
                diSearchDto.getPage(),diSearchDto.getSize(),userAuthentication.getUsername(), 'N', 0l);
        
        apiResponse.setMessage("Installation Search List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDiById/{id}")
    public ResponseEntity<?> getDiById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Installation Details");
        apiResponse.setStatus(HttpStatus.OK.value());

        DiViewDto diViewDto = new DiViewDto();
        diViewDto.setInstallationViewHeaderData(deliveryInstallationRepository.diViewGetHeaderData(id));
        //photo
        diViewDto.setMachineInstallationPhotoList(deliveryInstallationRepository.getMachineInstallationPhotoList(id));

        List<Map<String, Object>> checkpointListByDiId =
                deliveryInstallationRepository.diViewGetCheckpointListByDiId(id);

        diViewDto.setInstallationCheckpointList(checkpointListByDiId);
        apiResponse.setResult(diViewDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAvailableCsbNo")
    public ResponseEntity<?> getAvailableCsbNo(@RequestParam("csbNo") String csbNo, @RequestParam("model")String model)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String, Object>> no=deliveryInstallationRepository.findByCsbNoContaining(userAuthentication.getDealerId(), csbNo, model);
        apiResponse.setMessage("CSB Number get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return  ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/chassisNoAutoForSearch")
    public ResponseEntity<?> chassisNoAutoForSearch(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String, Object>> no=deliveryInstallationRepository.findByChassisNoContainingForSearch(userAuthentication.getUsername(),chassisNo);
        apiResponse.setMessage("chassis no get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return  ResponseEntity.ok(apiResponse);

    }
}

