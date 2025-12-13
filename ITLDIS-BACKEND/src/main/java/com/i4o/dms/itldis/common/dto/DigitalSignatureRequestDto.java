package com.i4o.dms.itldis.common.dto;

import lombok.Data;

/**
 * Digital Signature Request DTO
 */
@Data
public class DigitalSignatureRequestDto {
    private String userId;
    private String documentId;
    private String documentType;
    private String signatureData;
}
