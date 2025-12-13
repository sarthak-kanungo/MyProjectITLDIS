package com.i4o.dms.kubota.masters.service.checkpointspecification.controller;

import com.i4o.dms.kubota.masters.service.checkpointspecification.repository.CheckpointSpecificationRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/master/service/checkpointSpecification")
public class ServiceCheckpointSpecificationController {

    @Autowired
    private CheckpointSpecificationRepository checkpointSpecificationRepository;

    @GetMapping(value = "/specificationDropdown")
    public ResponseEntity<?> getCheckpointSpecification(@RequestParam Integer checkpointId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Specification List.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(checkpointSpecificationRepository.getSpecificationList(checkpointId));
        return ResponseEntity.ok(apiResponse);
    }

}
