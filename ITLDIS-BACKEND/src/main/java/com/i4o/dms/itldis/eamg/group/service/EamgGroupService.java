package com.i4o.dms.itldis.eamg.group.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.group.dto.GroupCreateRequestDto;
import com.i4o.dms.itldis.eamg.group.dto.GroupResponseDto;

/**
 * EAMG Group Service Interface
 */
public interface EamgGroupService {
    
    /**
     * Create Group from Excel
     * Reference: EAMG_CreateGroupAction
     */
    GroupResponseDto createGroupFromExcel(MultipartFile file, String groupNo);
    
    /**
     * Create Group Data
     * Reference: EAMG_CreateGroupDataAction
     */
    GroupResponseDto createGroupData(MultipartFile file);
    
    /**
     * Modify Group Details
     * Reference: EAMG_ModifyGroupDetailsAction
     */
    GroupResponseDto modifyGroup(GroupCreateRequestDto request);
    
    /**
     * Delete Unused Groups
     * Reference: EAMG_DeleteUnusedGroupsAction
     */
    GroupResponseDto deleteUnusedGroups();
    
    /**
     * Create Group by Wizard
     * Reference: EAMG_GroupCreationByWzAction
     */
    GroupResponseDto createGroupByWizard(GroupCreateRequestDto request);
    
    /**
     * Get Group BOM Custom
     * Reference: EAMG_ShowGrpBomCustomAction
     */
    GroupResponseDto getGroupBomCustom(String groupNo);
    
    /**
     * Change Group Sequence
     * Reference: ChangeGroupSequenceAction
     */
    GroupResponseDto changeGroupSequence(GroupCreateRequestDto request);
    
    /**
     * Change FCode Sequence
     * Reference: EAMG_ChangeFCodeSequenceAction
     */
    GroupResponseDto changeFCodeSequence(GroupCreateRequestDto request);
}
