package com.i4o.dms.kubota.service.activityclaim.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.service.activityclaim.dto.DealerInvoiceSearchResponseDto;
import com.i4o.dms.kubota.service.activityclaim.model.DealerInvoiceSearchRequestModel;
import com.i4o.dms.kubota.service.activityclaim.repository.ServiceClaimInvoiceRepo;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobCard;
import com.i4o.dms.kubota.service.jobcard.domain.ServiceJobcardPhotos;
import com.i4o.dms.kubota.spares.partrequisition.domain.SparePartRequisitionItem;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/activityClaim")
public class ServiceClaimInvoiceController {
    @Autowired
    private ServiceClaimInvoiceRepo serviceClaimInvoiceRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private StorageService storageService;
    
    @GetMapping("/generateDealerInvoice")
    public ResponseEntity<?> generateDealerInvoice(@RequestParam Long claimId, @RequestParam String invoiceType){
    	 ApiResponse apiResponse = new ApiResponse();
         Map<String, Object> result = serviceClaimInvoiceRepo
                 .generateDealerInvoice(claimId, invoiceType, userAuthentication.getUsername());
         apiResponse.setMessage("Dealer Invoice Generation For "+invoiceType);
         apiResponse.setResult(result);
         return ResponseEntity.ok(apiResponse);
    }
    
