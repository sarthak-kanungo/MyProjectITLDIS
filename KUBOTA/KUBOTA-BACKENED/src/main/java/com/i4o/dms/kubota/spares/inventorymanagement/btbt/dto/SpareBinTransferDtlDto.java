package com.i4o.dms.kubota.spares.inventorymanagement.btbt.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpareBinTransferDtlDto {

	private ItemDto itemNo;
    
    private IdMasterDto fromStore;
    
    private IdMasterDto fromLocation;
    
    private Integer fromBinStock;
    
    private Integer transferQty;
    
    private IdMasterDto toStore;
    
    private IdMasterDto toLocation;
    
    private Integer toBinStock;
}
