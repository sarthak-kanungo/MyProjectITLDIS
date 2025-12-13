package com.i4o.dms.itldis.spares.quotation.service.impl;

import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.PartyMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.repository.BranchDepotMasterRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.purchase.sparepurchaseorder.dto.UploadExcelDto;
import com.i4o.dms.itldis.spares.quotation.domain.SpareQuotation;
import com.i4o.dms.itldis.spares.quotation.repository.SpareQuotationRepository;
import com.i4o.dms.itldis.spares.quotation.service.SpareQuotationService;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.itldis.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

@Service
public class SpareQuotationImpl implements SpareQuotationService {


    @Autowired
    private SpareQuotationRepository spareQuotationRepository;

    @Autowired
    private CustomerMasterRepo customerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private BranchDepotMasterRepository branchDepotMasterRepo;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmpRepo;
    
    @Autowired
    private ExcelImportManager excelImportManager;
       

    private Logger logger= LoggerFactory.getLogger(SpareQuotationImpl.class);

    @Override
    public void saveSpareQuotation(SpareQuotation spareQuotation) {        
        
        if (spareQuotation.getCustomerMaster() != null) {
            spareQuotation.setCustomerMaster(customerMasterRepo.getOne(spareQuotation.getCustomerMaster().getId()));
        } else {
        	System.out.println("in else---->");
        	if (spareQuotation.getCustomerType().equals("New/Existing")) {
        	System.out.println("generateCustomerMaster---->");
			CustomerMaster customerMaster = new CustomerMaster();		
			customerMaster.setCustomerType("Individual");
			customerMaster.setRecordType("CUSTOMER");
			customerMaster.setMobileNumber(spareQuotation.getContactNumber());
			customerMaster.setFirstName(spareQuotation.getCustomerName());
			customerMaster.setAddress1(spareQuotation.getCustomerAddress1());
			customerMaster.setAddress2(spareQuotation.getCustomerAddress2());
			customerMaster.setCountry(spareQuotation.getCountry());
			customerMaster.setState(spareQuotation.getState());
			customerMaster.setDistrict(spareQuotation.getDistrict());
			customerMaster.setTehsil(spareQuotation.getTehsil());
			customerMaster.setCity(spareQuotation.getVillage());
			customerMaster.setPinCode(spareQuotation.getPinCode());
			customerMaster.setPostOffice(spareQuotation.getPostOffice());
			customerMasterRepo.save(customerMaster);	
			CustomerMaster customerMaster1 = customerMasterRepo.findByMobileNumber(spareQuotation.getContactNumber());
			spareQuotation.setCustomerMaster(customerMasterRepo.getOne(customerMaster1.getId()));
			}	
		} 
       // DecimalFormat formatter = new DecimalFormat("#0.00");
        spareQuotation.getQuotationPartDetail().forEach(partDetails->{

            partDetails.setGstAmount((partDetails.getCgstAmount()==null?0:partDetails.getCgstAmount()) +
            		(partDetails.getSgstAmount()==null?0:partDetails.getSgstAmount()) +
            		(partDetails.getIgstAmount()==null?0:partDetails.getIgstAmount())
            	);
            partDetails.setSubTotal(partDetails.getNetAmount());
            partDetails.setTotalInvoiceAmount(partDetails.getNetAmount()+partDetails.getGstAmount());
        });


        spareQuotation.setCreatedBy(userAuthentication.getLoginId());
        spareQuotation.setLastModifiedBy(userAuthentication.getLoginId());
        
        //spareQuotation.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        spareQuotation.setBranchDepotMaster(branchDepotMasterRepo.getOne(userAuthentication.getBranchId()));
        
        spareQuotationRepository.save(spareQuotation);
    }
    
    
    @Override
    public List<Map<String, Object>> uploadExcel(MultipartFile multipartFile, String state, String existingItems,String discountRate)
            throws InvalidColumnException, IOException {
        InputStream in = multipartFile.getInputStream();

        excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                                multipartFile.getInputStream()
                        )
                )
        );
        List<UploadExcelDto> partMasters = Poiji.fromExcel(
                in,
                PoijiExcelType.XLSX,
                UploadExcelDto.class,
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
            parts.append(","+p.getItemId());
        	qtys.append(","+p.getQuantity());
        });

        if(parts.toString().length()>0){
	        list = spareQuotationRepository.getItemDetailsByExcel(parts.substring(1), qtys.substring(1), userAuthentication.getBranchId(),state, existingItems,discountRate);
        }
        
        return list;
    }
}
