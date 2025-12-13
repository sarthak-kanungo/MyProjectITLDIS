package com.i4o.dms.itldis.service.activityreport.controller;


import com.i4o.dms.itldis.common.service.JasperPrintService;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.service.activityreport.domain.ServiceActivityReport;
import com.i4o.dms.itldis.service.activityreport.domain.ServiceActivityReportPhotos;
import com.i4o.dms.itldis.service.activityreport.dto.ActivityResponseSearchDto;
import com.i4o.dms.itldis.service.activityreport.dto.ActivitySearchResponse;
import com.i4o.dms.itldis.service.activityreport.dto.ServiceActivityReportViewDto;
import com.i4o.dms.itldis.service.activityreport.repository.ServiceActivityReportPhotoRepo;
import com.i4o.dms.itldis.service.activityreport.repository.ServiceActivityReportRepo;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;

import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/activityReport")
public class ServiceActivityReportController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceActivityReportController.class);

    @Autowired
    private ServiceActivityReportRepo serviceActivityReportRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private StorageService storageService;

    @Autowired
    private ServiceActivityReportPhotoRepo serviceActivityReportPhotoRepo;
    
    @Autowired
    private JasperPrintService jasperPrintService;

    @PostMapping("/saveServiceActivityReport")
    public ResponseEntity<?> saveServiceActivityReport(@RequestPart ServiceActivityReport serviceActivityReport,
                                                                     List<MultipartFile> multipartFileList)
    {
        serviceActivityReport.setCreatedBy(userAuthentication.getLoginId());
        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
        serviceActivityReport.setDealerMaster(dealerMaster);
        ServiceActivityReport serviceActivityReport1 = serviceActivityReportRepo.save(serviceActivityReport);

        if (!multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                ServiceActivityReportPhotos serviceActivityReportPhotos = new ServiceActivityReportPhotos();
                String reportPhotos = m.getOriginalFilename();
                String photoName = "service_activity_report_photos" + System.currentTimeMillis() + "_" + reportPhotos;
                storageService.store(m, photoName);
                serviceActivityReportPhotos.setFileName(photoName);
                serviceActivityReportPhotos.setServiceActivityReport(serviceActivityReport1);
                serviceActivityReportPhotoRepo.save(serviceActivityReportPhotos);
            });

        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service activity Report save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok(apiResponse);
    }

 @GetMapping("/activityNumberAuto")
 public ResponseEntity<?> activityNumberAuto(@RequestParam("activityNumber") String activityNumber)
 {
    ApiResponse apiResponse=new ApiResponse();
    apiResponse.setMessage("activity number get successfully");
    apiResponse.setStatus(HttpStatus.OK.value());
     
     List<Map<String,Object>> activityNo = serviceActivityReportRepo.findByActivityNumberContaining(activityNumber,userAuthentication.getDealerId());
     apiResponse.setMessage("Create Service Activity Report");
     apiResponse.setResult(activityNo);

     return ResponseEntity.ok(apiResponse);
 }

    @GetMapping("/getHeaderDetails")
    public ResponseEntity<?> getHeaderDetails(@RequestParam("activityProposalId") Long activityProposalId)
    {
        ApiResponse apiResponse=new ApiResponse();
        Map<String,Object> activityId = serviceActivityReportRepo.getHeaderDetails(userAuthentication.getDealerId(),activityProposalId);
        apiResponse.setMessage("header Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getJobCardDetails")
    public ResponseEntity<?> getJobCardDetails(@RequestParam("activityProposalId") Long activityProposalId,
    		@RequestParam("fromDate")String fromDate,
    		@RequestParam("toDate")String toDate)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> activityId = serviceActivityReportRepo.getJobCardDetails(userAuthentication.getDealerId(),activityProposalId, fromDate, toDate);
        apiResponse.setMessage("Job Card Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getMachineDetails")
    public ResponseEntity<?> getMachineDetails(@RequestParam("activityProposalId") Long activityProposalId,
    		@RequestParam("fromDate")String fromDate,
    		@RequestParam("toDate")String toDate)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> activityId = serviceActivityReportRepo.getMachineDetails(userAuthentication.getDealerId(),activityProposalId, fromDate, toDate);
        apiResponse.setMessage("Machine Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityId);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getServiceDetails")
    public ResponseEntity<?> getServiceDetails(@RequestParam("activityProposalId") Long activityProposalId,
    		@RequestParam("fromDate")String fromDate,
    		@RequestParam("toDate")String toDate)
    {
        ApiResponse apiResponse=new ApiResponse();
        List<Map<String,Object>> activityId = serviceActivityReportRepo.getServiceDetails(userAuthentication.getDealerId(),activityProposalId, fromDate, toDate);
        apiResponse.setMessage("Service Details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityId);
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping(value = "/serviceActivityReportSearch")
    public ResponseEntity<?>serviceActivityReportSearch(@RequestBody ActivityResponseSearchDto activityResponseSearchDto){
        ApiResponse apiResponse=new ApiResponse();
        
        List<ActivitySearchResponse> list = serviceActivityReportRepo.searchActivityReport(
                activityResponseSearchDto.getFromDate(),
                activityResponseSearchDto.getToDate(),
                activityResponseSearchDto.getActivityNumber(),
                activityResponseSearchDto.getActivityStatus(),
                activityResponseSearchDto.getActivityType(),
                userAuthentication.getDealerId(),
                userAuthentication.getDealerEmployeeId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getManagementAccess(),
                activityResponseSearchDto.getPage(),
                activityResponseSearchDto.getSize(),
                userAuthentication.getUsername(),
                activityResponseSearchDto.getOrgHierId()		//Suraj_02-11-2023
                );
        Long count = 0l;
        if(list!=null && list.size()>0){
        	count = list.get(0).getRecordCount();
        }
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setMessage("Service Activity Report Search List");
        apiResponse.setResult(list);
        apiResponse.setCount(count);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping(value = "/getActivityReportById/{id}")
    public ResponseEntity<?> getServiceMrcById(@PathVariable Long id) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Report by id details");

        Map<String, Object> header = serviceActivityReportRepo.getHeaderDataForView(id);
        List<Map<String, Object>> MachineList = serviceActivityReportRepo.getMachineDataForView(id);
        List<Map<String, Object>> serviceList = serviceActivityReportRepo.getServiceDataForView(id);
        List<Map<String, Object>> jobCard = serviceActivityReportRepo.getJobCardDataForView(id);
        List<Map<String, Object>> photoList = serviceActivityReportRepo.getPhotoForView(id);

        ServiceActivityReportViewDto serviceActivityReportViewDto = new ServiceActivityReportViewDto();

        serviceActivityReportViewDto.setActivityJobCardReport(jobCard);
        serviceActivityReportViewDto.setActivityMachineReport(MachineList);
        serviceActivityReportViewDto.setActivityReportHeaderData(header);
        serviceActivityReportViewDto.setActivityServiceReport(serviceList);
        serviceActivityReportViewDto.setActivityReportPhotoList(photoList);

        apiResponse.setResult(serviceActivityReportViewDto);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getActivityNumberForSearch")
    public ResponseEntity<?> getActivityNumberForSearch(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Service Activity Proposal Number get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult((serviceActivityReportRepo.getActivityNumberForSearch(userAuthentication.getDealerId(),activityNumber)));
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * @author Suraj_Gaur
     * @apiNote It will print the PDF for Service Activity Report. A separate code is written by me in common JasperPrintService class for using JASPER library to build report.
     * @param jobCardNo
     * @param printStatus
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/printServiceActivityReport")
    public void printServiceActivityReport(
    		@RequestParam String activityReportId,
    		@RequestParam String activityReportNo,
    		@RequestParam String printStatus, 
    		HttpServletRequest request,
    		HttpServletResponse response)
    {
    	String filePath = request.getServletContext().getRealPath("/WEB-INF/reports/");
    	String jasperfile = filePath + "ServiceActivityReport.jasper";
    	OutputStream outputStream = null;
    	
    	HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
		jasperParameter.put("activityReportId", activityReportId);
    	
		JasperPrint jasperPrint = jasperPrintService.getJasperPrint(jasperfile, jasperParameter);
    	try {
    		response.setContentType("application/pdf");
    		response.setHeader("Content-Disposition", "inline; filename=" + activityReportNo + ".pdf");
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
    
    /**
     * @author suraj.gaur
     * @param reportId
     * @return
     */
    @GetMapping("/getReportImagesByProposalId")
    public ResponseEntity<?> getReportImagesByProposalId(@RequestParam Long proposalId) {
    	try {
    		
    		ApiResponse<List<Map<String,Object>>> apiResponse = new ApiResponse<>();
    		List<Map<String,Object>> list = serviceActivityReportRepo.getReportImagesByReportId(proposalId);
    		
    		apiResponse.setResult(list);
    		apiResponse.setMessage("Service Activity Report Images get Successfully!");
            apiResponse.setStatus(HttpStatus.OK.value());
            
            return ResponseEntity.ok(apiResponse);
			
		} catch (Exception e) {
			logger.info("/getReportImagesByProposalId Exception: " + e.getMessage());
			e.printStackTrace();
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setResult("There is some error occured !!" + e.getMessage());
			apiResponse.setMessage(e.getMessage());
			apiResponse.setStatus(HttpStatus.FORBIDDEN.value());
			
			return ResponseEntity.badRequest().body(apiResponse);
		}
    }
    
}
