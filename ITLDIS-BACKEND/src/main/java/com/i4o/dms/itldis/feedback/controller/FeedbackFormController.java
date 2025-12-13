package com.i4o.dms.itldis.feedback.controller;

import com.i4o.dms.itldis.feedback.domain.FeedbackForm;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;

import com.i4o.dms.itldis.feedback.repository.FeedbackFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/feedback")
public class FeedbackFormController {

    @Autowired
    private FeedbackFormRepository feedbackFormRepository;
    @Autowired
    private UserAuthentication userAuthentication;
    @Autowired
    private DealerMasterRepo dealerMasterRepo;


    @PostMapping("addFeedbackForm")
    public ResponseEntity<?> addFeedbackForm(@RequestBody FeedbackForm feedbackForm) {
        feedbackForm.setDealerMaster(dealerMasterRepo.findById(userAuthentication.getDealerId()).get());
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("form submitted successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        feedbackFormRepository.save(feedbackForm);
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("getFeedbackList")
    public ResponseEntity<?> searchFeedback(@RequestParam("page") Integer page,@RequestParam("size") Integer size){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get feedback list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(feedbackFormRepository.searchFeedback(userAuthentication.getDealerId(),page,size));
        apiResponse.setCount(feedbackFormRepository.searchFeedbackCount(userAuthentication.getDealerId(),page,size));
        return ResponseEntity.ok(apiResponse);

    }

}
