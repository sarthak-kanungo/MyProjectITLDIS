package com.i4o.dms.itldis.service.mrc.controller;

import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.mrc.domain.ServiceMrc;
import com.i4o.dms.itldis.service.mrc.dto.MachineSearchResponseDto;
import com.i4o.dms.itldis.service.mrc.dto.SearchResponseServiceMrc;
import com.i4o.dms.itldis.service.mrc.dto.SearchServiceMrc;
import com.i4o.dms.itldis.service.mrc.dto.ServiceMrcViewResponseDto;
import com.i4o.dms.itldis.service.mrc.repository.MrcRepository;
import com.i4o.dms.itldis.service.mrc.service.ServiceMrcService;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/mrc")
class ServiceMrcController {

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private MrcRepository mrcRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserAuthentication getUserAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private ServiceMrcService serviceMrcService;

    private Logger logger = LoggerFactory.getLogger(ServiceMrcController.class);

    @GetMapping("/search/chassisNoByAccPacInvoice")
    public ResponseEntity<?> getChassisNumber(@RequestParam Long accpacInvoiceId) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Chassis No get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(mrcRepository.getChassisNoByAccpacInvoice(accpacInvoiceId, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/getChassisNo")
    public ResponseEntity<?> getChassisNumberFilter(@RequestParam String searchValue) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Chassis No get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(mrcRepository.getChassisNo(searchValue, userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);

    }
    
    @GetMapping("/getInvoiceForSearch")
    public ResponseEntity<?> getInvoiceForSearch(@RequestParam String invoiceNumber) {
    	  ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
          apiResponse.setMessage("Invoice No  get Successfully");
          apiResponse.setStatus(HttpStatus.OK.value());
          apiResponse.setResult(mrcRepository.getAutoCompleteAccpacInvoiceNumberSearch(invoiceNumber, userAuthentication.getUsername()));
          return ResponseEntity.ok(apiResponse);

    }
    @GetMapping("/search/checkChassisNumber")
    public ResponseEntity<?> checkChassisNumber(@RequestParam Long chassisNumberId, @RequestParam(required = false) Long accpacInvoiceId  ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());

        Map<String, Object> map = mrcRepository.checkByChassisNo(chassisNumberId);
        if (map.get("result").equals(false)) {
            apiResponse.setResult(mrcRepository.getChassisNoByAccpacInvoice(accpacInvoiceId, userAuthentication.getDealerId()));
            apiResponse.setMessage("create Service Mrc");
        } else {
            apiResponse.setResult(map);
            apiResponse.setMessage(map.get("draftFlag").equals(true) ? "Service MRC already save in draft mode" : "Service MRC already create for the given chassis no");
        }
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/search/autocompleteKaiInvoiceNumber")
    public ResponseEntity<?> getAutoCompleteInvoiceNumber(@RequestParam String invoiceNumber) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Invoice No  get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(mrcRepository.getAutoCompleteAccpacInvoiceNumber(invoiceNumber, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/saveMrc", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveServiceMrc(@RequestPart(value = "serviceMrc") ServiceMrc serviceMrc,
                                            @RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList) {
    	serviceMrc.setMrcDate(new Date());
    	serviceMrc.setCreatedBy(userAuthentication.getLoginId());
    	
    	if(serviceMrc.getId()!=null){
    		serviceMrc.setModifiedBy(userAuthentication.getLoginId());
    		serviceMrc.setModifiedDate(new Date());
    	}
        serviceMrcService.saveServiceMrc(serviceMrc, multipartFileList);
        ApiResponse<ServiceMrc> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service mrc save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/mrcSearch")
    public ResponseEntity<?>searchMrc(@RequestBody SearchServiceMrc searchServiceMrc){
        ApiResponse<List<SearchResponseServiceMrc>> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("Mrc Search List");
        List<SearchResponseServiceMrc>  result = mrcRepository.searchServiceMrc(searchServiceMrc.getMrcNo(),
        		searchServiceMrc.getInvoiceNo(),
                searchServiceMrc.getMrcFromDate(),
                searchServiceMrc.getMrcToDate(),
                searchServiceMrc.getInvoiceFromDate(),
                searchServiceMrc.getInvoiceToDate(),
                searchServiceMrc.getPage(),
                searchServiceMrc.getSize(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
        		userAuthentication.getUsername(),
        		searchServiceMrc.getOrgId());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping(value = "/machineFormFSearch")
    public ResponseEntity<?>machineFormFSearch(@RequestBody SearchServiceMrc searchServiceMrc){
        ApiResponse<List<MachineSearchResponseDto>> apiResponse=new ApiResponse<>();
        apiResponse.setMessage("Machine Form F - Form 22 Search List");
        List<MachineSearchResponseDto>  result = mrcRepository.machineFormFSearch(searchServiceMrc.getMachineNo(),
                searchServiceMrc.getPage(),
                searchServiceMrc.getSize(),
        		userAuthentication.getUsername(),
        		searchServiceMrc.getOrgId());
        apiResponse.setResult(result);
        Long count = 0l;
        if(result!=null && result.size()>0){
        	count = result.get(0).getRecordCount();
        }
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getMrcById/{id}")
    public ResponseEntity<?> getServiceMrcById(@PathVariable Long id) {

        ApiResponse<ServiceMrcViewResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Service mrc by id details");

        Map<String, Object> header = mrcRepository.getMrcHeaderData(id, userAuthentication.getDealerId());
        List<Map<String, Object>> checkpointList = mrcRepository.getMrcCheckpointListByMrcId(id);
        List<Map<String, Object>> photoList = mrcRepository.getMrcPhotoList(id);
        List<Map<String, Object>> discrepancyList = mrcRepository.getMrcDiscrepancyList(id);

        ServiceMrcViewResponseDto serviceMrcViewResponseDto = new ServiceMrcViewResponseDto();

        serviceMrcViewResponseDto.setMrcCheckpointList(checkpointList);
        serviceMrcViewResponseDto.setMrcHeaderData(header);
        serviceMrcViewResponseDto.setMrcPhotoList(photoList);
        serviceMrcViewResponseDto.setMrcDiscrepancyList(discrepancyList);

        apiResponse.setResult(serviceMrcViewResponseDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getMrcSearchForMrcListing")
    public ResponseEntity<?> getMrcSearchForMrcListing(@RequestParam String searchString) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Mrc listing get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(mrcRepository.getMrcSearchForMrcListing(searchString,userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

}
