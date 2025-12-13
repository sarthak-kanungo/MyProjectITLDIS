package com.i4o.dms.itldis.eamg.kit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.kit.dto.KitCreateRequestDto;
import com.i4o.dms.itldis.eamg.kit.dto.KitResponseDto;
import com.i4o.dms.itldis.eamg.kit.repository.EamgKitRepository;

/**
 * EAMG Kit Service Implementation
 * TODO: Implement business logic from EAMG.Kit.Action.*
 */
@Service
public class EamgKitServiceImpl implements EamgKitService {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgKitServiceImpl.class);
    
    @Autowired
    private EamgKitRepository eamgKitRepository;
    
    @Override
    public KitResponseDto createKitFromExcel(MultipartFile file) {
        logger.info("Creating kit from Excel");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public KitResponseDto createKitData(MultipartFile file) {
        logger.info("Creating kit data");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public KitResponseDto createKitByWizard(KitCreateRequestDto request) {
        logger.info("Creating kit by wizard");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public KitResponseDto insertKitByWizard(KitCreateRequestDto request) {
        logger.info("Inserting kit by wizard");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public KitResponseDto modifyKit(KitCreateRequestDto request) {
        logger.info("Modifying kit");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public KitResponseDto deleteUnusedKits() {
        logger.info("Deleting unused kits");
        KitResponseDto response = new KitResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
}
