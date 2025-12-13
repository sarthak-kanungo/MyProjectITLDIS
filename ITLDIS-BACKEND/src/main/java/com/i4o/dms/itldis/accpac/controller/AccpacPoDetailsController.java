package com.i4o.dms.itldis.accpac.controller;

import com.i4o.dms.itldis.accpac.domain.AccpacPoDetails;
import com.i4o.dms.itldis.accpac.repository.AccpacPoDetailsRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/accpac")
public class AccpacPoDetailsController {


    @Autowired
    private AccpacPoDetailsRepo accpacPoDetailsRepo;

    @PostMapping(value = "/saveaccpacpodetails")
    public ResponseEntity<?> saveaccpacpodetails(@RequestBody AccpacPoDetails accpacPoDetails) {
        ApiResponse apiResponse = new ApiResponse();
        accpacPoDetailsRepo.save(accpacPoDetails);
        apiResponse.setMessage("accpac po details successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getOsStatus")
    public ResponseEntity<?> getOsStatus(@RequestParam(required = false) String dealerCode,@RequestParam(required=false)String poid)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("outstanding details get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        if(dealerCode==null || dealerCode.equals("") || dealerCode.equals("null"))
        	dealerCode = null;
        if(poid==null || poid.equals("") || poid.equals("null"))
        	poid = null;
        apiResponse.setResult(accpacPoDetailsRepo.getOsStatus(dealerCode,(poid==null?null:Long.valueOf(poid))));
        return ResponseEntity.ok(apiResponse);
    }

}
