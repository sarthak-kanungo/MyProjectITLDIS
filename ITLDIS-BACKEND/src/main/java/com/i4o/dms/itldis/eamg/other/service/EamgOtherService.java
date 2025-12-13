package com.i4o.dms.itldis.eamg.other.service;

import com.i4o.dms.itldis.eamg.other.dto.OtherRequestDto;
import com.i4o.dms.itldis.eamg.other.dto.OtherResponseDto;

/**
 * EAMG Other Service Interface
 */
public interface EamgOtherService {
    OtherResponseDto addTSN(OtherRequestDto request);
    OtherResponseDto eamgVsOEM(OtherRequestDto request);
    OtherResponseDto eamgPartVsOEMPart(OtherRequestDto request);
    byte[] createPDF(OtherRequestDto request);
    OtherResponseDto getUsageDetail(String partNo);
}
