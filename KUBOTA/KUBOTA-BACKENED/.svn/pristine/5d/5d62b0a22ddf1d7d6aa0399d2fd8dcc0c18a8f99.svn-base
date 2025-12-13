package com.i4o.dms.kubota.spares.salesorder.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.i4o.dms.kubota.spares.salesorder.domain.SpareSalesOrder;
import org.springframework.web.multipart.MultipartFile;


public interface SpareSalesOrderService  {

    void saveSpareSaleOrder(SpareSalesOrder spareSalesOrder);
    
    String[] PreDefinedColumns = new String[]{"Item Number","Quantity"};
    
    List<Map<String,Object>> customerUploadExcel(MultipartFile multipartFile,String state,String existingItems, Double discountRate) throws IOException;
    
}
