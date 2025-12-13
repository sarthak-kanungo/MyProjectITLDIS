package com.i4o.dms.kubota.masters.service.jobcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.masters.service.jobcard.repository.ServiceMtServiceTypeInfoRepo;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/jobcard")
public class ServiceMtServiceTypeInfoController {
    @Autowired
    private ServiceMtServiceTypeInfoRepo serviceMtServiceTypeInfoRepo;


    @GetMapping("/dropDownServiceType")
    public ResponseEntity<?> dropDownServiceType(@RequestParam Long serviceCategoryId,@RequestParam Long modelId)
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("MRC get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceMtServiceTypeInfoRepo.dropDownServiceType(serviceCategoryId,modelId));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownModel")
    public ResponseEntity<?> dropDownModel()
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("MRC get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(serviceMtServiceTypeInfoRepo.dropDownModel());
        return ResponseEntity.ok(apiResponse);
    }
}
