package com.i4o.dms.itldis.service.pdi.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.pdi.domain.ServicePdi;
import com.i4o.dms.itldis.service.pdi.dto.PdiSearchDto;
import com.i4o.dms.itldis.service.pdi.dto.PdiSearchResponse;
import com.i4o.dms.itldis.service.pdi.dto.PdiViewDto;
import com.i4o.dms.itldis.service.pdi.repository.PdiRepository;
import com.i4o.dms.itldis.service.pdi.service.ServicePdiImpl;
import com.i4o.dms.itldis.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/pdi")
public class PdiController {

    @Autowired
    private PdiRepository pdiRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private ServicePdiImpl servicePdiImpl;
    Logger logger = LoggerFactory.getLogger(PdiController.class);

//    @GetMapping(value = "/autoCompleteChassisNo")
//    public ResponseEntity<?> getAutoCompleteChassisNo(@RequestParam("dealerId") Long dealerId,
//                                                      @RequestParam("chassisNo") String chassisNo){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("ChassisNo get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(serviceMtPdiAggregateRepo.autoCompleteChassisNo(dealerId,chassisNo));
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @GetMapping(value = "/grnDetailsByChassisNo")
//    public ResponseEntity<?> getGrnDetailsByChassisNo(@RequestParam("chassisNo") String chassisNo){
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Details get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(serviceMtPdiAggregateRepo.grnDetailsByChassisNo(chassisNo));
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping(value = "/savePdi")
    public ResponseEntity<?> savePdi(@Valid @RequestBody ServicePdi servicePdi) {
        ApiResponse apiResponse=servicePdiImpl.savePdi(servicePdi);
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Search Form Service
     */

    @GetMapping(value = "/search/autocompleteChassis")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> searchAutoCompleteChassisNumber(@RequestParam String searchString) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("ChassisNo auto complete search list");
        apiResponse.setStatus(HttpStatus.OK.value());

        apiResponse.setResult(
                pdiRepository.searchAutocompleteChassisNumber(searchString, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/search/autocompleteKaiInvoiceNumber")
    public ResponseEntity<?> searchAutocompleteKaiInvoiceNumber(@RequestParam String kaiInvoiceNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Kai Invoice No auto complete search list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(
                pdiRepository.searchKaiInvoiceNumber(kaiInvoiceNumber, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/search/autocompleteDmsGrnNumber")
    public ResponseEntity<?> searchAutocompleteDmsGrnNumber(@RequestParam String dmsGrnNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Dms Grn Number auto complete search list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(
                pdiRepository.searchDmsGrnNumber(dmsGrnNumber, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping(value = "/pdiSearch")
    public ResponseEntity<?> pdiSearch(@RequestBody PdiSearchDto pdiSearchDto) {
        ApiResponse apiResponse = new ApiResponse();
        List<PdiSearchResponse> result = pdiRepository.pdiSearch(pdiSearchDto.pdiId, pdiSearchDto.pdiFromDate,
                pdiSearchDto.pdiToDate, pdiSearchDto.kaiInvoiceNumber,
                pdiSearchDto.dmsGrnNumber, pdiSearchDto.dmsGrnFromDate,
                pdiSearchDto.dmsGrnToDate,userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(), userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(), pdiSearchDto.page,
                pdiSearchDto.size,
                userAuthentication.getUsername(),
                'N',pdiSearchDto.getOrgId());
        apiResponse.setMessage("Pdi search list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0L;
        if(result!=null && result.size()>0){
        	apiResponse.setCount(result.get(0).getRecordCount());
        }
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getPdiById/{id}")
    public ResponseEntity<?> getPdiById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Pdi Details");
        apiResponse.setStatus(HttpStatus.OK.value());

        PdiViewDto pdiViewDto = new PdiViewDto();
        pdiViewDto.setPdiHeaderData(pdiRepository.pdiViewGetHeaderData(id));

        List<Map<String, Object>> checkpointListByPdiId =
                pdiRepository.pdiViewGetCheckpointListByPdiId(id);

        pdiViewDto.setPdiCheckpointList(checkpointListByPdiId);
        apiResponse.setResult(pdiViewDto);
        return ResponseEntity.ok(apiResponse);

    }
}
