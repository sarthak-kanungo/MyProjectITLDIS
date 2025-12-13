package com.i4o.dms.itldis.masters.salesandpresales.enquirysource.controller;


import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.domain.EnquirySourceMaster;
import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.dto.EnquirySourceMasterSearchDto;
import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.dto.EnquirySourceMasterSearchResponse;
import com.i4o.dms.itldis.masters.salesandpresales.enquirysource.repository.EnquirySourceMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import io.swagger.annotations.ApiOperation;

import org.hibernate.boot.model.source.spi.EntitySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-with", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("api/salesandpresales/enquirySource")
public class EnquirySourceMasterController {

    @Autowired
    private EnquirySourceMasterRepo enquirySourceMasterRepo;

    private ResponseEntity<Map<String, Object>> entity = null;

    @Autowired
    private KubotaEmployeeRepository kubotaEmployeeRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @ApiOperation("")
    @PostMapping(value = "/SaveEnquirySource")
    public ResponseEntity<?> addEnquirySource(@Valid @RequestBody EnquirySourceMaster enquirySourceMaster)  {

        KubotaEmployeeMaster kubotaEmployeeMaster = kubotaEmployeeRepository.getOne(userAuthentication.getKubotaEmployeeId());
        enquirySourceMaster.setCreatedBy(kubotaEmployeeMaster);
        enquirySourceMaster.setLastModifiedBy(kubotaEmployeeMaster);

        ApiResponse<EnquirySourceMaster> apiResponse = new ApiResponse<>();
        EnquirySourceMaster enquirySource = enquirySourceMasterRepo.findBySourceCode(enquirySourceMaster.getSourceCode());
        if(enquirySource == null){
        	enquirySource = enquirySourceMasterRepo.save(enquirySourceMaster);
        	apiResponse.setMessage("Export Enquiry source save successfully");
        }
        else{
        	apiResponse.setMessage("Enquiry Source Code is already Exist");	
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquirySource);
        return ResponseEntity.ok(apiResponse);

    }

//    @GetMapping(value = "/getEnquirySource")
//    public ResponseEntity<?> getAllEnquirySource(
//            @RequestParam(required = false) String enquirySource) {
//
//        ApiResponse apiResponse = new ApiResponse();
//        if (!enquirySource.isEmpty()) {
//            List<EnquirySourceMaster> enquirySourceList = enquirySourceMasterRepo.findByEnquirySourceContaining(enquirySource);
//            apiResponse.setResult("Get All Data ");
//            apiResponse.setStatus(HttpStatus.OK.value());
//            apiResponse.setResult(enquirySourceList);
//            return ResponseEntity.ok(apiResponse);
//        } else {
//            List<EnquirySourceMaster> enquirySourceList = enquirySourceMasterRepo.getAllEnquirySource();
//            apiResponse.setResult("Get All Data ");
//            apiResponse.setStatus(HttpStatus.OK.value());
//            apiResponse.setResult(enquirySourceList);
//            return ResponseEntity.ok(apiResponse);
//
//        }
//
//    }

    @PutMapping(value = "/changeActiveStatus")
    public ResponseEntity<?> changeEnquirySourceStatus(@RequestParam Long id) {
        ApiResponse<EnquirySourceMaster> apiResponse = new ApiResponse<>();

        EnquirySourceMaster enquirySourceMaster = enquirySourceMasterRepo.getOne(id);
        enquirySourceMaster.setActiveStatus(enquirySourceMaster.getActiveStatus().equalsIgnoreCase("Y") ? "N" : "Y");

        EnquirySourceMaster updated = enquirySourceMasterRepo.save(enquirySourceMaster);
        apiResponse.setMessage("STATUS UPDATED");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(updated);
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/searchEnquiryMaster")
    public ResponseEntity<?> searchEnquiryMasterMaster(@RequestBody EnquirySourceMasterSearchDto enquirySourceMasterSearchDto){
        ApiResponse< List<EnquirySourceMasterSearchResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Enquiry Source Master Search Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());

        apiResponse.setResult(enquirySourceMasterRepo.searchEnquirySourceMaster(
                enquirySourceMasterSearchDto.getSourceCode(),
                enquirySourceMasterSearchDto.getSourceName(),
                enquirySourceMasterSearchDto.getPurpose(),
                enquirySourceMasterSearchDto.getPage(),
                enquirySourceMasterSearchDto.getSize()));

        apiResponse.setCount(enquirySourceMasterRepo.searchEnquirySourceMasterCount(
                enquirySourceMasterSearchDto.getSourceCode(),
                enquirySourceMasterSearchDto.getSourceName(),
                enquirySourceMasterSearchDto.getPurpose(),
                enquirySourceMasterSearchDto.getPage(),
                enquirySourceMasterSearchDto.getSize()));
        return ResponseEntity.ok(apiResponse);
    }










    @PutMapping(value = "/updateEnquirySource")
    public ResponseEntity<?> updateEnquirySource(@RequestBody EnquirySourceMaster enquirySourceMaster) {
        ApiResponse apiResponse = new ApiResponse();

        Optional<EnquirySourceMaster> enquirySource = enquirySourceMasterRepo.findById(enquirySourceMaster.getId());
        if (enquirySource.isPresent()) {
            enquirySourceMaster.setSourceCode(enquirySourceMaster.getSourceCode());
            enquirySourceMaster.setSourceName(enquirySourceMaster.getSourceName());
            enquirySourceMaster.setActiveStatus(enquirySourceMaster.getActiveStatus());
            EnquirySourceMaster updateEnquirySource = enquirySourceMasterRepo.save(enquirySourceMaster);
            apiResponse.setMessage("UPDATED SUCCESSFULLY");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(enquirySource);
            return ResponseEntity.ok(apiResponse);
        } else {
            apiResponse.setMessage("TRY AGAIN");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(enquirySource);
            return ResponseEntity.ok(apiResponse);
        }
    }

//    //modified 6th feb
//    @GetMapping("/searchByEnquirySource")
//    public ResponseEntity<?> searchByEnquriySource(@RequestParam String enquirySource, @RequestParam String activeStatus) {
//
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Enquiry Source");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(enquirySourceMasterRepo.findByEnquirySourceAndActiveStatus(enquirySource, activeStatus));
//        return ResponseEntity.ok(apiResponse);
//
//    }

    @GetMapping("/getSourceCodeAutocomplete")
    public ResponseEntity<?> getSourceCodeAutocomplete(String sourceCode) {

        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List source = enquirySourceMasterRepo.getSourceCode(sourceCode);
        apiResponse.setMessage("get source Code Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(source);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getSourceNameAutocomplete")
    public ResponseEntity<?> getSourceNameAutocomplete(String sourceName) {

        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        List source = enquirySourceMasterRepo.getSourceName(sourceName);
        apiResponse.setMessage("get source Name Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(source);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getPurpose")
    public ResponseEntity<?> getPurpose() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("get purpose");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquirySourceMasterRepo.getPurpose());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityCategory")
    public ResponseEntity<?> getActivityCategory() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("get ActivityCategory");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquirySourceMasterRepo.getActivityCategory());
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getSource")
    public ResponseEntity<?> getSourceDropDown() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("get Source Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquirySourceMasterRepo.getSourceDropDown());
        return ResponseEntity.ok(apiResponse);
    }

}
