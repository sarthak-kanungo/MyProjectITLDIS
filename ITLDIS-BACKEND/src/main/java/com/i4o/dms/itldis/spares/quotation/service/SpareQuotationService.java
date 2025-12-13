package com.i4o.dms.itldis.spares.quotation.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;


public interface SpareQuotationService {
	String[] PreDefinedColumns = new String[]{"itemId","quantity"};
	
    void saveSpareQuotation(SpareQuotation spareQuotation);
    
    List<Map<String,Object>> uploadExcel(MultipartFile multipartFile,String state, String existingItems, String discountRate) throws IOException;
}
