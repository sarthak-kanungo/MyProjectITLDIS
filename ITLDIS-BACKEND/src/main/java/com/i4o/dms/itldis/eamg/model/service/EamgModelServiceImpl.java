package com.i4o.dms.itldis.eamg.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.model.dto.ModelCreateRequestDto;
import com.i4o.dms.itldis.eamg.model.dto.ModelResponseDto;
import com.i4o.dms.itldis.eamg.model.repository.EamgModelRepository;

/**
 * EAMG Model Service Implementation
 * TODO: Implement business logic from EAMG.Model.Action.*
 */
@Service
public class EamgModelServiceImpl implements EamgModelService {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgModelServiceImpl.class);
    
    @Autowired
    private EamgModelRepository eamgModelRepository;
    
    @Override
    public ModelResponseDto addVehicle(MultipartFile file) {
        logger.info("Adding vehicle from Excel");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto addModel(ModelCreateRequestDto request) {
        logger.info("Adding model");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto completeModel(ModelCreateRequestDto request) {
        logger.info("Completing model");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto deleteModel(ModelCreateRequestDto request) {
        logger.info("Deleting model");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto modifyModel(ModelCreateRequestDto request) {
        logger.info("Modifying model");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto addAggregate(ModelCreateRequestDto request) {
        logger.info("Adding aggregate");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto variantAggregates(ModelCreateRequestDto request) {
        logger.info("Processing variant aggregates");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto attachGroup(ModelCreateRequestDto request) {
        logger.info("Attaching group");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto displayGroups(String modelNo) {
        logger.info("Displaying groups for model: {}", modelNo);
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto assignComponent(ModelCreateRequestDto request) {
        logger.info("Assigning component");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
    
    @Override
    public ModelResponseDto tempModelCreation(ModelCreateRequestDto request) {
        logger.info("Creating temp model");
        ModelResponseDto response = new ModelResponseDto();
        response.setStatus("SUCCESS");
        return response;
    }
}
