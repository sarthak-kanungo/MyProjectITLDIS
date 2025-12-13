package com.i4o.dms.kubota.salesandpresales.sales.allotment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeAllotmentDto {
  /*  public String reason;
    public Long allotmentId;
    public Set<Long> ids;
    public Boolean mainMachine;*/

    private Long allotmentId;
    private String reason;
    private Long machineDetailId;
}
