package com.i4o.dms.itldis.warranty.hotlinereport.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.excelmanager.exception.InvalidColumnException;
import com.i4o.dms.itldis.warranty.hotlinereport.domain.WarrantyHotlineReport;
import com.i4o.dms.itldis.warranty.hotlinereport.dto.WarrantyHotlineReportSearchDto;

/**
 * @author suraj.gaur
 */
@Transactional
public interface WarrantyHotlineReportService {
	
	ApiResponse<?> submitHotlineReport(WarrantyHotlineReport hotlineReport, List<MultipartFile> multipartFileList)
			throws InvalidColumnException, IOException;
	
	ApiResponse<?> findHotlineByHtlrNo(String hotlineNo);
	
	ApiResponse<?> searchHotlineNo(String hotlineNo);
	
	ApiResponse<?> dealerDepoList();
	
	ApiResponse<?> getFailureCode();
	
	ApiResponse<?> getHotlineStatus();
	
	ApiResponse<?> getHotlinePlant();
	
	ApiResponse<?> getHoDepartment();
	
	ApiResponse<?> getChassisNo(String chassisNo);
	
	ApiResponse<?> getDepartmentIncharge(Long deptId);
	
	ApiResponse<?> searchHotlineReport(WarrantyHotlineReportSearchDto reportSearchDto);
	
	void printWarrantyHotlineReport(String hotlineId, String printStatus, String filePath, OutputStream outputStream) 
			throws Exception;
	
	ApiResponse<?> getFailureType();
	
}
