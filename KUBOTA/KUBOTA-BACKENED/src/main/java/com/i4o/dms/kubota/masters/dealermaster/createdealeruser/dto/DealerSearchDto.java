package com.i4o.dms.kubota.masters.dealermaster.createdealeruser.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealerSearchDto {
    private String employeeCode;
    
    private String employeeName;
    
    private String applicablefor;

    private Integer page;

    private Integer size;

}
