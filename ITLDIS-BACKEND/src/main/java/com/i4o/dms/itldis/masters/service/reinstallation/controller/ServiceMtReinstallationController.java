package com.i4o.dms.itldis.masters.service.reinstallation.controller;


import com.i4o.dms.itldis.masters.service.reinstallation.repository.ServiceMtRiModelAggregateMappingRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/master/service/reinstallation")
public class ServiceMtReinstallationController {


    @Autowired
    private ServiceMtRiModelAggregateMappingRepo serviceMtRiModelAggregateMappingRepo;

    @GetMapping("/getAllReInstallationDetails")
    public ResponseEntity<?> getAllReInstallationDetails(@RequestParam("series") String series) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> details = serviceMtRiModelAggregateMappingRepo.getAllReInstallationDetails(series);
        apiResponse.setMessage(" Reinstallation details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(details);
        return ResponseEntity.ok(apiResponse);
    }
}
