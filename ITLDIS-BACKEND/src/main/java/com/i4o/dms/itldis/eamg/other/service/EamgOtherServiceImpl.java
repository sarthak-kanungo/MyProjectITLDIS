package com.i4o.dms.itldis.eamg.other.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.i4o.dms.itldis.eamg.other.dto.OtherRequestDto;
import com.i4o.dms.itldis.eamg.other.dto.OtherResponseDto;

/**
 * EAMG Other Service Implementation
 * TODO: Implement business logic from EAMG.Other.Action.*
 */
@Service
public class EamgOtherServiceImpl implements EamgOtherService {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgOtherServiceImpl.class);
    
    @Override
    public OtherResponseDto addTSN(OtherRequestDto request) {
        logger.info("Adding TSN");
        OtherResponseDto response = new OtherResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public OtherResponseDto eamgVsOEM(OtherRequestDto request) {
        logger.info("Processing EAMG vs OEM");
        OtherResponseDto response = new OtherResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public OtherResponseDto eamgPartVsOEMPart(OtherRequestDto request) {
        logger.info("Processing EAMG part vs OEM part");
        OtherResponseDto response = new OtherResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public byte[] createPDF(OtherRequestDto request) {
        logger.info("Creating PDF");
        return new byte[0];
    }
    
    @Override
    public OtherResponseDto getUsageDetail(String partNo) {
        logger.info("Getting usage detail for part: {}", partNo);
        OtherResponseDto response = new OtherResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
}
