package com.i4o.dms.itldis.eamg.tool.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.tool.dto.ToolCreateRequestDto;
import com.i4o.dms.itldis.eamg.tool.dto.ToolResponseDto;
import com.i4o.dms.itldis.eamg.tool.service.EamgToolService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Tool Controller
 * Handles Tool-related operations
 * Reference: EAMG.Tool.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/tool")
public class EamgToolController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgToolController.class);
    
    @Autowired
    private EamgToolService eamgToolService;
    
    /**
     * Tool Creation Action
     * Reference: EAMG_ToolCreationAction
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTool(@RequestPart("file") MultipartFile file) {
        try {
            ToolResponseDto response = eamgToolService.createTool(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating tool", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating tool: " + e.getMessage()));
        }
    }
    
    /**
     * Create Tool Data
     * Reference: EAMG_CreateToolDataAction
     */
    @PostMapping("/create-data")
    public ResponseEntity<ApiResponse> createToolData(@RequestPart("file") MultipartFile file) {
        try {
            ToolResponseDto response = eamgToolService.createToolData(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating tool data", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating tool data: " + e.getMessage()));
        }
    }
    
    /**
     * Create Tool by Wizard
     * Reference: EAMG_CreateToolByWizardAction
     */
    @PostMapping("/create-by-wizard")
    public ResponseEntity<ApiResponse> createToolByWizard(@RequestBody ToolCreateRequestDto request) {
        try {
            ToolResponseDto response = eamgToolService.createToolByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating tool by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating tool by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Tool Insertion by Wizard
     * Reference: EAMG_ToolInsertionByWzAction
     */
    @PostMapping("/insert-by-wizard")
    public ResponseEntity<ApiResponse> insertToolByWizard(@RequestBody ToolCreateRequestDto request) {
        try {
            ToolResponseDto response = eamgToolService.insertToolByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error inserting tool by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error inserting tool by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Modify Tool Details
     * Reference: EAMG_ModifyToolDetailsAction
     */
    @PostMapping("/modify")
    public ResponseEntity<ApiResponse> modifyTool(@RequestBody ToolCreateRequestDto request) {
        try {
            ToolResponseDto response = eamgToolService.modifyTool(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying tool", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying tool: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Unused Tools
     * Reference: EAMG_DeleteUnusedToolsAction
     */
    @PostMapping("/delete-unused")
    public ResponseEntity<ApiResponse> deleteUnusedTools() {
        try {
            ToolResponseDto response = eamgToolService.deleteUnusedTools();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting unused tools", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting unused tools: " + e.getMessage()));
        }
    }
}
