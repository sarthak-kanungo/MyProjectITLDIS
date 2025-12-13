package com.i4o.dms.itldis.masters.service.machineinstallation.deliveryInstallation.controller;

import com.i4o.dms.itldis.masters.service.machineinstallation.deliveryInstallation.repository.ServiceMtDeliveryInstallationRepository;
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
@RequestMapping("/api/master/service/deliveryInstallation")
public class ServiceMtDeliveryInstallationController {


    @Autowired
    ServiceMtDeliveryInstallationRepository serviceMtDeliveryInstallationRepository;

    @GetMapping("/getAllDeliveryInstallationDetails")
    public ResponseEntity<?> getAllDeliveryInstallationDetails(@RequestParam("model") String model) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> details = serviceMtDeliveryInstallationRepository.getAllDeliveryInstallationDetails(model);
        apiResponse.setMessage("delivery installation details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(details);
        return ResponseEntity.ok(apiResponse);
    }
}
