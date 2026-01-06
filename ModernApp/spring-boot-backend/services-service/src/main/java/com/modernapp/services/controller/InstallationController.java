package com.modernapp.services.controller;

import com.modernapp.services.dto.PendingInstallationDTO;
import com.modernapp.services.repository.VehicleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services/installation")
@CrossOrigin(origins = "http://localhost:4200")
public class InstallationController {

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    private com.modernapp.services.repository.InstallationDetailsRepository installationDetailsRepository;

    @GetMapping("/pending")
    public ResponseEntity<List<PendingInstallationDTO>> getPendingInstallations(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode) {
        
        // Handle empty strings as null for optional parameters
        if (chassisNo != null && chassisNo.trim().isEmpty()) chassisNo = null;
        if (dealerCode != null && dealerCode.trim().isEmpty()) dealerCode = null;

        List<PendingInstallationDTO> installations = vehicleDetailsRepository.findPendingInstallations(chassisNo, dealerCode);
        return ResponseEntity.ok(installations);
    }

    @GetMapping("/view")
    public ResponseEntity<List<com.modernapp.services.dto.ViewInstallationDTO>> getCompletedInstallations(
            @RequestParam(required = false) String chassisNo,
            @RequestParam(required = false) String dealerCode) {
        
        // Handle empty strings as null for optional parameters
        if (chassisNo != null && chassisNo.trim().isEmpty()) chassisNo = null;
        if (dealerCode != null && dealerCode.trim().isEmpty()) dealerCode = null;

        List<com.modernapp.services.dto.ViewInstallationDTO> installations = installationDetailsRepository.findCompletedInstallations(chassisNo, dealerCode);
        return ResponseEntity.ok(installations);
    }
}
