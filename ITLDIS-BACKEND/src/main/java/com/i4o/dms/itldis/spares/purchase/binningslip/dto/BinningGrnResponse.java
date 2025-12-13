package com.i4o.dms.itldis.spares.purchase.binningslip.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class BinningGrnResponse {
    Map<String,Object> sparesGrn;
    List<Map<String,Object>> sparesGrnItems;

}
