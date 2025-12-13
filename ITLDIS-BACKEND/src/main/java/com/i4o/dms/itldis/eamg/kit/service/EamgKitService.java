package com.i4o.dms.itldis.eamg.kit.service;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.kit.dto.KitCreateRequestDto;
import com.i4o.dms.itldis.eamg.kit.dto.KitResponseDto;

/**
 * EAMG Kit Service Interface
 */
public interface EamgKitService {
    KitResponseDto createKitFromExcel(MultipartFile file);
    KitResponseDto createKitData(MultipartFile file);
    KitResponseDto createKitByWizard(KitCreateRequestDto request);
    KitResponseDto insertKitByWizard(KitCreateRequestDto request);
    KitResponseDto modifyKit(KitCreateRequestDto request);
    KitResponseDto deleteUnusedKits();
}
