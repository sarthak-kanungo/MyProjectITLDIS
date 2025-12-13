package com.i4o.dms.kubota.salesandpresales.sales.blockmachinesforsale.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusByIdsRequest {
	List<Long> blockIds;
}
