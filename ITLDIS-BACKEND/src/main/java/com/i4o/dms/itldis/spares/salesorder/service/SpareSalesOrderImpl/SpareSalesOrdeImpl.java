package com.i4o.dms.itldis.spares.salesorder.service.SpareSalesOrderImpl;

import com.i4o.dms.itldis.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;
import com.i4o.dms.itldis.spares.quotation.repository.SpareQuotationRepository;
import com.i4o.dms.itldis.spares.salesorder.domain.SpareSalesOrder;
import com.i4o.dms.itldis.spares.salesorder.dto.UploadCustomerExcelDto;
import com.i4o.dms.itldis.spares.salesorder.repository.SpareSalesOrderRepository;
import com.i4o.dms.itldis.spares.salesorder.service.SpareSalesOrderService;
import com.i4o.dms.itldis.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.Arrays;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class SpareSalesOrdeImpl implements SpareSalesOrderService {

    @Autowired
    private SpareSalesOrderRepository spareSalesOrderRepository;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private CustomerMasterRepo customerMasterRepo;
/*
    @Autowired
    private ProspectMasterRepo prospectMasterRepo;*/
    
    @Autowired
    private ExcelImportManager excelImportManager;

    @Autowired
    private SpareQuotationRepository spareQuotationRepository;

    @Override
    public void saveSpareSaleOrder(SpareSalesOrder spareSalesOrder) {

        spareSalesOrder.setCreatedBy(userAuthentication.getLoginId());
        spareSalesOrder.setCreatedDate(new Date());
        spareSalesOrder.setBranchId(userAuthentication.getBranchId());
        spareSalesOrder.setSalesOrderDate(new Date());
        if(spareSalesOrder.getId()!=null){
        	spareSalesOrder.setLastModifiedBy(userAuthentication.getLoginId());
        	spareSalesOrder.setLastModifiedDate(new Date());
        }
        
        if (spareSalesOrder.getCustomerMaster() != null) {
            spareSalesOrder.setCustomerMaster(customerMasterRepo.getOne(spareSalesOrder.getCustomerMaster().getId()));
        }
        /*if (spareSalesOrder.getProspectMaster() != null) {
            spareSalesOrder.setProspectMaster(prospectMasterRepo.getOne(spareSalesOrder.getProspectMaster().getId()));
        }*/
        if (spareSalesOrder.getSpareQuotation() != null) {
            SpareQuotation spareQuotation = spareQuotationRepository.getOne(spareSalesOrder.getSpareQuotation().getId());
            spareQuotation.setQuotationStatus("Closed");
            spareSalesOrder.setSpareQuotation(spareQuotation);
        }
        spareSalesOrderRepository.save(spareSalesOrder);
        
        if(spareSalesOrder.getDeleteParts()!=null && !spareSalesOrder.getDeleteParts().equals("")){
        		spareSalesOrderRepository.deletePart(Arrays.asList(spareSalesOrder.getDeleteParts().split(",")));
	        
        }
        
    }

	
	public List<Map<String, Object>> customerUploadExcel(MultipartFile multipartFile, String state,
			String existingItems, Double discountRate) throws IOException {
		InputStream in = multipartFile.getInputStream();

        excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                                multipartFile.getInputStream()
                        )
                )
        );
        List<UploadCustomerExcelDto> partMasters = Poiji.fromExcel(
                in,
                PoijiExcelType.XLSX,
                UploadCustomerExcelDto.class,
                PoijiOptions.PoijiOptionsBuilder
                        .settings()
                        .headerStart(0)
                        .build()
        );
        StringBuffer parts = new StringBuffer("");
        StringBuffer qtys = new StringBuffer("");
        
        List<Map<String, Object>> list = new ArrayList<>();
        
        System.out.println(existingItems);
        partMasters.forEach(p -> {
        	parts.append("," + p.getItemNumber());
        	qtys.append("," + p.getQuantity());
        });
        System.out.println("SpareSalesOrdeImpl=="+parts.toString().length());
        if(parts.toString().length()>0){
        	System.out.println("in_the_SpareSalesOrdeImpl");
        	list = spareSalesOrderRepository.getItemDetailsByExcel(parts.substring(1), qtys.substring(1), state, userAuthentication.getBranchId(), existingItems, discountRate);
        }
		return list;
	}


}
