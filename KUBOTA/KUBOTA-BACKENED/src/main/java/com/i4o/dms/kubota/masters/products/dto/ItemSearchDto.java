package com.i4o.dms.kubota.masters.products.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchDto {


    private String itemNo;
    private String model;
    private String product;
    private String series;
    private String subModel;
    private String variant;
    private Integer page;
    private Integer size;

}
