package com.i4o.dms.itldis.eamg.part.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.part.dto.PartCreateRequestDto;
import com.i4o.dms.itldis.eamg.part.dto.PartResponseDto;

/**
 * EAMG Part Service Interface
 */
public interface EamgPartService {
    PartResponseDto addPart(MultipartFile file);
    PartResponseDto createPartByWizard(PartCreateRequestDto request);
    PartResponseDto updateComponentByExcel(MultipartFile file);
    PartResponseDto modifyComponentParams(PartCreateRequestDto request);
    PartResponseDto deleteUnusedParts();
    PartResponseDto priceList(PartCreateRequestDto request);
    PartResponseDto sapPartAction(PartCreateRequestDto request);
    PartResponseDto partVsAlternatePainted(PartCreateRequestDto request);
}
