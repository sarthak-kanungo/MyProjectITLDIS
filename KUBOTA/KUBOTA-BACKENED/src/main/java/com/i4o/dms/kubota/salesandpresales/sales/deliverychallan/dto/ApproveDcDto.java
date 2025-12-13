package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveDcDto {
    private String approvalFlag;
    private Long userId;
    private Long dcId;
    private String remarks;
}
