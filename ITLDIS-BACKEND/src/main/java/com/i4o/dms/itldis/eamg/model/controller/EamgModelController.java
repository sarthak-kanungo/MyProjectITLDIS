package com.i4o.dms.itldis.eamg.model.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.model.dto.ModelCreateRequestDto;
import com.i4o.dms.itldis.eamg.model.dto.ModelResponseDto;
import com.i4o.dms.itldis.eamg.model.service.EamgModelService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Model Controller
 * Handles Model-related operations
 * Reference: EAMG.Model.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/model")
public class EamgModelController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgModelController.class);
    
    @Autowired
    private EamgModelService eamgModelService;
    
    /**
     * Add Vehicle/Model
     * Reference: EAMG_addVehicleAction
     */
    @PostMapping("/add-vehicle")
    public ResponseEntity<ApiResponse> addVehicle(@RequestPart("file") MultipartFile file) {
        try {
            ModelResponseDto response = eamgModelService.addVehicle(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding vehicle", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding vehicle: " + e.getMessage()));
        }
    }
    
    /**
     * Add Model
     * Reference: EAMG_AddModelAction
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addModel(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.addModel(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding model", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding model: " + e.getMessage()));
        }
    }
    
    /**
     * Complete Model
     * Reference: EAMG_CompleteModelAction
     */
    @PostMapping("/complete")
    public ResponseEntity<ApiResponse> completeModel(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.completeModel(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error completing model", e);
            return ResponseEntity.ok(ApiResponse.error("Error completing model: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Model
     * Reference: EAMG_DeleteModelAction
     */
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteModel(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.deleteModel(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting model", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting model: " + e.getMessage()));
        }
    }
    
    /**
     * Modify Model Details
     * Reference: EAMG_ModifyModelDetailsAction
     */
    @PostMapping("/modify")
    public ResponseEntity<ApiResponse> modifyModel(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.modifyModel(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying model", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying model: " + e.getMessage()));
        }
    }
    
    /**
     * Add Aggregate
     * Reference: EAMG_AddAggregateAction
     */
    @PostMapping("/add-aggregate")
    public ResponseEntity<ApiResponse> addAggregate(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.addAggregate(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding aggregate", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding aggregate: " + e.getMessage()));
        }
    }
    
    /**
     * Variant Aggregates
     * Reference: EAMG_Variant_AggregatesAction
     */
    @PostMapping("/variant-aggregates")
    public ResponseEntity<ApiResponse> variantAggregates(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.variantAggregates(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error processing variant aggregates", e);
            return ResponseEntity.ok(ApiResponse.error("Error processing variant aggregates: " + e.getMessage()));
        }
    }
    
    /**
     * Attach Group
     * Reference: EAMG_AttachGroupAction
     */
    @PostMapping("/attach-group")
    public ResponseEntity<ApiResponse> attachGroup(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.attachGroup(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error attaching group", e);
            return ResponseEntity.ok(ApiResponse.error("Error attaching group: " + e.getMessage()));
        }
    }
    
    /**
     * Display Groups
     * Reference: EAMG_DisplayGroupsAction
     */
    @GetMapping("/display-groups")
    public ResponseEntity<ApiResponse> displayGroups(@RequestParam String modelNo) {
        try {
            ModelResponseDto response = eamgModelService.displayGroups(modelNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error displaying groups", e);
            return ResponseEntity.ok(ApiResponse.error("Error displaying groups: " + e.getMessage()));
        }
    }
    
    /**
     * Assign Component to Model
     * Reference: AssignComponentToModelAction
     */
    @PostMapping("/assign-component")
    public ResponseEntity<ApiResponse> assignComponent(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.assignComponent(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error assigning component", e);
            return ResponseEntity.ok(ApiResponse.error("Error assigning component: " + e.getMessage()));
        }
    }
    
    /**
     * Temp Model Creation
     * Reference: EAMG_TempModelCreationAction
     */
    @PostMapping("/temp-create")
    public ResponseEntity<ApiResponse> tempModelCreation(@RequestBody ModelCreateRequestDto request) {
        try {
            ModelResponseDto response = eamgModelService.tempModelCreation(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating temp model", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating temp model: " + e.getMessage()));
        }
    }
}
