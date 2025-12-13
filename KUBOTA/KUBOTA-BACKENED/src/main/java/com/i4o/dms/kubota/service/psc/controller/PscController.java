package com.i4o.dms.kubota.service.psc.controller;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.pdi.dto.PdiSearchDto;
import com.i4o.dms.kubota.service.pdi.dto.PdiViewDto;
import com.i4o.dms.kubota.service.psc.service.ServicePscImpl;
import com.i4o.dms.kubota.service.psc.domain.ServicePsc;
import com.i4o.dms.kubota.service.psc.dto.PscSearchDto;
import com.i4o.dms.kubota.service.psc.dto.PscSearchResponse;
import com.i4o.dms.kubota.service.psc.dto.PscViewDto;
import com.i4o.dms.kubota.service.psc.repository.PscRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//import com.i4o.dms.kubota.service.psc.service.ServicePscImpl;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/psc")
public class PscController {

    @Autowired
    private PscRepository pscRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private ServicePscImpl servicePscImpl;

    @PostMapping(value = "/savePsc")
    public ResponseEntity<?> savePsc(@Valid @RequestBody ServicePsc servicePsc) {

            ApiResponse apiResponse=servicePscImpl.savePsc(servicePsc);
            return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/autoCompleteChassisNumber")
    public ResponseEntity<?> autoCompleteChassisNumber(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> no=pscRepository.getAutoCompleteChassisNumber(userAuthentication.getBranchId(),chassisNo);
        apiResponse.setMessage("Chassis No get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDetailsByChassisNo")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();

        apiResponse.setMessage("Chassis No get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(pscRepository.getDetailsByChassisNo(chassisNo));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/autoCompletePscNo")
    public ResponseEntity<?> autoCompletePscNo(@RequestParam("pscNo") String pscNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> no=pscRepository.getPscNoContaining(pscNo,userAuthentication.getUsername());
        apiResponse.setMessage("PSC No get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/searchPsc")
    public ResponseEntity<?> searchPsc(@RequestBody PscSearchDto pscSearchDto){
        ApiResponse apiResponse = new ApiResponse();
        List<PscSearchResponse> result = pscRepository.searchPsc(pscSearchDto.getChassisNo(),
                pscSearchDto.getPscNo(),pscSearchDto.getFromDate(),pscSearchDto.getToDate(),userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),userAuthentication.getKubotaEmployeeId(),pscSearchDto.getManagementAccess(),
                pscSearchDto.getPage(),pscSearchDto.getSize(),userAuthentication.getUsername(),
                'N',pscSearchDto.getOrgId());
        apiResponse.setMessage("get PSC");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);

        Long count = 0L;
        if(result!=null && result.size()>0){
        	apiResponse.setCount(result.get(0).getRecordCount());
        }
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getPscById/{id}")
    public ResponseEntity<?> getPscById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Psc Details");
        apiResponse.setStatus(HttpStatus.OK.value());

        PscViewDto pscViewDto = new PscViewDto();
        pscViewDto.setPscViewHeaderData(pscRepository.pscViewGetHeaderData(id));

        List<Map<String, Object>> checkpointListById =
                pscRepository.pscViewGetCheckpointListById(id);

        pscViewDto.setPscCheckpointList(checkpointListById);
        apiResponse.setResult(pscViewDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/autoCompleteChassisNumberForSearch")
    public ResponseEntity<?> autoCompleteChassisNumberForSearch(@RequestParam("chassisNo") String chassisNo)
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> no=pscRepository.getAutoCompleteChassisNumberForSearch(userAuthentication.getUsername(),chassisNo);
        apiResponse.setMessage("Chassis No get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(no);
        return ResponseEntity.ok(apiResponse);
    }

}


