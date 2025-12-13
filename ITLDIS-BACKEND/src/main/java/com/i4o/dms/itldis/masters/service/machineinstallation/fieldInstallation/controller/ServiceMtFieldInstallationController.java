package com.i4o.dms.itldis.masters.service.machineinstallation.fieldInstallation.controller;

import com.i4o.dms.itldis.masters.service.machineinstallation.fieldInstallation.repository.ServiceMtFieldInstallationRepository;
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
@RequestMapping("/api/master/service/fieldInstallation")
public class ServiceMtFieldInstallationController {

    @Autowired
    private ServiceMtFieldInstallationRepository serviceMtFieldInstallationRepository;

    @GetMapping("/getAllFieldInstallationDetails")
    public ResponseEntity<?> getAllFieldInstallationDetails(@RequestParam("model") String model) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> details = serviceMtFieldInstallationRepository.getAllFieldInstallationDetails(model);
        apiResponse.setMessage("Field installation details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(details);
        return ResponseEntity.ok(apiResponse);
    }
}
