package com.i4o.dms.itldis.masters.usermanagement.dealerusers.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalItemMasterSearchDto {

    private String dmsItemNo;

    private String vendorItemNo;

    private String dealerVendorCode;

    private Integer page;

    private  Integer size;

}
