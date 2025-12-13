package com.i4o.dms.itldis.eamg.part.controller;

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

import com.i4o.dms.itldis.eamg.part.dto.PartCreateRequestDto;
import com.i4o.dms.itldis.eamg.part.dto.PartResponseDto;
import com.i4o.dms.itldis.eamg.part.service.EamgPartService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Part Controller
 * Handles Part-related operations
 * Reference: EAMG.Part.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/part")
public class EamgPartController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgPartController.class);
    
    @Autowired
    private EamgPartService eamgPartService;
    
    /**
     * Add Part
     * Reference: EAMG_AddPartAction
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPart(@RequestPart("file") MultipartFile file) {
        try {
            PartResponseDto response = eamgPartService.addPart(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding part", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding part: " + e.getMessage()));
        }
    }
    
    /**
     * Part Creation by Wizard
     * Reference: EAMG_PartCreationByWizardAction
     */
    @PostMapping("/create-by-wizard")
    public ResponseEntity<ApiResponse> createPartByWizard(@RequestBody PartCreateRequestDto request) {
        try {
            PartResponseDto response = eamgPartService.createPartByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating part by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating part by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Update Component by Excel
     * Reference: EAMG_UpdateCompByExcelAction
     */
    @PostMapping("/update-by-excel")
    public ResponseEntity<ApiResponse> updateComponentByExcel(@RequestPart("file") MultipartFile file) {
        try {
            PartResponseDto response = eamgPartService.updateComponentByExcel(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error updating component by Excel", e);
            return ResponseEntity.ok(ApiResponse.error("Error updating component by Excel: " + e.getMessage()));
        }
    }
    
    /**
     * Component Modification Parameters
     * Reference: EAMG_CompModifParamsAction
     */
    @PostMapping("/modify-params")
    public ResponseEntity<ApiResponse> modifyComponentParams(@RequestBody PartCreateRequestDto request) {
        try {
            PartResponseDto response = eamgPartService.modifyComponentParams(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying component params", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying component params: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Unused Parts
     * Reference: EAMG_DeleteUnusedpartsAction
     */
    @PostMapping("/delete-unused")
    public ResponseEntity<ApiResponse> deleteUnusedParts() {
        try {
            PartResponseDto response = eamgPartService.deleteUnusedParts();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting unused parts", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting unused parts: " + e.getMessage()));
        }
    }
    
    /**
     * Price List Action
     * Reference: EAMG_PriceListAction
     */
    @PostMapping("/price-list")
    public ResponseEntity<ApiResponse> priceList(@RequestBody PartCreateRequestDto request) {
        try {
            PartResponseDto response = eamgPartService.priceList(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing price list", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing price list: " + e.getMessage()));
        }
    }
    
    /**
     * SAP Part Action
     * Reference: EAMG_SAP_PartAction
     */
    @PostMapping("/sap")
    public ResponseEntity<ApiResponse> sapPartAction(@RequestBody PartCreateRequestDto request) {
        try {
            PartResponseDto response = eamgPartService.sapPartAction(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing SAP part", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing SAP part: " + e.getMessage()));
        }
    }
    
    /**
     * Part vs Alternate Painted Action
     * Reference: PartVsAlternatPaintedAction
     */
    @PostMapping("/alternate-painted")
    public ResponseEntity<ApiResponse> partVsAlternatePainted(@RequestBody PartCreateRequestDto request) {
        try {
            PartResponseDto response = eamgPartService.partVsAlternatePainted(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing part vs alternate painted", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing part vs alternate painted: " + e.getMessage()));
        }
    }
}
