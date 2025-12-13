package com.i4o.dms.itldis.masters.service.psc.controller;


import com.i4o.dms.itldis.masters.service.psc.repository.ServiceMtPscRepository;
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
@RequestMapping("/api/master/service/pscAggregate")
public class ServiceMtPscController {

@Autowired
private ServiceMtPscRepository serviceMtPscRepository;

    @GetMapping(value = "/getAllCheckpoints")
    public ResponseEntity<?> getAllCheckpoints()
    {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> checkpoints=serviceMtPscRepository.getAllCheckpoints();
        apiResponse.setMessage("Checkpoint list get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(checkpoints);
        return ResponseEntity.ok(apiResponse);
    }
}
