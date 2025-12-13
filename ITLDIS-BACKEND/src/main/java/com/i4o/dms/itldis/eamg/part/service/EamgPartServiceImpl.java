package com.i4o.dms.itldis.eamg.part.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.part.dto.PartCreateRequestDto;
import com.i4o.dms.itldis.eamg.part.dto.PartResponseDto;
import com.i4o.dms.itldis.eamg.part.repository.EamgPartRepository;

/**
 * EAMG Part Service Implementation
 * TODO: Implement business logic from EAMG.Part.Action.*
 */
@Service
public class EamgPartServiceImpl implements EamgPartService {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgPartServiceImpl.class);
    
    @Autowired
    private EamgPartRepository eamgPartRepository;
    
    @Override
    public PartResponseDto addPart(MultipartFile file) {
        logger.info("Adding part from Excel");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto createPartByWizard(PartCreateRequestDto request) {
        logger.info("Creating part by wizard");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto updateComponentByExcel(MultipartFile file) {
        logger.info("Updating component by Excel");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto modifyComponentParams(PartCreateRequestDto request) {
        logger.info("Modifying component params");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto deleteUnusedParts() {
        logger.info("Deleting unused parts");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto priceList(PartCreateRequestDto request) {
        logger.info("Processing price list");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto sapPartAction(PartCreateRequestDto request) {
        logger.info("Processing SAP part action");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public PartResponseDto partVsAlternatePainted(PartCreateRequestDto request) {
        logger.info("Processing part vs alternate painted");
        PartResponseDto response = new PartResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
}
