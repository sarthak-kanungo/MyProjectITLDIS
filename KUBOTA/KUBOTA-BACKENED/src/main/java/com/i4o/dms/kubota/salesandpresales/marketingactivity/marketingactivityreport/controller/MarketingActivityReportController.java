package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReport;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReportImages;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto.ReportViewResponse;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto.SearchDto;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.dto.SearchResponseMarketingActivityReportDto;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.repository.MarketingActivityReportPhotoRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.repository.MarketingActivityReportRepo;
import com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityreport.service.MarketingActivityReportService;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.storage.StorageService;
import com.i4o.dms.kubota.utils.ApiResponse;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/marketingactivityreport")
public class MarketingActivityReportController {
    private static final Logger logger = LoggerFactory.getLogger(MarketingActivityReportController.class);

    @Autowired
    private MarketingActivityReportRepo marketingActivityReportRepo;

    @Autowired
    private MarketingActivityReportService marketingActivityReportService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private EnquiryRepo enquiryRepo;

    @Autowired
    private MarketingActivityProposalRepo marketingActivityProposalRepo;

    @Autowired
    private DealerMasterRepo dealerMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;

    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;

    @Autowired
    private MarketingActivityReportPhotoRepo marketingActivityReportPhotoRepo;

//    @Transactional
//    @PostMapping(value = "/savereport", consumes = {"multipart/form-data"})
//    public ResponseEntity<?> savereport(@RequestPart(value = "marketingActivityReport") MarketingActivityReport marketingActivityReport,
//                                        @RequestPart(value = "marketingActivityReportImages", required = false) List<MultipartFile> marketingActivityReportImages) {
//        System.out.println("mar:" + marketingActivityReport.toString());
//       if (marketingActivityReportImages.size()<=5 && marketingActivityReportImages.size()>0) {
//           if (marketingActivityReportRepo
//                   .findByMarketingActivityProposalActivityProposalId(marketingActivityReport.getMarketingActivityProposalId()) == null) {
//               List<MarketingActivityReportImages> reportImages = new ArrayList<>();
//               marketingActivityReport.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
//               marketingActivityReport.setDealerEmployeeMaster(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
//               MarketingActivityProposal proposal = marketingActivityProposalRepo
//                       .getOne(marketingActivityReport.getMarketingActivityProposalId());
//               marketingActivityReport.setMarketingActivityProposal(proposal);
//               MarketingActivityReport report = marketingActivityReportRepo.save(marketingActivityReport);
//               marketingActivityReportImages.forEach(m -> {
//                   MarketingActivityReportImages reportImage = new MarketingActivityReportImages();
//                   String photoName = m.getOriginalFilename();
//                   String name = "activity_report_" + System.currentTimeMillis() + "_" + photoName;
//                   storageService.store(m, name);
//                   reportImage.setMarketingActivityReport(report);
//                   reportImage.setPhotoPath(name);
//                   reportImages.add(reportImage);
//               });
//
//               System.out.println("marketing activity proposal:==========>" + proposal.toString());
//
//               marketingActivityReport.setActivityReportEnquiryDetails(marketingActivityReportService.getEntityDetails(
//                       proposal.getActivityNumber(), report
//               ));
//               report.setMarketingActivityReportImages(reportImages);
//               marketingActivityReportRepo.save(report);
//               ApiResponse apiResponse = new ApiResponse();
//               apiResponse.setMessage("marketing activity report successfully saved.");
//               apiResponse.setStatus(HttpStatus.OK.value());
//               return ResponseEntity.ok(apiResponse);
//           } else {
//               ApiResponse apiResponse = new ApiResponse();
//               apiResponse.setMessage("marketing activity proposal already exist.");
//               apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//               return ResponseEntity.ok(apiResponse);
//           }
//       }
//       else {
//           ApiResponse apiResponse = new ApiResponse();
//           apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
//           if (marketingActivityReportImages.size()>5) {
//               apiResponse.setMessage("Documents exceed the limit(limit 5).");
//           }
//           else {
//               apiResponse.setMessage("Documents should not be empty.");
//           }
//           return ResponseEntity.ok(apiResponse);
//       }
//    }

//    //Wrong Stored Procedure
//    @GetMapping(value = "/searchactivitynumber")
//    public ResponseEntity<?> searchActivityNumber(@RequestParam("activityNumber") String activityNumber) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("activity number get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityReportRepo.getActivityNumber(activityNumber));
//        return ResponseEntity.ok(apiResponse);
//    }

