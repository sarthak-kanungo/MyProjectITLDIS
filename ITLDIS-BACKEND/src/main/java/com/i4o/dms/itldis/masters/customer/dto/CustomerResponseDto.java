package com.i4o.dms.itldis.masters.customer.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Customer Response DTO
 */
@Data
public class CustomerResponseDto {
    private String status;
    private String message;
    private String customerId;
    private CustomerRequestDto customerDetails;
    private List<CustomerRequestDto> customerList;
    private Map<String, Object> summary;
}
