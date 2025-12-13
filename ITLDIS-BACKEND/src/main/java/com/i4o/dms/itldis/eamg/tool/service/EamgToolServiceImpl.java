package com.i4o.dms.itldis.eamg.tool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.tool.dto.ToolCreateRequestDto;
import com.i4o.dms.itldis.eamg.tool.dto.ToolResponseDto;
import com.i4o.dms.itldis.eamg.tool.repository.EamgToolRepository;

/**
 * EAMG Tool Service Implementation
 * TODO: Implement business logic from EAMG.Tool.Action.*
 */
@Service
public class EamgToolServiceImpl implements EamgToolService {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgToolServiceImpl.class);
    
    @Autowired
    private EamgToolRepository eamgToolRepository;
    
    @Override
    public ToolResponseDto createTool(MultipartFile file) {
        logger.info("Creating tool from Excel");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ToolResponseDto createToolData(MultipartFile file) {
        logger.info("Creating tool data");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ToolResponseDto createToolByWizard(ToolCreateRequestDto request) {
        logger.info("Creating tool by wizard");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ToolResponseDto insertToolByWizard(ToolCreateRequestDto request) {
        logger.info("Inserting tool by wizard");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ToolResponseDto modifyTool(ToolCreateRequestDto request) {
        logger.info("Modifying tool");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ToolResponseDto deleteUnusedTools() {
        logger.info("Deleting unused tools");
        ToolResponseDto response = new ToolResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
}