    //Same Service
    @GetMapping(value = "/searchActivityNumberForReport")
    public ResponseEntity<?> searchActivityNumberForReport(@RequestParam("activityNumber") String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity number get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(marketingActivityReportRepo.searchActivityNumberForReport(activityNumber,userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @SuppressWarnings("unchecked")
	@PostMapping(value = "/searchBy")
    public ResponseEntity<?> searchBy(@RequestBody SearchDto searchDto) {
    	System.out.println("searchDto : "+searchDto.getHierId());
        ApiResponse apiResponse = new ApiResponse();
        
        List<SearchResponseMarketingActivityReportDto> result = marketingActivityReportRepo.searchBy(
                userAuthentication.getManagementAccess(),
                userAuthentication.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                searchDto.getActivityNumber(),
                searchDto.getActivityType(),
                searchDto.getFromDate(),
                searchDto.getToDate(),
                searchDto.getPage(),
                searchDto.getSize(),
                userAuthentication.getUsername(),
                'N',
                searchDto.getHierId()
        );
        
        apiResponse.setMessage("marketing activity report get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        Long count = 0L;
        if(result!=null && result.size()>0){
        	count = result.get(0).getTotalCount();
        }
        apiResponse.setCount(count);
        /*apiResponse.setCount(marketingActivityReportRepo.searchByCount(userAuthentication.getManagementAccess(),
                userAuthentication.getDealerId(),
                userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(),
                searchDto.getActivityNumber(),
                searchDto.getActivityType(),
                searchDto.getActivityStatus(),
                searchDto.getFromDate(),
                searchDto.getToDate(),
                searchDto.getPage(),
                searchDto.getSize()
        ));*/
        return ResponseEntity.ok(apiResponse);
    }

    //In Use Correct
    @GetMapping(value = "/activityReportById/{reportId}")
    public ResponseEntity<?> activityReportById(@PathVariable("reportId") Long reportId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("activity report get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        ReportViewResponse response = marketingActivityReportRepo.findByReportId(reportId);
        if(response.getMarketingActivityProposal()!=null){
        	System.out.println(response.getMarketingActivityProposal().getActivityType());
        	String activityType = marketingActivityReportRepo.getActivityTypeName(Long.valueOf(response.getMarketingActivityProposal().getActivityType()));
        	response.getMarketingActivityProposal().setActivityTypeName(activityType);
        }
        apiResponse.setResult(response);
        
        return ResponseEntity.ok(apiResponse);
    }

    //Not Used Yet
//    @GetMapping("/getEnquiryByActivityNumber")
//    public ResponseEntity<?> getEnquiryByActivityNumber(@RequestParam String activityNumber) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("enquiry get successfully");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(marketingActivityReportRepo.getEnquiryByActivityNumber(activityNumber));
//        return ResponseEntity.ok(apiResponse);
//    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    @PostMapping("/saveMarketingActivityReport")
    public ResponseEntity<?> saveMarketingActivityReport(@RequestPart MarketingActivityReport marketingActivityReport,
                                                       List<MultipartFile> multipartFileList)
    {
//        DealerEmployeeMaster dealerEmployeeMaster = dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId());
//        marketingActivityReport.setDealerEmployeeMaster(dealerEmployeeMaster);
//        DealerMaster dealerMaster = dealerMasterRepo.getOne(userAuthentication.getDealerId());
//        marketingActivityReport.setDealerMaster(dealerMaster);
       // marketingActivityReportRepo.save(marketingActivityReport);
        marketingActivityReport.setCreatedBy(userAuthentication.getLoginId()) ;
        marketingActivityReport.setDealerId(userAuthentication.getDealerId()) ;
        MarketingActivityReport marketingActivityReport1 = marketingActivityReportRepo.save(marketingActivityReport);
      
        if (!multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
                MarketingActivityReportImages marketingActivityReportImages = new MarketingActivityReportImages();
                String reportPhotos = m.getOriginalFilename();
                String photoName = "marketing_activity_report_photos" + System.currentTimeMillis() + "_" + reportPhotos;
                storageService.store(m, photoName);
                marketingActivityReportImages.setFileName(photoName);
                marketingActivityReportImages.setMarketingActivityReport(marketingActivityReport1);
                marketingActivityReportPhotoRepo.save(marketingActivityReportImages);
            });
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Marketing activity Report save successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }



    @GetMapping("/getActivityNumberForReport")
    public ResponseEntity<?> getActivityNumberForReport(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity Number Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());

        Map<String, Object> map = marketingActivityReportRepo.checkActivityNumber(activityNumber);
        if (map.get("result").equals(false))
        {
            apiResponse.setResult(marketingActivityReportRepo.getActivityNumberForReport(activityNumber,userAuthentication.getDealerId()));
            apiResponse.setMessage("Create Marketing Activity Report");

        } else {
            apiResponse.setResult(map);
            apiResponse.setMessage("Marketing Activity Report Already Created");
        }
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getActivityReportHeaderData")
    public ResponseEntity<?> getActivityReportHeaderData(@RequestParam Long activityProposalId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity Report Header Data Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        //apiResponse.setResult(marketingActivityReportRepo.getHeaderData(activityProposalId));
        apiResponse.setResult(marketingActivityProposalRepo.getViewHeaderData(activityProposalId));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getActivityReportEnquiryDetails")
    public ResponseEntity<?> getActivityReportEnquiryDetails(@RequestParam Long activityProposalId, @RequestParam String fromDate, @RequestParam String toDate) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity Report Enquiry Details Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        System.out.println(activityProposalId+","+ fromDate+","+ toDate);
        List<Map<String,Object>> list = marketingActivityReportRepo.getEnquiryDetails(activityProposalId, fromDate, toDate);
        apiResponse.setResult(list);
        if(list!=null)
        	System.out.println(list.size());
        else
        	System.out.println(list);
        return ResponseEntity.ok(apiResponse);
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
    		List<Map<String,Object>> list = marketingActivityReportRepo.getReportImagesByReportId(proposalId);
    		
    		apiResponse.setResult(list);
    		apiResponse.setMessage("Marketing Activity Report Images get Successfully!");
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