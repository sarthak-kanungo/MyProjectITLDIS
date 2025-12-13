package com.i4o.dms.itldis.eamg.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.model.dto.ModelCreateRequestDto;
import com.i4o.dms.itldis.eamg.model.dto.ModelResponseDto;

/**
 * EAMG Model Service Interface
 */
public interface EamgModelService {
    ModelResponseDto addVehicle(MultipartFile file);
    ModelResponseDto addModel(ModelCreateRequestDto request);
    ModelResponseDto completeModel(ModelCreateRequestDto request);
    ModelResponseDto deleteModel(ModelCreateRequestDto request);
    ModelResponseDto modifyModel(ModelCreateRequestDto request);
    ModelResponseDto addAggregate(ModelCreateRequestDto request);
    ModelResponseDto variantAggregates(ModelCreateRequestDto request);
    ModelResponseDto attachGroup(ModelCreateRequestDto request);
    ModelResponseDto displayGroups(String modelNo);
    ModelResponseDto assignComponent(ModelCreateRequestDto request);
    ModelResponseDto tempModelCreation(ModelCreateRequestDto request);
}
