package com.i4o.dms.itldis.masters.customer.dto;

import lombok.Data;
import java.util.Date;
import java.util.Map;

/**
 * Customer Request DTO
 */
@Data
public class CustomerRequestDto {
    private String customerId;
    private String customerName;
    private String dealerCode;
    private String address;
    private String city;
    private String state;
    private String pinCode;
    private String mobileNo;
    private String email;
    private Date dateOfBirth;
    private String customerCategory;
    private Map<String, Object> additionalData;
}
