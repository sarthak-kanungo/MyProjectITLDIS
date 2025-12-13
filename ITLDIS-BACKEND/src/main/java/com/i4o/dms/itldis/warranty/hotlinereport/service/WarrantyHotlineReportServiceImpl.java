package com.i4o.dms.itldis.warranty.hotlinereport.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.itldis.warranty.hotlinereport.domain.WarrantyHotlineReport;
import com.i4o.dms.itldis.warranty.hotlinereport.domain.WarrantyHotlineReportAttachment;
import com.i4o.dms.itldis.warranty.hotlinereport.repository.WarrantyHotlineReportAttachmentRepo;
import com.i4o.dms.itldis.warranty.hotlinereport.repository.WarrantyHotlineReportRepo;
import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.constant.model.DmsConstants;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineReportSearchDto;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineReportViewDto;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineSearchResponceDto;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author suraj.gaur
 */
@Slf4j
@Service
public class WarrantyHotlineReportServiceImpl implements WarrantyHotlineReportService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private StorageService storageService;
    
    @Autowired
	private WarrantyHotlineReportRepo waHotlineReportRepo;
    
    @Autowired
    private WarrantyHotlineReportAttachmentRepo attachmentRepo;
	
    @Autowired
    private JasperPrintService jasperPrintService;
	
    
	/**
	 * This method submits a Warranty Hotline Report along with its attachments.
	 * It saves the data of the report, including the created and modified dates and
	 * the creator's ID.
	 * If attachments are present, it saves them to the storage service and updates
	 * their status accordingly.
	 * @param hotlineReport     The Warranty Hotline Report to submit.
	 * @param multipartFileList The list of attachments to the report.
	 * @return ApiResponse object containing the result of the submission.
	 * @throws InvalidColumnException Thrown when there is an invalid column in the database.
	 * @throws IOException Thrown when there is an error accessing or reading the attachments.
	 */
	@Transactional
	@Override
	public ApiResponse<?> submitHotlineReport(WarrantyHotlineReport hotlineReport, List<MultipartFile> multipartFileList)
			throws InvalidColumnException, IOException 
	{
		ApiResponse<String> apiResponse = new ApiResponse<>();
		
		// If the report is new, set the creator's ID and creation date.
		if(hotlineReport.getId() == null) {
			hotlineReport.setCreatedBy(userAuthentication.getLoginId());
			hotlineReport.setCreatedDate(new Date());
			hotlineReport.setHtlrDate(new Date());
        }
		// If the report already exists, update the last modified by ID and date.
		else {
			hotlineReport.setLastModifiedBy(userAuthentication.getLoginId());
        	hotlineReport.setLastModifiedDate(new Date());
        	hotlineReport.setVendorResponse(hotlineReport.getVendorResponseTemp());
        }
		
		// Save the report data (excluding attachments).
		WarrantyHotlineReport warrantyHotlineReport = waHotlineReportRepo.save(hotlineReport);
		
		// If attachments are present, save them to the storage service and update their status.
		if (multipartFileList.size() <= 5 && !multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
            	WarrantyHotlineReportAttachment hotlineReportAttachment = new WarrantyHotlineReportAttachment();
                
            	String hotlinePhoto = m.getOriginalFilename();
            	
				// Set the attachment status based on the report's status.
                if(hotlineReport.getStatus() != null){
                	if(hotlineReport.getStatus().equals(DmsConstants.PHOTO_SUBMIT)) {
                        String photoName = "wa_hotline_rpt_" + DmsConstants.PHOTO_SUBMIT + System.currentTimeMillis() + "_" + hotlinePhoto;
                        
                        storageService.store(m, photoName);	//Storing the file to the location
                        
                        hotlineReportAttachment.setFileName(photoName);
                        hotlineReportAttachment.setAttachStatus(DmsConstants.PHOTO_SUBMIT);
                	}
                    else if(hotlineReport.getStatus().equals(DmsConstants.PHOTO_ANSWERED)) {
                        String photoName = "wa_hotline_rpt_" + DmsConstants.PHOTO_ANSWERED + System.currentTimeMillis() + "_" + hotlinePhoto;
                        
                        storageService.store(m, photoName);	//Storing the file to the location
                        
                        hotlineReportAttachment.setFileName(photoName);
                    	hotlineReportAttachment.setAttachStatus(DmsConstants.PHOTO_ANSWERED);
                    }
                    	
                }               
                else {
                	// Throw an exception if the report status is blank.
                	NoSuchElementException ex = new NoSuchElementException("\"The status should not be blank!\"");
                	logger.info("Saving Hotline Report Exception: " + ex.getMessage());
                	ex.printStackTrace();
                	throw ex;
                }
                hotlineReportAttachment.setFileType("hotline report photo");
                hotlineReportAttachment.setWarrantyHotlineReport(warrantyHotlineReport);
                
                //Saving the attachment
                attachmentRepo.save(hotlineReportAttachment);
            });
        }
        
        apiResponse.setResult("Submitted");
        
        return apiResponse;
	}


	@Override
	public ApiResponse<?> findHotlineByHtlrNo(String hotlineNo) {
		ApiResponse<WarrantyHotlineReportViewDto> apiResponse = new ApiResponse<>();
		
		WarrantyHotlineReportViewDto dto = waHotlineReportRepo.findByHtlrNo(hotlineNo);
		apiResponse.setResult(dto);
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> searchHotlineNo(String hotlineNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
		
		apiResponse.setResult(waHotlineReportRepo.searchHotlineNo(hotlineNo));
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> dealerDepoList() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.dealerDepoList());
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getFailureCode() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getFailureCode());
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getHotlineStatus() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getHotlineStatus());
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getHotlinePlant() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getHotlinePlant());
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getHoDepartment() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getHoDepartment());
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getChassisNo(String chassisNo) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getChassisNo(chassisNo));
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> getDepartmentIncharge(Long deptId) {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getDepartmentIncharge(deptId));
		
		return apiResponse;
	}


	@Override
	public ApiResponse<?> searchHotlineReport(WarrantyHotlineReportSearchDto reportSearchDto) {
		ApiResponse<List<WarrantyHotlineSearchResponceDto>> apiResponse = new ApiResponse<>();
		
		List<WarrantyHotlineSearchResponceDto> responceDtoList = waHotlineReportRepo.searchHotlineReport(
				reportSearchDto.getHotlineNo(), reportSearchDto.getStatus(), reportSearchDto.getFromDate(),
				reportSearchDto.getToDate(), reportSearchDto.getPage(), reportSearchDto.getSize()
				);

		Long count = 0l;
		if (responceDtoList != null && !responceDtoList.isEmpty()) {
			count = responceDtoList.get(0).getTotalCount();
		}
		
		apiResponse.setCount(count);
		apiResponse.setResult(responceDtoList);
		
		return apiResponse;
	}


	@Override
	public void printWarrantyHotlineReport(String hotlineId, String printStatus, String filePath,
			OutputStream outputStream) throws Exception
	{
    	String jasperfile = filePath + "WarrantyHotlineReport.jasper";
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("campaignId", hotlineId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	
		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
	}


	@Override
	public ApiResponse<?> getFailureType() {
		ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();

		apiResponse.setResult(waHotlineReportRepo.getFailureType());
		
		return apiResponse;
	}
	
}
