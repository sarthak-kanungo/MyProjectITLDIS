package com.i4o.dms.itldis.eamg.group.controller;

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

import com.i4o.dms.itldis.eamg.group.dto.GroupCreateRequestDto;
import com.i4o.dms.itldis.eamg.group.dto.GroupResponseDto;
import com.i4o.dms.itldis.eamg.group.service.EamgGroupService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Group Controller
 * Handles all Group-related operations
 * Reference: EAMG.Group.Action.*
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/group")
public class EamgGroupController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgGroupController.class);
    
    @Autowired
    private EamgGroupService eamgGroupService;
    
    /**
     * Create Group by Excel upload
     * Reference: EAMG_CreateGroupAction
     */
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createGroup(@RequestPart("file") MultipartFile file,
                                                   @RequestParam(required = false) String groupNo) {
        try {
            GroupResponseDto response = eamgGroupService.createGroupFromExcel(file, groupNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating group from Excel", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating group: " + e.getMessage()));
        }
    }
    
    /**
     * Create Group Data
     * Reference: EAMG_CreateGroupDataAction
     */
    @PostMapping("/create-data")
    public ResponseEntity<ApiResponse> createGroupData(@RequestPart("file") MultipartFile file) {
        try {
            GroupResponseDto response = eamgGroupService.createGroupData(file);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating group data", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating group data: " + e.getMessage()));
        }
    }
    
    /**
     * Modify Group Details
     * Reference: EAMG_ModifyGroupDetailsAction
     */
    @PostMapping("/modify")
    public ResponseEntity<ApiResponse> modifyGroup(@RequestBody GroupCreateRequestDto request) {
        try {
            GroupResponseDto response = eamgGroupService.modifyGroup(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying group", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying group: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Unused Groups
     * Reference: EAMG_DeleteUnusedGroupsAction
     */
    @PostMapping("/delete-unused")
    public ResponseEntity<ApiResponse> deleteUnusedGroups() {
        try {
            GroupResponseDto response = eamgGroupService.deleteUnusedGroups();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting unused groups", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting unused groups: " + e.getMessage()));
        }
    }
    
    /**
     * Group Creation by Wizard
     * Reference: EAMG_GroupCreationByWzAction
     */
    @PostMapping("/create-by-wizard")
    public ResponseEntity<ApiResponse> createGroupByWizard(@RequestBody GroupCreateRequestDto request) {
        try {
            GroupResponseDto response = eamgGroupService.createGroupByWizard(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error creating group by wizard", e);
            return ResponseEntity.ok(ApiResponse.error("Error creating group by wizard: " + e.getMessage()));
        }
    }
    
    /**
     * Show Group BOM Custom
     * Reference: EAMG_ShowGrpBomCustomAction
     */
    @GetMapping("/bom-custom")
    public ResponseEntity<ApiResponse> showGroupBomCustom(@RequestParam String groupNo) {
        try {
            GroupResponseDto response = eamgGroupService.getGroupBomCustom(groupNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting group BOM custom", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting group BOM: " + e.getMessage()));
        }
    }
    
    /**
     * Change Group Sequence
     * Reference: ChangeGroupSequenceAction
     */
    @PostMapping("/change-sequence")
    public ResponseEntity<ApiResponse> changeGroupSequence(@RequestBody GroupCreateRequestDto request) {
        try {
            GroupResponseDto response = eamgGroupService.changeGroupSequence(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error changing group sequence", e);
            return ResponseEntity.ok(ApiResponse.error("Error changing group sequence: " + e.getMessage()));
        }
    }
    
    /**
     * Change FCode Sequence
     * Reference: EAMG_ChangeFCodeSequenceAction
     */
    @PostMapping("/change-fcode-sequence")
    public ResponseEntity<ApiResponse> changeFCodeSequence(@RequestBody GroupCreateRequestDto request) {
        try {
            GroupResponseDto response = eamgGroupService.changeFCodeSequence(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error changing FCode sequence", e);
            return ResponseEntity.ok(ApiResponse.error("Error changing FCode sequence: " + e.getMessage()));
        }
    }
}
