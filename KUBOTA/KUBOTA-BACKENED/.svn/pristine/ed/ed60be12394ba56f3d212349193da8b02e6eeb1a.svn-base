package com.i4o.dms.kubota.warranty.retrofitmentcampaign.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.common.service.JasperPrintService;
import com.i4o.dms.kubota.masters.usermanagement.kubotausers.repository.KubotaEmployeeRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.domain.WarrantyRetrofitmentCampaign;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.dto.ChassisDetails;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.dto.WarrantyRetrofitmentCampaignResponseDto;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.dto.WarrantyRetrofitmentCampaignSearchDto;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.repository.WarrantyRetrofitmentCampaignRepo;
import com.i4o.dms.kubota.warranty.retrofitmentcampaign.service.WarrantyRetrofitmentCampaignService;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@Slf4j
@RequestMapping(value = "/api/warranty/retrofitmentcampaign")
public class WarrantyRetrofitmentCampaignController {

    @Autowired
    private WarrantyRetrofitmentCampaignService warrantyRetrofitmentCampaignService;

    @Autowired
    private WarrantyRetrofitmentCampaignRepo warrantyRetrofitmentCampaignRepo;
    
    @Autowired
    private JasperPrintService jasperPrintService;


    @PostMapping(value = "/uploadExcel")
    public ResponseEntity<?> uploadExcel(@RequestParam MultipartFile multipartFile) throws Exception {
    	List<ChassisDetails> chassisDetails = new ArrayList<>();
		ApiResponse apiResponse = new ApiResponse();
		warrantyRetrofitmentCampaignService.saveExcelChassisDetail(multipartFile);
		// apiResponse.setResult(chassisDetails);
		apiResponse.setMessage("get successfully");
		return ResponseEntity.ok(apiResponse);
    }

    /**
     * @author suraj.gaur
     * @param warrantyRetrofitmentCampaign
     * @param multipartFile
     * @param multipartFileList
     * @return ResponseEntity<?>
     */
    @PostMapping(value = "/saveRetrofitmentCampaign")
	public ResponseEntity<?> saveRetrofitmentCampaign(
			@RequestPart(value = "warrantyRetrofitmentCampaign") WarrantyRetrofitmentCampaign warrantyRetrofitmentCampaign,
			@RequestPart(value = "multipartFile") MultipartFile multipartFile,
			@RequestPart(value = "multipartFileList") List<MultipartFile> multipartFileList) 
    {
		ApiResponse apiResponse = new ApiResponse();
        try {
        	apiResponse =  warrantyRetrofitmentCampaignService.saveRetrofitmentCampaign(warrantyRetrofitmentCampaign, multipartFile, multipartFileList);
			apiResponse.setMessage("Retrofitment Campaign has been Saved Successfully");
			apiResponse.setStatus(HttpStatus.OK.value());
        }catch (Exception e){
            apiResponse.setResult(e.getStackTrace());
            apiResponse.setMessage("Retrofitment Campaign did not Saved");
            apiResponse.setStatus(HttpStatus.CONFLICT.value());
        }

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "searchRetrofitmentCampaign")
    public ResponseEntity<?> searchRetrofitmentCampaign(@RequestBody WarrantyRetrofitmentCampaignSearchDto campaignSearchDto)  {
		List<ChassisDetails> chassisDetails = new ArrayList<>();
		ApiResponse apiResponse = new ApiResponse();
		List<WarrantyRetrofitmentCampaignResponseDto> warrantyRetrofitmentCampaignResponseDtoList = warrantyRetrofitmentCampaignRepo
				.searchWarrantyRetrofitmentCampaign(campaignSearchDto.getRfcNo(), campaignSearchDto.getStatus(),
						campaignSearchDto.getFromDate(), campaignSearchDto.getToDate(), campaignSearchDto.getPage(),
						campaignSearchDto.getSize());

		Long count = 0l;
		if (warrantyRetrofitmentCampaignResponseDtoList != null
				&& !warrantyRetrofitmentCampaignResponseDtoList.isEmpty()) {
			count = warrantyRetrofitmentCampaignResponseDtoList.get(0).getTotalCount();
		}
		apiResponse.setCount(count);

		//Long count=warrantyRetrofitmentCampaignRepo.searchWarrantyRetrofitmentCampaignCount(campaignSearchDto.getRfcNo(),campaignSearchDto.getStatus(),campaignSearchDto.getFromDate(),campaignSearchDto.getToDate(),campaignSearchDto.getPage(),campaignSearchDto.getSize());
		apiResponse.setResult(warrantyRetrofitmentCampaignResponseDtoList);
		apiResponse.setCount(count);
		return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "viewRetrofitmentCampaign")
    public ResponseEntity<?> viewRetrofitmentCampaign(@RequestParam String retrofitmentNo)  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyRetrofitmentCampaignRepo.findByRetrofitmentNo(retrofitmentNo));
        apiResponse.setMessage("view retro fitment ");
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "searchRetrofitmentStatus")
    public ResponseEntity<?> searchRetrofitmentStatus()  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyRetrofitmentCampaignRepo.searchRetrofitmentStatus());
        apiResponse.setMessage("search retro fitment status");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "searchRetrofitmentNo")
    public ResponseEntity<?> searchRetrofitmentNo(@RequestParam String retrofitmentNo)  {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyRetrofitmentCampaignRepo.searchRetrofitmentNo(retrofitmentNo));
        apiResponse.setMessage("search retro fitment number");
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     */
    @GetMapping(value = "getAutoCompleteJobCode")
    public ResponseEntity<?> getAutoCompleteJobCode(@RequestParam String jobCodeNo) {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setResult(warrantyRetrofitmentCampaignRepo.getAutoCompleteJobCode(jobCodeNo));
        apiResponse.setMessage("Auto Completed JobCard No.");
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author Suraj_Gaur
     * @apiNote It will print the PDF for Warranty Retrofitment Campaign. A separate code is written by me in common JasperPrintService class for using JASPER library to build report.
     * @param jobCardNo
     * @param printStatus
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/printWarrantyRetrofitmentReport")
    public void printWarrantyRetrofitmentReport(
    		@RequestParam String campaignId,
    		@RequestParam String campaignNo,
    		@RequestParam String printStatus, 
    		HttpServletRequest request,
    		HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	String jasperfile = filePath + "WarrantyRetrofitmentCampaignReport.jasper";
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("campaignId", campaignId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" 
    				+ campaignNo.replace('/', '-') + "-" + ThreadLocalRandom.current().nextInt(1000) + "-" 
    				+ System.currentTimeMillis() + ".pdf");
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
    		outputStream = response.getOutputStream();
    		jasperPrintService.printPdfReport(jasperPrint, printStatus, outputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream!=null){
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					System.err.println("There is an Error flushing and closing the outStream object");
					e.printStackTrace();
				}
            }
		}
    }
    
}
