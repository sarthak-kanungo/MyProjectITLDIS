package com.i4o.dms.itldis.masters.areamaster.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaMaster {
    private String state;
    private String district;
    private String tehsil;
    private String postOffice;
    private String city;
    private Integer pincode;
}
