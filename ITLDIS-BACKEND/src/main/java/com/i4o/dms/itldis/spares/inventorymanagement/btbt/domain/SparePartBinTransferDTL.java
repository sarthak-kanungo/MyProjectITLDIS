package com.i4o.dms.itldis.spares.inventorymanagement.btbt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SP_PART_BIN_TRANSFER_DTL")
public class SparePartBinTransferDTL {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long binTransferDtlId;
	
	private Long binTransfer_id;
    
    private String itemNo;
    
    private Long fromStoreId;
    
    private Long fromBinLocationId;
    
    private int fromBinStock;
    
    private int transferQty;
    
    private Long toStoreId;
    
    private Long toBinLocationId;
    
    private int toBinStock;
    
}
