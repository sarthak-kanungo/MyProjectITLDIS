package com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.service;


import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.domain.SparePurchaseOrder;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.OPSItemsDetailResponseDto;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.PoViewDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SparesPurchaseOrderService {
	String[] PreDefinedColumns = new String[]{"itemId","quantity"};

    void saveSparePurchaseOrder(SparePurchaseOrder sparePurchaseOrder);

    PoViewDto getViewDto(Long id,String orderType);
    
    List<Map<String,Object>> uploadExcel(MultipartFile multipartFile,String orderType, String priceType, String existingItems) throws IOException;

	OPSItemsDetailResponseDto getOPSitemsDeatail(String opsId, String priceType, String existingItems);
}
