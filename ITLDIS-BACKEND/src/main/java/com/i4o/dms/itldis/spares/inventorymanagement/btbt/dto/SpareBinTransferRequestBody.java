package com.i4o.dms.itldis.spares.inventorymanagement.btbt.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpareBinTransferRequestBody {

	private List<SpareBinTransferDtlDto> itemDetails;
}
