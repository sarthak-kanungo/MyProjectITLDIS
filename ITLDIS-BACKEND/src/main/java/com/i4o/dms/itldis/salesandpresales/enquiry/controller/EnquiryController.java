package com.i4o.dms.itldis.salesandpresales.enquiry.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectCropGrown;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectMachineryDetail;
import com.i4o.dms.itldis.masters.dbentities.enquiry.ProspectSoilType;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.dealermaster.customermaster.repository.CustomerMasterRepo;
import com.i4o.dms.itldis.masters.oldcustomerdetail.repository.OldCustomerRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.EnquiryAttachmentsImages;
import com.i4o.dms.itldis.salesandpresales.enquiry.domain.OldVehicleDetails;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquiryDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquiryPartialSearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.dto.EnquirySearchResponseDto;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryAttachmentsImagesRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.itldis.salesandpresales.enquiry.repository.OldVehicleRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityproposal.repository.MarketingActivityProposalRepo;
import com.i4o.dms.itldis.salesandpresales.marketingactivity.marketingactivityreport.domain.MarketingActivityReportImages;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.storage.StorageService;
import com.i4o.dms.itldis.utils.ApiResponse;
import com.i4o.dms.itldis.utils.ExcelCellGenerator;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/salesandpresales/enquiry")
public class EnquiryController {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());
    @Autowired
    private EnquiryRepo enquiryRepo;
    @Autowired
    private OldCustomerRepo oldCustomerRepo;
    /*@Autowired
    private ProspectMasterRepo prospectMasterRepo;*/
    @Autowired
    private MarketingActivityProposalRepo marketingActivityProposalRepo;
    @Autowired
    private DealerEmployeeMasterRepo dealerEmployeeMasterRepo;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private CustomerMasterRepo customerMasterRepo;
    @Autowired
    private OldVehicleRepo oldVehicleRepo; 
    @Autowired
    private StorageService storageService;
    @Autowired
    private EnquiryAttachmentsImagesRepo enquiryAttachmentsImagesRepo;
    

    @GetMapping("/getEnquiryByEnquiryNumber/enquiryNumber")
    public ResponseEntity<?> getEnquiryByEnquiryNumber(@RequestParam String enquiryNumber) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Enquiry by no");
        apiResponse.setStatus(HttpStatus.OK.value());
        EnquiryDto enquiry = enquiryRepo.findByEnquiryNumber(enquiryNumber);
        enquiry.setCurrentFollowUpDate(enquiryRepo.getCurrentFollowUpDate(enquiryNumber));
        Integer count = enquiryRepo.getSubsidyCount(enquiry.getEnquiryNumber());
        if(count!=null && count>0){
        	enquiry.setSubsidyReceived(true);
        }else{
        	enquiry.setSubsidyReceived(false);
        }
        apiResponse.setResult(enquiry);
        return ResponseEntity.ok(apiResponse);
    }

    @ApiOperation(value = "get purpose of purchase", notes = "this method return key and value pair of purpose of purchase")
    @GetMapping("/getPurposeOfPurchase")
    public ResponseEntity<?> getALLPurposeOfPurchase() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get all purpose of purchase");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getPurposeOfPurchase());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEnquiryType")
    public ResponseEntity<?> getPurposeOfPurchase() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get all enquiry type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryType());
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/addEnquiry")
    public ResponseEntity<?> addEnquiry(@Valid @RequestPart Enquiry enquiry, List<MultipartFile> multipartFileList) {
    	System.out.println();
    	System.out.println("addEnquiry----->"+enquiry);
    	System.out.println();
    	Enquiry enquiry1 = new Enquiry();
        ApiResponse apiResponse = new ApiResponse();
        //enquiry.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        enquiry.setBranchId(userAuthentication.getBranchId());
        enquiry.setCreatedBy(userAuthentication.getLoginId());
        enquiry.setCreatedDate(new Date());
        enquiry.setLastModifiedBy(enquiry.getCreatedBy());
		if (enquiry.getAppEnquiryFlag() != null) {
			enquiry.setAppEnquiryFlag(true);
		} else {
			enquiry.setAppEnquiryFlag(false);
			if (enquiry.getEnquiryDate() == null)
				enquiry.setEnquiryDate(new Date());
		}
       /* if (enquiry.getProspectMaster() != null) {
            if (enquiry.getProspectMaster().getProspectCode() != null) {
                enquiry.setProspectMaster(prospectMasterRepo.findById(enquiry.getProspectMaster().getId()).get());
                Enquiry enquiry1 = enquiryRepo.save(enquiry);
                apiResponse.setMessage("only enquiry will be save ");
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setResult(enquiryRepo.getEnquiryNumberAfterEnquiryCreation());
                return ResponseEntity.ok(apiResponse);
            }
        } else */
        if (enquiry.getCustomerMaster() != null && enquiry.getCustomerMaster().getCustomerCode() != null && enquiry.getCustomerMaster().getRecordType()!=null) {
            if (enquiry.getCustomerMaster().getRecordType().equalsIgnoreCase("CUSTOMER")) {
                Long customerId = enquiryRepo.getIdByCustomerMobileNo(enquiry.getMobileNumber());
                if (customerId != null) {
                    enquiry.setCustomerMaster(customerMasterRepo.getOne(customerId));
                    enquiry1 = enquiryRepo.save(enquiry);
                    apiResponse.setMessage("only enquiry and customer will be save ");
                    apiResponse.setStatus(HttpStatus.OK.value());
                    apiResponse.setResult(enquiryRepo.getEnquiryNumberAfterEnquiryCreation(enquiry.getBranchId()));
                    
                    if (!multipartFileList.isEmpty()) {
                        for(MultipartFile m:multipartFileList) {
                        	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                            String attachments = m.getOriginalFilename();
                            String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                            storageService.store(m, photoName);
                            enquiryAttachmentsImages.setFileName(photoName);
                            enquiryAttachmentsImages.setEnquiry(enquiry1);
                            enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
                        };
                    }
                    
                    return ResponseEntity.ok(apiResponse);
                }
            } else if (enquiry.getCustomerMaster().getRecordType().equalsIgnoreCase("OLD CUSTOMER")) {
            	
            	CustomerMaster customerMaster = customerMasterRepo.getOne(enquiry.getCustomerMaster().getId());
                 
            	customerMaster.setRecordType("CUSTOMER");
            	customerMaster.setCompanyName(enquiry.getCompanyName());
            	customerMaster.setTitle(enquiry.getTitle());
            	customerMaster.setCustomerType(enquiry.getProspectType());
            	customerMaster.setFirstName(enquiry.getFirstName());
            	customerMaster.setMiddleName(enquiry.getMiddleName());
            	customerMaster.setLastName(enquiry.getLastName());
            	customerMaster.setFatherName(enquiry.getFatherName());
            	customerMaster.setWhatsAppNo(enquiry.getWhatsAppNumber());
            	customerMaster.setMobileNumber(enquiry.getMobileNumber());
            	customerMaster.setAlternateMobileNo(enquiry.getAlternateMobileNumber());
            	customerMaster.setStd(enquiry.getStd());
            	customerMaster.setTelephoneNo(enquiry.getTelephoneNumber());
            	customerMaster.setEmailId(enquiry.getEmailId());
            	customerMaster.setAddress1(enquiry.getAddress1());
            	customerMaster.setAddress2(enquiry.getAddress2());
            	customerMaster.setAddress3(enquiry.getAddress3());
            	customerMaster.setPinCode(enquiry.getPinCode());
            	customerMaster.setPinId(enquiry.getPinId());
            	customerMaster.setPostOffice(enquiry.getPostOffice());
                customerMaster.setCity(enquiry.getCity());
                customerMaster.setTehsil(enquiry.getTehsil());
                customerMaster.setDistrict(enquiry.getDistrict());
                customerMaster.setState(enquiry.getState());
                customerMaster.setCountry(enquiry.getCountry());
                customerMaster.setLanguage(enquiry.getLanguage());
                customerMaster.setLandInAcres(enquiry.getLandSize());
                customerMaster.setDob(enquiry.getDob());
                customerMaster.setAnniversaryDate(enquiry.getAnniversaryDate());
                customerMaster.setAnniversaryDate(enquiry.getAnniversaryDate());
                customerMaster.setGstNo(enquiry.getGstNumber());
                customerMaster.setOccupation(enquiry.getOccupation());
                customerMaster.setPancardNo(enquiry.getPanNumber());

                customerMasterRepo.save(customerMaster);
                
                enquiry.setCustomerMaster(customerMaster);
                enquiry1 = enquiryRepo.save(enquiry);
                apiResponse.setMessage("Enquiry saved successfully  ");
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setResult(enquiryRepo.getEnquiryNumberAfterEnquiryCreation(enquiry.getBranchId()));
                
                if (!multipartFileList.isEmpty()) {
                    for(MultipartFile m:multipartFileList) {
                    	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                        String attachments = m.getOriginalFilename();
                        String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                        storageService.store(m, photoName);
                        enquiryAttachmentsImages.setFileName(photoName);
                        enquiryAttachmentsImages.setEnquiry(enquiry1);
                        enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
                    };
                }
                
                return ResponseEntity.ok(apiResponse);

            }else if (enquiry.getCustomerMaster().getRecordType().equalsIgnoreCase("PROSPECT")) {
                //CustomerMaster customerMaster = customerMasterRepo.save(enquiry.getCustomerMaster());
                enquiry.setCustomerMaster(enquiry.getCustomerMaster());
                enquiry1 = enquiryRepo.save(enquiry);
                apiResponse.setMessage("Enquiry saved successfully  ");
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setResult(enquiryRepo.getEnquiryNumberAfterEnquiryCreation(enquiry.getBranchId()));
                
                if (!multipartFileList.isEmpty()) {
                    for(MultipartFile m:multipartFileList) {
                    	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                        String attachments = m.getOriginalFilename();
                        String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                        storageService.store(m, photoName);
                        enquiryAttachmentsImages.setFileName(photoName);
                        enquiryAttachmentsImages.setEnquiry(enquiry1);
                        enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
                    };
                }
                
                return ResponseEntity.ok(apiResponse);

            }
        } else {

        	CustomerMaster prospectMaster = new CustomerMaster();
        	prospectMaster.setRecordType("PROSPECT");
            prospectMaster.setCompanyName(enquiry.getCompanyName());
            prospectMaster.setTitle(enquiry.getTitle());
            prospectMaster.setCustomerType(enquiry.getProspectType());
            prospectMaster.setFirstName(enquiry.getFirstName());
            prospectMaster.setMiddleName(enquiry.getMiddleName());
            prospectMaster.setLastName(enquiry.getLastName());
            prospectMaster.setFatherName(enquiry.getFatherName());
            prospectMaster.setWhatsAppNo(enquiry.getWhatsAppNumber());
            prospectMaster.setMobileNumber(enquiry.getMobileNumber());


            prospectMaster.setAlternateMobileNo(enquiry.getAlternateMobileNumber());
            prospectMaster.setStd(enquiry.getStd());
            prospectMaster.setTelephoneNo(enquiry.getTelephoneNumber());
            prospectMaster.setEmailId(enquiry.getEmailId());
            prospectMaster.setAddress1(enquiry.getAddress1());
            prospectMaster.setAddress2(enquiry.getAddress2());

            prospectMaster.setAddress3(enquiry.getAddress3());
            prospectMaster.setPinCode(enquiry.getPinCode());
            prospectMaster.setPinId(enquiry.getPinId());
            prospectMaster.setPostOffice(enquiry.getPostOffice());
            prospectMaster.setCity(enquiry.getCity());
            prospectMaster.setTehsil(enquiry.getTehsil());

            prospectMaster.setDistrict(enquiry.getDistrict());
            prospectMaster.setState(enquiry.getState());
            prospectMaster.setCountry(enquiry.getCountry());
            prospectMaster.setLanguage(enquiry.getLanguage());
            prospectMaster.setLandInAcres(enquiry.getLandSize());

            prospectMaster.setDob(enquiry.getDob());
            prospectMaster.setAnniversaryDate(enquiry.getAnniversaryDate());
            prospectMaster.setAnniversaryDate(enquiry.getAnniversaryDate());
            prospectMaster.setGstNo(enquiry.getGstNumber());
            //prospectMaster.setOther(enquiry.getOther());
            prospectMaster.setOccupation(enquiry.getOccupation());
            prospectMaster.setPancardNo(enquiry.getPanNumber());
            prospectMaster.setCreatedBy(userAuthentication.getLoginId());
            
            List<ProspectSoilType> prospectSoilTypeList = new ArrayList<>();
            if (enquiry.getEnquirySoilTypes() != null) {
                enquiry.getEnquirySoilTypes().forEach(e -> {
                    logger.info(e.getSoilType()+"-================");
                    ProspectSoilType prospectSoilType = new ProspectSoilType();
                    prospectSoilType.setSoilName(e.getSoilType());
                    prospectSoilType.setCustomerMaster(prospectMaster);
                    prospectSoilTypeList.add(prospectSoilType);

                });
                prospectMaster.setProspectSoilTypes(prospectSoilTypeList);
            }

            List<ProspectCropGrown> prospectCropGrownList = new ArrayList<>();
            if (enquiry.getEnquiryCropGrows() != null) {
                enquiry.getEnquiryCropGrows().forEach(e -> {
                    ProspectCropGrown prospectCropGrown = new ProspectCropGrown();
                    prospectCropGrown.setCropName(e.getCropGrown());
                    prospectCropGrown.setCustomerMaster(prospectMaster);
                    prospectCropGrownList.add(prospectCropGrown);
                });

                prospectMaster.setProspectCropNames(prospectCropGrownList);
            }
            List<ProspectMachineryDetail> prospectMachineryDetailList = new ArrayList<>();
            if (enquiry.getEnquiryMachineryDetails() != null) {
                enquiry.getEnquiryMachineryDetails().forEach(e -> {
                    ProspectMachineryDetail prospectMachineryDetail = new ProspectMachineryDetail();
                    prospectMachineryDetail.setBrand(e.getBrand());
                    prospectMachineryDetail.setYearOfPurchase(e.getYearOfPurchase());
                    prospectMachineryDetail.setModel(e.getModel());
                    prospectMachineryDetail.setCustomerMaster(prospectMaster);
                    prospectMachineryDetailList.add(prospectMachineryDetail);

                });
                prospectMaster.setProspectMachineryDetails(prospectMachineryDetailList);
            }
            System.out.println("<-------Customer Master Save-------->");
            customerMasterRepo.save(prospectMaster);
            enquiry.setCustomerMaster(prospectMaster);
            if (enquiry.getGenerationActivityNumber() != null) {
                enquiry.setGenerationActivityNumber(marketingActivityProposalRepo.getOne(enquiry.getGenerationActivityNumber().getActivityProposalId()));
            }
            logger.info("enquiry.getId():"+enquiry.getId());

            enquiry1 = enquiryRepo.save(enquiry);
            logger.info("enquiry1.getId():"+enquiry1.getId());

            String result= enquiryRepo.updateProspectCodeInEnquiry(enquiry1.getId());
            logger.info("result:"+result);
            apiResponse.setMessage("Enquiry saved successfully ");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(enquiryRepo.getEnquiryNumberAfterEnquiryCreation(enquiry.getBranchId()));
            
            if (!multipartFileList.isEmpty()) {
                for(MultipartFile m:multipartFileList) {
                	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                    String attachments = m.getOriginalFilename();
                    String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                    storageService.store(m, photoName);
                    enquiryAttachmentsImages.setFileName(photoName);
                    enquiryAttachmentsImages.setEnquiry(enquiry1);
                    enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
                };
            }

            return ResponseEntity.ok(apiResponse);
        }
        if (!multipartFileList.isEmpty()) {
            for(MultipartFile m:multipartFileList) {
            	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                String attachments = m.getOriginalFilename();
                String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                storageService.store(m, photoName);
                enquiryAttachmentsImages.setFileName(photoName);
                enquiryAttachmentsImages.setEnquiry(enquiry1);
                enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
            };
        }
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getFollowUpType")
    public ResponseEntity<?> getFollowUpType() {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> enquiryData = enquiryRepo.getFollowUpType();
        apiResponse.setMessage("get enquiry follow up ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryData);
        ResponseEntity<ApiResponse> entity = ResponseEntity.ok(apiResponse);
        return entity;
    }

    @PostMapping("/addEnquiryInApp")
    public ResponseEntity<?> addEnquiryInApp(@RequestBody Enquiry enquiry) {
    	System.out.println(enquiry);
        ApiResponse apiResponse = new ApiResponse();
        enquiry.setBranchId(userAuthentication.getBranchId());
        enquiry.setCreatedBy(userAuthentication.getLoginId());
        enquiry.setSalesPerson(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        enquiry.setLastModifiedBy(enquiry.getCreatedBy());
        apiResponse.setMessage(" enquiry save by app");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.save(enquiry));
        ResponseEntity<ApiResponse> entity = ResponseEntity.ok(apiResponse);
        return entity;
    }


    @GetMapping("/getProspectType")
    public ResponseEntity<?> getProspectType() {
        ApiResponse apiResponse = new ApiResponse();
        List prospectType = enquiryRepo.getProspectType();
        apiResponse.setMessage("get prospect type  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(prospectType);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getSoilType")
    public ResponseEntity<?> getSoilType() {
        ApiResponse apiResponse = new ApiResponse();
        List soilType = enquiryRepo.getSoilType();
        apiResponse.setMessage("get soil type  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(soilType);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getMajorCropGrown")
    public ResponseEntity<?> getMajorCropGrown() {
        ApiResponse apiResponse = new ApiResponse();
        List majorCropGrown = enquiryRepo.getMajorCropGrown();
        apiResponse.setMessage("get major crop grown  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(majorCropGrown);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getCashLoan")
    public ResponseEntity<?> getCashLoan() {
        ApiResponse apiResponse = new ApiResponse();
        List cashLoan = enquiryRepo.getCashLoan();
        apiResponse.setMessage("get cash loan drop down  ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(cashLoan);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getFinalStatus")
    public ResponseEntity<?> getFinalStatus() {
        ApiResponse apiResponse = new ApiResponse();
        List finalStatus = enquiryRepo.getFinanceStatus();
        apiResponse.setMessage("get final Status ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(finalStatus);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getOccupation")
    public ResponseEntity<?> getOccupation() {
        ApiResponse apiResponse = new ApiResponse();
        List occupation = enquiryRepo.getOccupation();
        apiResponse.setMessage("get occupation ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(occupation);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getConversionActivityType")
    public ResponseEntity<?> getConversionActivityType() {
        ApiResponse apiResponse = new ApiResponse();
        List conversionActivityType = enquiryRepo.getConversionActivityType();
        apiResponse.setMessage("get conversion marketingactivity type ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(conversionActivityType);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getGenerationActivityType")
    public ResponseEntity<?> getGenerationActivityType() {
        ApiResponse apiResponse = new ApiResponse();
        List generationActivityType = enquiryRepo.getGenerationActivityType();
        apiResponse.setMessage("get generation marketingactivity type ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(generationActivityType);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBrands")
    public ResponseEntity<?> getBrands() {
        List brands = enquiryRepo.getBrand();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get brands");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(brands);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getRetailConversionActivityType")
    public ResponseEntity<?> getRetailConversionActivityType() {
        List brands = enquiryRepo.getRetailConversionActivity();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get retail conversion marketingactivity type ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(brands);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEnquirySearch")
    public ResponseEntity<?> getEnquirySearch(
            @RequestParam(required = true) Long userId,
            @RequestParam(required = false) String enquiryNumber,
            @RequestParam(required = false) String enquiryType,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String salesPerson,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String enquiryStatus,
            @RequestParam(required = false) String retailConversionActivity,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String series,
            @RequestParam(required = false) String subModel,
            @RequestParam(required = false) String variant,
            @RequestParam(required = false) String itemNo,
            @RequestParam(required = false) String finance,
            @RequestParam(required = false) String autoClose,
            @RequestParam(required = false) String subSidy,
            @RequestParam(required = false) String exchange,           
            @RequestParam(required = false) String nextFollowUpToDate,
            @RequestParam(required = false) String nextfollowUpFromDate, 
            @RequestParam(required = false) String tentativePurchaseFromDate,
            @RequestParam(required = false) String tentativePurchaseToDate,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String village,
            @RequestParam(required = false,defaultValue="0") Long hierId,
            @RequestParam(required = false) Boolean showPartial,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) Long dealerId) {
    	
//    	System.out.println("EnquirySearch----> "+" 1) ManagementAccess=> "+userAuthentication.getManagementAccess() 
//    	+" 2) DealerId=> "+userAuthentication.getDealerId()
//    	+" 3) KubotaEmployeeId=> "+userAuthentication.getKubotaEmployeeId()    
//    	+" 4) DealerEmployeeId=> "+userAuthentication.getDealerEmployeeId()
//    	+" 5) enquiryNumber=> "+enquiryNumber+" 6) enquiryType=> "+ enquiryType+" 7) model=> "+ model+" 8) salesPerson=> "+ salesPerson+" 9) fromDate=> "+ fromDate
//    	+" 10) toDate=> "+ toDate
//    	+" 11) source=> "+source+" 12) enquiryStatus=> "+ enquiryStatus+" 13) retailConversionActivity=> "+ retailConversionActivity+" 14) product=> "+ product
//    	+" 15) series=> "+ series+" 16) subModel=> "+ subModel
//    	+" 17) variant=> "+variant+" 18) itemNo=> "+ itemNo+" 19) finance=> "+ finance+" 20) autoClose=> "+ autoClose+" 21) subSidy=> "+ subSidy+" 22) exchange=> "+ exchange
//    	+" 23) nextfollowUpFromDate=> "+nextfollowUpFromDate+" 24) nextFollowUpToDate=> "+ nextFollowUpToDate+" 25) tentativePurchaseFromDate=> "+ tentativePurchaseFromDate
//    	+" 26) tentativePurchaseToDate=> "+ tentativePurchaseToDate
//    	+" 27) Username=> "+userAuthentication.getUsername()+" 28) state=> "+state+" 29) village=> "+village+" 30) includeInactive =>"+'N'
//    	+" 31) hierId=> "+hierId+" 32) page=> "+page
//    	+" 33) size=> "+size);
    	
        Map<String, Object> map = new HashMap<>();

        Long getEnquirySearchCount = 0L;
        Long dealerid;
        if(userAuthentication.getDealerId()==null) {
        	dealerid = dealerId;
        }else {
        	dealerid = userAuthentication.getDealerId();
        }
        
        if(showPartial==null || !showPartial){
	        List<EnquirySearchResponseDto> enquirySearchResponseDto = enquiryRepo.getEnquirySearch(userAuthentication.getManagementAccess(),dealerid,userAuthentication.getKubotaEmployeeId(),
	                userAuthentication.getDealerEmployeeId(), enquiryNumber, enquiryType, model, salesPerson, fromDate, toDate, source, enquiryStatus, retailConversionActivity, product, series, subModel, variant, itemNo, finance, autoClose, subSidy, exchange, nextfollowUpFromDate, nextFollowUpToDate, tentativePurchaseFromDate, tentativePurchaseToDate,
	                userAuthentication.getUsername(),state,village,'N',hierId,page, size);
	        
	        //System.out.println("action------>"+enquirySearchResponseDto.get(0).getAction());
	        if(enquirySearchResponseDto!=null && enquirySearchResponseDto.size()>0){
	        	getEnquirySearchCount = enquirySearchResponseDto.get(0).getTotalRecords();
	        }
	        map.put("result", enquirySearchResponseDto);
        }else{
        	List<EnquiryPartialSearchResponseDto> enquirySearchResponseDto = enquiryRepo.getEnquiryPartialSearch(userAuthentication.getManagementAccess(),dealerid,userAuthentication.getKubotaEmployeeId(),
	                userAuthentication.getDealerEmployeeId(), enquiryNumber, enquiryType, model, salesPerson, fromDate, toDate, source, enquiryStatus, retailConversionActivity, product, series, subModel, variant, itemNo, finance, autoClose, subSidy, exchange, nextfollowUpFromDate, nextFollowUpToDate, tentativePurchaseFromDate, tentativePurchaseToDate,
	                userAuthentication.getUsername(),state,village,'N',hierId,page, size);
	        if(enquirySearchResponseDto!=null && enquirySearchResponseDto.size()>0){
	        	getEnquirySearchCount = enquirySearchResponseDto.get(0).getTotalRecords();
	        }
            map.put("result", enquirySearchResponseDto);
        }
        
        Boolean isAllowTransferEnquiry = enquiryRepo.getFunctionPermision(userAuthentication.getLoginId(), "Transfer Enquiry");
        map.put("isAllowTransferEnquiry", isAllowTransferEnquiry);
		/*
		 * System.out.println(userAuthentication.getManagementAccess()+":"+
		 * userAuthentication.getDealerId()+":"+
		 * userAuthentication.getKubotaEmployeeId()+":"+
		 * userAuthentication.getDealerEmployeeId()+":"+ enquiryNumber+":"+
		 * enquiryType+":"+ model+":"+ salesPerson+":"+ fromDate+":"+
		 * toDate+":"+ source+":"+ enquiryStatus+":"+
		 * retailConversionActivity+":"+ product+":"+ series+":"+ subModel+":"+
		 * variant+":"+ itemNo+":"+ finance+":"+ autoClose+":"+ subSidy+":"+
		 * exchange+":"+ nextFollowUpFromDate+":"+ nextFollowUpToDate+":"+
		 * tentativePurchaseFromDate+":"+ tentativePurchaseToDate+":"+
		 * userAuthentication.getUsername()+":"+ state+":"+ village+":"+
		 * 'N'+":"+ hierId+":"+ page+":"+ size);
		 */
		/*
		 * enquiryRepo.getEnquirySearchCount(userAuthentication.
		 * getManagementAccess(),userAuthentication.getDealerId(),
		 * userAuthentication.getKubotaEmployeeId(),
		 * userAuthentication.getDealerEmployeeId(), enquiryNumber, enquiryType,
		 * model, salesPerson, fromDate, toDate, source, enquiryStatus,
		 * retailConversionActivity, product, series, subModel, variant, itemNo,
		 * finance, autoClose, subSidy, exchange, nextFollowUpFromDate,
		 * nextFollowUpToDate, tentativePurchaseFromDate,
		 * tentativePurchaseToDate, page, size);
		 */
        map.put("message", "get enquiry search");
        map.put("status", HttpStatus.OK.value());
        map.put("count", getEnquirySearchCount);
        
        return ResponseEntity.ok(map);
    }
    @GetMapping("/getStatesEnuiry")
    public ResponseEntity<?> getStatesEnuiry(@RequestParam Long cityId){
    	System.out.println("cityId--"+userAuthentication.getDealerId());
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get States data");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getStatesEnuiry(cityId, userAuthentication.getUsername(), userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/villageTehsilDistrictSearch")
    public ResponseEntity<?> villageTehsilDistrictSearch(@RequestParam String searchValue, @RequestParam(required=false) Long stateId) {
    	System.out.println("villageTehsilDistrictSearch------>" + searchValue +" ==== "+ userAuthentication.getUsername() +" ==== "+ userAuthentication.getDealerId());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Get Vilage/Tehsil/District data");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.villageTehsilDistrictSearch(searchValue, userAuthentication.getUsername(), userAuthentication.getDealerId(), stateId));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/getDataByMobileNo")
    public ResponseEntity<?> getDataByMobileNo(@RequestParam String mobileNumber) {
        ApiResponse apiResponse = new ApiResponse();

        Long customerId = enquiryRepo.getIdByCustomerMobileNo(mobileNumber);

        if (customerId != null) {

            apiResponse.setMessage("get customer data");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(customerMasterRepo.findById(customerId));
            return ResponseEntity.ok(apiResponse);
        }
        /*Long oldCustomerId = enquiryRepo.getIdByOldCustomerMobileNo(mobileNumber);
        if (oldCustomerId != null) {

            apiResponse.setMessage("get old customer data");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(oldCustomerRepo.findById(oldCustomerId));
            return ResponseEntity.ok(apiResponse);

        }*/
        /*Long prospectId = enquiryRepo.getIdByProspectMobileNo(mobileNumber);
        if (prospectId != null) {

            apiResponse.setMessage("get prospect data");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult(prospectMasterRepo.findById(prospectId));
            return ResponseEntity.ok(apiResponse);
        }
*/
        return null;
    }

    //need to change this service to below to customer master service
    @GetMapping("/getMobileNumber")
    public ResponseEntity<?> getMobileNumber(@RequestParam String mobileNumber, @RequestParam(required=false) String requestFrom) {
    	System.out.println(mobileNumber+":"+userAuthentication.getDealerId());
        List<Map<String, Object>> mobile = enquiryRepo.getMobileNumber(mobileNumber,requestFrom,userAuthentication.getDealerId(), userAuthentication.getUsername());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get mobile number");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(mobile);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getEnquiryNo")
    public ResponseEntity<?> getEnquiryNo(@RequestParam String enquiryNo, @RequestParam(required=false,defaultValue="ENQUIRY_SEARCH") String functionality) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> list = enquiryRepo.getEnquiryNumberName(enquiryNo,userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId(),
        		userAuthentication.getBranchId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getUsername(),
        		functionality);
        apiResponse.setMessage("get enquiry");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }

    /*@SuppressWarnings("unchecked")
	@GetMapping("/getEnquiryCode")
    public ResponseEntity<?> getEnquiryCode(@RequestParam(required = true) String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry number  all");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryNumberName(enquiryNo,
        		userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId(),
        		userAuthentication.getBranchId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getUsername(),
        		"PAYMENT_RECEIPT"
        		)
        	);
        return ResponseEntity.ok(apiResponse);

    }*/

    @Deprecated
    @GetMapping("/getenquirybyactivitynumber")
    public ResponseEntity<?> getEnquiryByActivityNumber(@RequestParam String activityNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("enquiry get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryByActivityNumber(activityNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getEnquiryListByCustomerNameAndMobileNo")
    public ResponseEntity<?> getEnquiryListByCustomerNameAndMobileNo(@RequestParam(required = false) String customerNameMobileNo, @RequestParam(required = true) Long userId) {
        ApiResponse apiResponse = new ApiResponse();

        List<Map<String, Object>> enquiryList = enquiryRepo.getEnquiryListByCustomerNameAndMobileNo(customerNameMobileNo, userAuthentication.getDealerEmployeeId());
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryList);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getEnquiryById/{id}")
    public ResponseEntity<?> getEnquiryById(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry by id");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.findById(id));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("isEnquiryValidate")
    public ResponseEntity<?> isEnquiryValidate(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry by id");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.isValidate(enquiryNumber));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getEnquiryInAppByEnquiryNo")
    public ResponseEntity<?> getEnquiryInAppByEnquiryNo(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry in app");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryInAppByEnquiryNo(enquiryNumber));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getEnquiryList")
    public ResponseEntity<?> getEnquiryList(@RequestParam String fromDate, @RequestParam String toDate, @RequestParam(required = false) String model, @RequestParam(required = true) Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryList(fromDate, toDate, model, userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getEnquiryByNumber")
    public ResponseEntity<?> getEnquiryByNumber(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("enquiry get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryByNumber(enquiryNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/setEnquiryType")
    public ResponseEntity<?> setEnquiryType(@RequestParam String enquiryDate, @RequestParam String tentativePurchaseDate) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("set enquiry type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.setEnquiryType(enquiryDate, tentativePurchaseDate));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/searchMarketingActivityNumber")
    public ResponseEntity<?> searchMarketingActivityNumber(@RequestParam String activityNumber, @RequestParam String activityPurpose, @RequestParam String source, @RequestParam String activityType, @RequestParam String enqnumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("set enquiry type");
        apiResponse.setStatus(HttpStatus.OK.value());
        System.out.println(activityNumber+","+activityPurpose+","+source+","+userAuthentication.getDealerId()+","+activityType+","+enqnumber);
        apiResponse.setResult(enquiryRepo.searchMarketingActivityNumber(activityNumber, activityPurpose, source,userAuthentication.getDealerId(), activityType,enqnumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/isMobileNumberExist")
    public ResponseEntity<?> isMobileNumberExist(@RequestParam String mobileNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("set enquiry type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.isMobileNumberExist(mobileNumber));
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getEnquiryNumberNameMobileNoTehsil")
    public ResponseEntity<?> getEnquiryNumberNameMobileTehsil(@RequestParam String search) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry number and name ,mobile number ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryNumberName(search,
        		userAuthentication.getManagementAccess(),
        		userAuthentication.getDealerId(),
        		userAuthentication.getBranchId(),
        		userAuthentication.getKubotaEmployeeId(),
        		userAuthentication.getDealerEmployeeId(),
        		userAuthentication.getUsername(),
        		"ENQUIRY_SEARCH"
        		)
        	);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateEnquiry")
    public ResponseEntity<?> updateEnquiry(@RequestPart Enquiry enquiry, List<MultipartFile> multipartFileList) {
        ApiResponse apiResponse = new ApiResponse();
        //enquiry.setDealerMaster(dealerMasterRepo.getOne(userAuthentication.getDealerId()));
        //enquiry.setCreatedBy(dealerEmployeeMasterRepo.getOne(userAuthentication.getDealerEmployeeId()));
        
        enquiry.setLastModifiedDate(new Date());
        enquiry.setLastModifiedBy(userAuthentication.getLoginId());
        apiResponse.setMessage("update enquiry ");
        apiResponse.setStatus(HttpStatus.OK.value());
        enquiry.setAppEnquiryFlag(enquiryRepo.getAppEnquiryFlag(enquiry.getEnquiryNumber()));
        Enquiry enquiry1 = enquiryRepo.save(enquiry);
        if(enquiry.getCustomerMaster()!=null) {
            String result = enquiryRepo.updateEnquiry(enquiry.getId(), enquiry.getCustomerMaster().getId());
            apiResponse.setResult(result);
            if(enquiry.getExchangeReceived()!=null && enquiry.getExchangeReceived().equals('Y')){
            	OldVehicleDetails oldVehicleDetails = oldVehicleRepo.getByEnquiryId(enquiry.getId());
            	if( oldVehicleDetails == null ){
            		oldVehicleDetails = new OldVehicleDetails();
	            	oldVehicleDetails.setBranchId(userAuthentication.getBranchId());
	            	oldVehicleDetails.setBrandName(enquiry.getExchangeBrand());
	            	oldVehicleDetails.setModelName(enquiry.getExchangeModel());
	            	oldVehicleDetails.setModelYear(enquiry.getExchangeModelYear());
	            	oldVehicleDetails.setEnquiryId(enquiry.getId());
	            	oldVehicleDetails.setCreatedby(userAuthentication.getLoginId());
	            	oldVehicleDetails.setCreateddate(new Date());
	            	oldVehicleDetails.setInvInDate(new Date());
	            	oldVehicleDetails.setEstimatedExchangePrice(enquiry.getEstimatedExchangePrice());
	            	oldVehicleDetails.setStatus("Pending");
	            	oldVehicleRepo.save(oldVehicleDetails);
            	}
            }
        }
        
        System.out.println("multipartFileList.isEmpty()----> "+multipartFileList.isEmpty());
        if (!multipartFileList.isEmpty()) {
            multipartFileList.forEach(m -> {
            	EnquiryAttachmentsImages enquiryAttachmentsImages = new EnquiryAttachmentsImages();
                String attachments = m.getOriginalFilename();
                System.out.println("File Attachments----> "+attachments);
                String photoName = "enquiry_attachments_photos" + System.currentTimeMillis() + "_" + attachments;
                storageService.store(m, photoName);
                enquiryAttachmentsImages.setFileName(photoName);
                enquiryAttachmentsImages.setEnquiry(enquiry1);
                enquiryAttachmentsImagesRepo.save(enquiryAttachmentsImages);
            });
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEnquiryStatus")
    public ResponseEntity<?> getEnquiryStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get enquiry status ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getEnquiryListByDealerEmployeeMasterId")
    public ResponseEntity<?> getEnquiryNoByDealerEmployeeMasterId(@RequestParam(required = true) Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Search enquiry number by dealer employee master ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getEnquiryNoByDealerEmployeeMasterId(id));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getItemByModel")
    public ResponseEntity<?> getItemNumberInEnquiry(@RequestParam(required = true) String itemNumber, @RequestParam(required = false) String model) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Search enquiry number by dealer employee master ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getItemNumberInEnquiry(itemNumber, model));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getCustomerNameMobileNumberModelCity")
    public ResponseEntity<?> getItemNumberInEnquiry(@RequestParam(required = false) String search, @RequestParam(required = true) Long userId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get customer name mobile number model and city ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getCustomerNameMobileModelCity(search, userId));
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownEnquiryStatus")
    public ResponseEntity<?> dropDownEnquiryStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("drop down enquiry status ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.dropDownEnquiryStatus());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/checkItemNumberModelInEnquiry")
    public ResponseEntity<?> checkItemNumberModelInEnquiry(String model,String mobileNumber) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setStatus(HttpStatus.OK.value());
        Integer bool=enquiryRepo.checkItemNumberModelInEnquiry(model,mobileNumber);
        if(bool==0) {
            apiResponse.setMessage("Enquiry already created  with model "+ model);
            apiResponse.setResult(bool);
        }else{
            apiResponse.setResult(bool);
        }

        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/dropDownTitle")
    public ResponseEntity<?> dropDownTitle() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("drop down title ");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.dropDownTitle());
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/reopenEnquiry")
    public ResponseEntity<?> reopenEnquiry(@RequestParam String enquiryNo){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Enquiry Reopened");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.reopenEnquiry(enquiryNo, userAuthentication.getDealerId(), userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getFinancier")
    public ResponseEntity<?> getFinancier(@RequestParam String searchValue){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Financier List");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryRepo.getFinancier(userAuthentication.getBranchId(), userAuthentication.getDealerId(),searchValue, userAuthentication.getUsername()));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/downloadEnquiryReportExcel")
    public ResponseEntity<InputStreamResource> downloadEnquiryReportExcel(@RequestParam(required = true) Long userId,
            @RequestParam(required = false) String enquiryNumber,
            @RequestParam(required = false) String enquiryType,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String salesPerson,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String enquiryStatus,
            @RequestParam(required = false) String retailConversionActivity,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String series,
            @RequestParam(required = false) String subModel,
            @RequestParam(required = false) String variant,
            @RequestParam(required = false) String itemNo,
            @RequestParam(required = false) String finance,
            @RequestParam(required = false) String autoClose,
            @RequestParam(required = false) String subSidy,
            @RequestParam(required = false) String exchange,
            @RequestParam(required = false) String nextFollowUpFromDate,
            @RequestParam(required = false) String nextFollowUpToDate,
            @RequestParam(required = false) String tentativePurchaseFromDate,
            @RequestParam(required = false) String tentativePurchaseToDate,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String village,
            @RequestParam(required = false) Long hierId,
            @RequestParam(required = false) Boolean showPartial,
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) Long dealerId,
            HttpServletResponse response) throws IOException{
    	
    	Long getEnquirySearchCount = 0L;
    	  Long dealerid;
          if(userAuthentication.getDealerId()==null) {
          	dealerid = dealerId;
          }else {
          	dealerid = userAuthentication.getDealerId();
          }
    	size = Integer.MAX_VALUE-1;
        List<EnquirySearchResponseDto> enquirySearchResponseDto = enquiryRepo.getEnquirySearch(userAuthentication.getManagementAccess(),dealerid,userAuthentication.getKubotaEmployeeId(),
                userAuthentication.getDealerEmployeeId(), enquiryNumber, enquiryType, model, salesPerson, fromDate, toDate, source, enquiryStatus, retailConversionActivity, product, series, subModel, variant, itemNo, finance, autoClose, subSidy, exchange, nextFollowUpFromDate, nextFollowUpToDate, tentativePurchaseFromDate, tentativePurchaseToDate,
                userAuthentication.getUsername(),state,village,'N',hierId,page, size);
        
    	ByteArrayInputStream in = ExcelCellGenerator.enquiryExcelReport(enquirySearchResponseDto);

        response.setContentType("application/vnd.ms-excel");

        HttpHeaders headers = new HttpHeaders();
        String filename = "EnquiryReport_"+(Calendar.getInstance()).getTimeInMillis()+".xlsx";
        headers.add("Content-Disposition", "attachment ; filename = "+filename);
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);


        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));

    }
    
    @DeleteMapping("/deleteAttachment")
    public ResponseEntity<?> deleteAttachmentFile(@RequestParam String id){
    	ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        try {
        	enquiryAttachmentsImagesRepo.deleteById(Long.parseLong(id));
            apiResponse.setMessage("Attachment is Deleted Successfully.");
            return ResponseEntity.ok(apiResponse);
		} catch (Exception e) {
	        apiResponse.setMessage("Attachment is not in database.");
			 return ResponseEntity.ok(apiResponse);
		}
    	              
       
    }
    
    @GetMapping("/getLanguages")
    public ResponseEntity<ApiResponse> languagesForEnquary() {
    	ApiResponse apiResponse = new ApiResponse();
    	List languageList=enquiryRepo.getLanguages();
    	apiResponse.setMessage("Languages get Successfully");
    	apiResponse.setStatus(HttpStatus.OK.value());
    	apiResponse.setResult(languageList);
    	return ResponseEntity.ok(apiResponse);
    }
    
}












