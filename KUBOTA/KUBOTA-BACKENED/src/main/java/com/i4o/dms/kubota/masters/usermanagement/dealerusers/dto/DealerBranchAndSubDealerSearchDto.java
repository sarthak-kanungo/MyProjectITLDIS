package com.i4o.dms.kubota.masters.usermanagement.dealerusers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DealerBranchAndSubDealerSearchDto {
    private String branchCode;

    private String subDealerName;

    private String category;

    private Integer page;

    private Integer size;

}
