package com.i4o.dms.itldis.eamg.tool.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.tool.dto.ToolCreateRequestDto;
import com.i4o.dms.itldis.eamg.tool.dto.ToolResponseDto;

/**
 * EAMG Tool Service Interface
 */
public interface EamgToolService {
    ToolResponseDto createTool(MultipartFile file);
    ToolResponseDto createToolData(MultipartFile file);
    ToolResponseDto createToolByWizard(ToolCreateRequestDto request);
    ToolResponseDto insertToolByWizard(ToolCreateRequestDto request);
    ToolResponseDto modifyTool(ToolCreateRequestDto request);
    ToolResponseDto deleteUnusedTools();
}
