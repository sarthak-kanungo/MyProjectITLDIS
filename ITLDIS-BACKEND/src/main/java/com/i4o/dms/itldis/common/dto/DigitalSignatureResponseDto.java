package com.i4o.dms.itldis.common.dto;

import lombok.Data;

/**
 * Digital Signature Response DTO
 */
@Data
public class DigitalSignatureResponseDto {
    private String status;
    private String message;
    private String signatureUrl;
    private String signatureData;
}
