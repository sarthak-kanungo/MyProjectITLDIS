package com.i4o.dms.itldis.eamg.kit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.kit.dto.KitCreateRequestDto;
import com.i4o.dms.itldis.eamg.kit.dto.KitResponseDto;
import com.i4o.dms.itldis.eamg.kit.service.EamgKitService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Kit Controller
 * Handles Kit-related operations
 * Reference: EAMG.Kit.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/kit")
public class EamgKitController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgKitController.class);
    
    @Autowired
    private EamgKitService eamgKitService;
    
    /**
     * Create Kit
     * Reference: EAMG_KitCreationAction
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createKit(@RequestPart("file") MultipartFile file) {
        try {
            KitResponseDto response = eamgKitService.createKitFromExcel(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating kit", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating kit: " + e.getMessage()));
        }
    }
    
    /**
     * Create Kit Data
     * Reference: EAMG_CreateKitDataAction
     */
    @PostMapping("/create-data")
    public ResponseEntity<ApiResponse> createKitData(@RequestPart("file") MultipartFile file) {
        try {
            KitResponseDto response = eamgKitService.createKitData(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating kit data", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating kit data: " + e.getMessage()));
        }
    }
    
    /**
     * Create Kit by Wizard
     * Reference: EAMG_CreateKitByWizardAction
     */
    @PostMapping("/create-by-wizard")
    public ResponseEntity<ApiResponse> createKitByWizard(@RequestBody KitCreateRequestDto request) {
        try {
            KitResponseDto response = eamgKitService.createKitByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating kit by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating kit by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Kit Insertion by Wizard
     * Reference: EAMG_KitInsertionByWzAction
     */
    @PostMapping("/insert-by-wizard")
    public ResponseEntity<ApiResponse> insertKitByWizard(@RequestBody KitCreateRequestDto request) {
        try {
            KitResponseDto response = eamgKitService.insertKitByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error inserting kit by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error inserting kit by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Modify Kit Details
     * Reference: EAMG_ModifyKitDetailsAction
     */
    @PostMapping("/modify")
    public ResponseEntity<ApiResponse> modifyKit(@RequestBody KitCreateRequestDto request) {
        try {
            KitResponseDto response = eamgKitService.modifyKit(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying kit", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying kit: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Unused Kits
     * Reference: EAMG_DeleteUnusedKitsAction
     */
    @PostMapping("/delete-unused")
    public ResponseEntity<ApiResponse> deleteUnusedKits() {
        try {
            KitResponseDto response = eamgKitService.deleteUnusedKits();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting unused kits", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting unused kits: " + e.getMessage()));
        }
    }
}