    @PostMapping("/getDealerInvoiceList")
    public ResponseEntity<?> getDealerInvoiceList(@RequestBody DealerInvoiceSearchRequestModel requestModel ){
    	 ApiResponse apiResponse = new ApiResponse();
         List<DealerInvoiceSearchResponseDto> result = serviceClaimInvoiceRepo
                 .getDealerInvoiceList(requestModel.getInvoiceNumber(), 
                		 requestModel.getClaimNumber(),
                		 requestModel.getInvoiceType(), 
                		 requestModel.getFromDate(),
                		 requestModel.getToDate(),
                		 userAuthentication.getUsername(),
                		 requestModel.getDealerCode(),
                		 requestModel.getPage(),
                		 requestModel.getSize(),
                		 requestModel.getOrgHierarchyId(),
                		 requestModel.getActivityNumber());
         Long count=0l;
         if(result!=null && result.size()>0){
        	 count = result.get(0).getRecordCount();
         }
         apiResponse.setCount(count);
         apiResponse.setMessage("Get Dealer Invoice List");
         apiResponse.setResult(result);
         return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/viewDealerInvoice")
    public ResponseEntity<?> viewDealerInvoice(@RequestParam Long invoiceId){
    	 ApiResponse apiResponse = new ApiResponse();
         Map<String, Object> header = serviceClaimInvoiceRepo.getInvoiceHeaderDetails(invoiceId);
         List<Map<String, Object>> details = serviceClaimInvoiceRepo.getInvoiceItemDetails(invoiceId);
         
         Map result = new HashMap<>();
         result.put("Header", header);
         result.put("Details", details);
         
         apiResponse.setMessage("Dealer Invoice Fetched Succesfully");
         apiResponse.setResult(result);
         return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/dealerInvoiceNumberSearch")
    public ResponseEntity<?> viewDealerInvoice(@RequestParam String searchVal, @RequestParam String invoiceType){
   	 ApiResponse apiResponse = new ApiResponse();
   	 String userCode = userAuthentication.getUsername();
     List<Map<String, Object>> result = serviceClaimInvoiceRepo.getInvoiceNumberSearch(searchVal, invoiceType, userCode);
     apiResponse.setMessage("Dealer Invoice Number Fetched Succesfully");
     apiResponse.setResult(result);
     return ResponseEntity.ok(apiResponse);
   }
    
    @GetMapping(value="/invoiceVerification")
    public ResponseEntity<?> invoiceVerification(@RequestParam Integer invoiceId){
      	ApiResponse apiResponse = new ApiResponse();
      	String userCode = userAuthentication.getUsername();
        serviceClaimInvoiceRepo.invoiceStatusUpdate(invoiceId, userAuthentication.getLoginId(), "Invoice Verified");
        apiResponse.setMessage("Dealer Invoice Verified Succesfully");
        
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value="/invoiceDownload")
    public void invoiceDownload(@RequestParam Long id, HttpServletResponse response) throws IOException{
    	String filename = serviceClaimInvoiceRepo.getInvoiceFileName(id);
    	ServletOutputStream outputStream;
    	if(filename!=null){
	    	Resource file = storageService.loadAsResource(filename);
	    	InputStream is = file.getInputStream();
	    	response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + filename);
            response.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
			outputStream = response.getOutputStream();
			IOUtils.copy(is, outputStream);
			outputStream.flush();
			is.close();
	    }
    }
    
    @PostMapping(value = "/invoiceUpload", consumes = { "multipart/form-data" })
	public ResponseEntity<?> invoiceUpload(@RequestPart(value = "invoiceData") Map<String,Object> invoiceData,
			@RequestPart(value = "attachedFile", required = false) MultipartFile attachedFile) {
		ApiResponse apiResponse = new ApiResponse();
		
		if (attachedFile != null) {
			Integer invoiceId = (Integer) invoiceData.get("invoiceId");
			String invoiceFileName = attachedFile.getOriginalFilename().replaceAll(" ", "_");
			String fileType = attachedFile.getContentType();
			String photoName = "DealerInvoice_" + System.currentTimeMillis() + "_" + invoiceFileName;
			try{
				serviceClaimInvoiceRepo.uploadInvoice(photoName, fileType, invoiceId, userAuthentication.getLoginId());
				serviceClaimInvoiceRepo.invoiceStatusUpdate(invoiceId, userAuthentication.getLoginId(), "Invoice Uploaded");
				storageService.store(attachedFile, photoName);
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setMessage("Invoice Copy uploaded Succesfully");
			}catch(Exception ex){
				ex.printStackTrace();
			apiResponse.setMessage("Error while uploading Invoice Copy");
			}
		}else{
			apiResponse.setMessage("Invoice Copy not found");
		}
	    return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/claimNumberSearch")
    public ResponseEntity<?> claimNumberSearch(@RequestParam String searchVal, @RequestParam String invoiceType){
   	 ApiResponse apiResponse = new ApiResponse();
   	 String userCode = userAuthentication.getUsername();
     List<Map<String, Object>> result = serviceClaimInvoiceRepo.getClaimNumberSearch(searchVal, invoiceType, userCode);
     apiResponse.setMessage("Claim Number Fetched Succesfully");
     apiResponse.setResult(result);
     return ResponseEntity.ok(apiResponse);
   }
    
    
    @GetMapping("/activityNumberSearch")
    public ResponseEntity<?> activityNumberSearch(@RequestParam String searchVal, @RequestParam String invoiceType){
   	 ApiResponse apiResponse = new ApiResponse();
   	 String userCode = userAuthentication.getUsername();
     List<Map<String, Object>> result = serviceClaimInvoiceRepo.getActivityNumberSearch(searchVal, invoiceType, userCode);
     apiResponse.setMessage("Claim Number Fetched Succesfully");
     apiResponse.setResult(result);
     return ResponseEntity.ok(apiResponse);
   }
    
    /**
     * @author suraj.gaur
     * @param invoiceData
     * @param attachedFile
     * @return ResponseEntity<?> 
     */
    @PostMapping(value = "/dealerInvoiceUpload", consumes = { "multipart/form-data" })
	public ResponseEntity<?> dealerInvoiceUpload(@RequestPart(value = "invoiceData") Map<String,Object> invoiceData,
			@RequestPart(value = "attachedFile", required = false) MultipartFile attachedFile) {
		ApiResponse apiResponse = new ApiResponse();
		
		if (attachedFile != null) {
			Integer invoiceId = (Integer) invoiceData.get("invoiceId");
			String invoiceNo = (String) invoiceData.get("invoiceNo");
   			String invoiceDateString = (String) invoiceData.get("invoiceDate");
   			
   			// Check if invoiceNo and invoiceDate are not null or empty
   		    if (invoiceNo == null || invoiceNo.trim().isEmpty() || invoiceDateString == null || invoiceDateString.trim().isEmpty()) {
   		        apiResponse.setMessage("invoiceNo and invoiceDate must not be null or empty.");
   		        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
   		        return ResponseEntity.badRequest().body(apiResponse);
   		    }
   		    
   		    Date invoiceDate = null;
			try {
				invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(invoiceDateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			String invoiceFileName = attachedFile.getOriginalFilename().replaceAll(" ", "_");
			String fileType = attachedFile.getContentType();
			String photoName = "DealerInvoice_" + System.currentTimeMillis() + "_" + invoiceFileName;
			try{
				serviceClaimInvoiceRepo.dealerUploadInvoice(photoName, fileType, invoiceId, userAuthentication.getLoginId(), invoiceNo, invoiceDate);
				serviceClaimInvoiceRepo.invoiceStatusUpdate(invoiceId, userAuthentication.getLoginId(), "Invoice Uploaded");
				storageService.store(attachedFile, photoName);
				apiResponse.setStatus(HttpStatus.OK.value());
				apiResponse.setMessage("Invoice Copy uploaded Succesfully");
			}catch(Exception ex){
				ex.printStackTrace();
				apiResponse.setMessage("Error While uploading Invoice Copy");
			}
		}else{
			apiResponse.setMessage("Invoice Copy not found");
		}
	    return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @param invoiceData
     * @return
     */
    @PostMapping(value="/kaiInvoiceVerify")
    public ResponseEntity<?> kaiInvoiceVerify(@RequestBody Map<String,Object> invoiceData){
      	ApiResponse apiResponse = new ApiResponse();
      	
      	Integer invoiceId = (Integer) invoiceData.get("invoiceId");
		String invoiceNo = (String) invoiceData.get("invoiceNo");
		String invoiceDateString = (String) invoiceData.get("invoiceDate");
		
		if (invoiceNo == null || invoiceNo.trim().isEmpty() || invoiceDateString == null
				|| invoiceDateString.trim().isEmpty()) {
			apiResponse.setMessage("invoiceNo and invoiceDate must not be null or empty.");
			apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(apiResponse);
		}

		Date invoiceDate = null;
		try {
			invoiceDate = new SimpleDateFormat("dd-MM-yyyy").parse(invoiceDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
      	
		try{
			serviceClaimInvoiceRepo.kaiUpdateInvoiceNoDate(invoiceId, userAuthentication.getLoginId(), invoiceNo, invoiceDate);
	        serviceClaimInvoiceRepo.invoiceStatusUpdate(invoiceId, userAuthentication.getLoginId(), "Invoice Verified");
			apiResponse.setStatus(HttpStatus.OK.value());
			apiResponse.setMessage("Dealer Invoice Verified Succesfully");
		}catch(Exception ex){
			ex.printStackTrace();
			apiResponse.setMessage("Error While Verifying Invoice Copy");
		}
        
        return ResponseEntity.ok(apiResponse);
    }
    
    /**
     * @author suraj.gaur
     * @param invoiceId
     * @return
     */
    @GetMapping(value="/getInvoiceDetail")
    public ResponseEntity<?> getInvoiceDetail(@RequestParam Long invoiceId) {
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setResult(serviceClaimInvoiceRepo.getInvoiceDetail(invoiceId));
        apiResponse.setMessage("Get Invoice No and Invoice Date successfull!");
        return ResponseEntity.ok(apiResponse);

    }
    
}
