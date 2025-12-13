package com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.domain.SPBranchTransferIndent;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentSearchRequestDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentSearchResponseDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.BTIndentViewDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.dto.UploadExcelDto;
import com.i4o.dms.itldis.spares.inventorymanagement.branchtransfer.branchtransferindent.repository.SPBranchTransferIndentRepo;

import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.excelmanager.util.ExcelImportManager;
import com.poiji.bind.Poiji;
import com.poiji.exception.PoijiExcelType;
import com.poiji.option.PoijiOptions;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@Service
public class SPBranchTransferIndentServiceImpl implements SPBranchTransferIndentService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private SPBranchTransferIndentRepo indentRepo;
    
    @Autowired
    private ExcelImportManager excelImportManager;

    @Autowired
    private JasperPrintService jasperPrintService;
	
	@Override
	public ApiResponse<?> findSubBranch() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.findSubBranchByBranchId(userAuthentication.getBranchId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getReqToBranchDeatilsById() {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.getReqToBranchDeatilsById(userAuthentication.getBranchId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getReqByEmployeeDetail() {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.getEmployeeDetailsById(userAuthentication.getDealerEmployeeId()));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getAllStatus() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.getAllStatus());
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> autoGetIndentNo(String indentNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.autoGetIndentNo(indentNo));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> getSpareItemDetails(String itemNo, Long suppliedByBranchId) {
		ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.getSpareItemDetails(itemNo, userAuthentication.getBranchId(), suppliedByBranchId));
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> uploadExcel(MultipartFile file, Long suppByBranchId, String existingItems) throws IOException {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		if(file.isEmpty()) {
        	NoSuchElementException ex = new NoSuchElementException("\"Excel file can't be null!\"");
        	logger.info("Uploading Excel Spare Part Exception: " + ex.getMessage());
        	ex.printStackTrace();
        	throw ex;
        }
		
		InputStream in = file.getInputStream();
		excelImportManager.checkXLSValidity(
                PreDefinedColumns,
                excelImportManager.getXLSHeaders(
                        WorkbookFactory.create(
                        		file.getInputStream()
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
        
        if(partMasters.isEmpty()) {
        	NoSuchElementException ex = new NoSuchElementException("\"The Excel should not be blank!\"");
        	logger.info("Uploading Excel Spare Part Exception: " + ex.getMessage());
        	ex.printStackTrace();
        	throw ex;
        }
        
        StringBuffer parts = new StringBuffer("");
        StringBuffer qtys = new StringBuffer("");
        
        List<Map<String, Object>> list = new ArrayList<>();
        
        partMasters.forEach(p -> {
        	parts.append("," + p.getItemNo());
        	qtys.append("," + p.getQuantity());
        });

        if(parts.toString().length()>0){
	        list = indentRepo.getItemDetailsByExcel(
	        		parts.substring(1), qtys.substring(1), existingItems, userAuthentication.getBranchId(), suppByBranchId);
        }
        
        apiResponse.setResult(list);
		
		return apiResponse;
	}

	@Transactional
	@Override
	public ApiResponse<?> saveBTIndent(SPBranchTransferIndent branchTransferIndent) {
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
        if(branchTransferIndent.getDraftFlag()){
        	branchTransferIndent.setReqNo("DRA/"+ ThreadLocalRandom.current().nextInt(1000) +"/"+ System.currentTimeMillis());
        }
        
        branchTransferIndent.setStatus(branchTransferIndent.getDraftFlag() ? "Draft" : "Submitted");
        
		if (branchTransferIndent.getId() == null) {
			branchTransferIndent.setCreatedBy(userAuthentication.getLoginId());
			branchTransferIndent.setCreatedDate(new Date());
		} else if(branchTransferIndent.getId() != null) {
        	branchTransferIndent.setLastModifiedBy(userAuthentication.getLoginId());
        	branchTransferIndent.setLastModifiedDate(new Date());
        }
        
        indentRepo.save(branchTransferIndent);
        
        apiResponse.setResult("Submitted");
        apiResponse.setMessage(branchTransferIndent.getDraftFlag() 
				? "Saving Branch Transfer Indent Successful" : "Submit Branch Transfer Indent Successful");
        
		return apiResponse;
	}

	@Override
	public ApiResponse<?> searchBTIndent(BTIndentSearchRequestDto indentSearchDto) {
		ApiResponse<List<BTIndentSearchResponseDto>> apiResponse = new ApiResponse<>();
		
		List<BTIndentSearchResponseDto> responceDtoList = indentRepo.searchBTIndent(
				indentSearchDto.getIndentNo(),
				indentSearchDto.getFromDate(),
				indentSearchDto.getToDate(),
				indentSearchDto.getStatus(),
				indentSearchDto.getPage(),
				indentSearchDto.getSize());
		
		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}

	@Override
	public ApiResponse<?> viewBTIndentByReqNo(String reqNo) {
		ApiResponse<BTIndentViewDto> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.findByReqNo(reqNo));
		
		return apiResponse;
	}

	@Override
	public void printBTIndentReport(String reqId, String printStatus, String filePath,
			OutputStream outputStream) throws Exception 
	{
		String jasperfile = filePath + "BranchTransferIndentReport.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("indentId", reqId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
	}

	@Override
	public ApiResponse<?> autoCompleteItemNumber(String itemNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(indentRepo.autoCompleteItemNumber(itemNo, userAuthentication.getDealerId()));
		
		return apiResponse;
	}

}
