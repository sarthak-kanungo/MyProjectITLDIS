package com.i4o.dms.kubota.salesandpresales.enquiry.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.salesandpresales.enquiry.domain.Enquiry;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.EnquiryFollowUp;
import com.i4o.dms.kubota.salesandpresales.enquiry.domain.EnquiryLostDrop;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryFollowUpRepo;
import com.i4o.dms.kubota.salesandpresales.enquiry.repository.EnquiryRepo;
import com.i4o.dms.kubota.security.service.UserAuthentication;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/salesandpresales/enquiry")
public class EnquiryFollowUpController {
    @Autowired
    private EnquiryFollowUpRepo enquiryFollowUpRepo;
    @Autowired
    private EnquiryRepo enquiryRepo;
    @Autowired
    private UserAuthentication userAuthentication;

    @PostMapping("/addEnquiryFollowUp")
    public ResponseEntity<?> addEnquiryFollowUp(@RequestBody EnquiryFollowUp enquiryFollowUp) {
        ApiResponse apiResponse = new ApiResponse();
        enquiryFollowUp.setCreatedBy(userAuthentication.getLoginId());
        enquiryFollowUp.setCreatedDate(new Date());
        EnquiryFollowUp enquiryFollowUp1 = enquiryFollowUpRepo.save(enquiryFollowUp);
        apiResponse.setMessage("data saved successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUp);
        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping("/addEnquiryLostDrop")
    public ResponseEntity<?> addEnquiryLostDrop(@RequestBody EnquiryLostDrop enquiryLostDrop)
    {
        ApiResponse apiResponse = new ApiResponse();
        Enquiry enquiry=enquiryRepo.getOne(enquiryLostDrop.getEnquiry().getId());

            enquiry.setResult(enquiryLostDrop.getResult());

            if(enquiryLostDrop.getResult().equals("Lost")){
                enquiry.setEnquiryStatus("LOST");
            }else{
                enquiry.setEnquiryStatus("DROP");
            }
            enquiry.setLostDropReason(enquiryLostDrop.getReason());
            enquiry.setAlternateRemarks(enquiryLostDrop.getLostDrop());
            enquiry.setBrand(enquiryLostDrop.getBrand());
            enquiry.setAlternateModel(enquiryLostDrop.getModel());
            enquiry.setAlternateReason(enquiryLostDrop.getReason());

            Enquiry enquiry1=  enquiryRepo.save(enquiry);
            apiResponse.setMessage("enquiry update with result");
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setResult("data");
           return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getEnquiryFollowByEnquiryCode")
    public ResponseEntity<?> getEnquiryFollowByMobileNo(String enquiryNo) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> enquiryFollowUp1 = enquiryFollowUpRepo.getEnquiryFollowUp(enquiryNo);
        apiResponse.setMessage("data enquiry follow up");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUp1);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getResult")
    public ResponseEntity<?> getResult() {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> result = enquiryFollowUpRepo.getResult();
        apiResponse.setMessage("get result");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(result);
        return ResponseEntity.ok(apiResponse);


    }

    @GetMapping("/getLostDropReason")
    public ResponseEntity<?> getLostDropReason(@RequestParam String result) {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> lostDropReason = enquiryFollowUpRepo.getLostDropReason(result);
        apiResponse.setMessage("get lost drop reason");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(lostDropReason);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getReason")
    public ResponseEntity<?> getReason() {
        ApiResponse apiResponse = new ApiResponse();
        List<Map<String, Object>> enquiryFollowUp1 = enquiryFollowUpRepo.getReason();
        apiResponse.setMessage("data reason");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUp1);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getTodayFollowUp")
    public ResponseEntity<?> getTodayFollowUp(@RequestParam  Long id ) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get today follow up");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUpRepo.getTodayFollowUp(userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("getPendingValidation")
    public ResponseEntity<?> getPendingValidation(@RequestParam Long id) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("get pending validation");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUpRepo.getPendingValidation(userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getPendingFollowUp")

    public ResponseEntity<?> getPendingFollowUp(@RequestParam(required = true) Long id) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setMessage("get pending follow up");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUpRepo.getPendingFollowUp(userAuthentication.getDealerEmployeeId()));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getFollowUpHistory")
    public ResponseEntity<?> getFollowUpHistory(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get follow up history");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUpRepo.getFollowUpHistory(enquiryNumber));
        return ResponseEntity.ok(apiResponse);

    }


    @GetMapping("getFollowUpTimes")
    public ResponseEntity<?> getEnquiryFollowUpTimes(@RequestParam String enquiryNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get  number of times enquiry follow");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(enquiryFollowUpRepo.salesAndPreSales_enquiryFollowUp_getFollowUpTimes(enquiryNumber));
        return ResponseEntity.ok(apiResponse);

    }


}
