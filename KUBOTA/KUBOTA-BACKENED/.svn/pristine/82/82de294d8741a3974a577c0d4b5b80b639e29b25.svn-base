package com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.controller;


import com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.domain.IncentiveSchemeMaster;
import com.i4o.dms.kubota.masters.salesandpresales.schemes.incentiveSchemeMaster.repository.IncentiveSchemeRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.i4o.dms.kubota.configurations.Constants.MESSAGE;
import static com.i4o.dms.kubota.configurations.Constants.RESPONSE;

@RestController
@RequestMapping("api/salesandpresales/incentiveScheme")
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
public class IncentiveSchemeController {

    @Autowired
    private IncentiveSchemeRepo incentiveSchemeRepo;

    @PostMapping("/saveIncentiveScheme")
    public ResponseEntity<?> saveIncentiveSchemeMaster(@RequestBody IncentiveSchemeMaster incentiveSchemeMaster) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Scheme master Saved Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(incentiveSchemeRepo.save(incentiveSchemeMaster));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/schemeTypeDropdown")
    public ResponseEntity<?> schemeTypeDropdown() {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Scheme Type Get Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(incentiveSchemeRepo.schemeType());
        return ResponseEntity.ok(apiResponse);
    }


}

