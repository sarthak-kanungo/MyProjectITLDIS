package com.i4o.dms.itldis.masters.service.serviceactivityproposal.controller;


import com.i4o.dms.itldis.masters.service.serviceactivityproposal.repository.ActivityProposalMtRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "/api/service/activityProposal")
public class ServiceActivityProposalMasterController
{
    @Autowired
    private ActivityProposalMtRepo activityProposalMtRepo;

    @GetMapping("/getSubActivityByActivityTypeId")
    public ResponseEntity<?> getSubActivityByActivityType(@RequestParam Long activityTypeId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity Proposal  get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityProposalMtRepo.getSubActivity(activityTypeId));
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/getAllHeadsByActivityTypeId")
    public ResponseEntity<?> getAllHeadsByActivityType(@RequestParam Long activityTypeId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Heads get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityProposalMtRepo.getHeadsOnActivityType(activityTypeId));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/sbjc/getActiveActivityType")
    public ResponseEntity<?> getAllActivityType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Activity Type get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(activityProposalMtRepo.getAllActivityType());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct()
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("All Product get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult( activityProposalMtRepo.getAllProductFromProductMaster());
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getMaxAllowedBudgetByNumberPerson")
    public ResponseEntity<?> getMaxAllowedBudgetByNumberPerson(@RequestParam Integer numberOfPerson)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Maximum Allowed Budget By Number Of Person get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult( activityProposalMtRepo.getMAxAllowedBudgetByPerson(numberOfPerson));
        return ResponseEntity.ok(apiResponse);
    }

}
