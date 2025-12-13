package com.i4o.dms.itldis.spares.partrequisition.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PartRequisitionDetailDto {
    Map<String,Object> sparePartRequisition;
    List<Map<String,Object>> sparePartRequisitionItem;

}
