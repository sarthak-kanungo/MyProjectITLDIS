package com.i4o.dms.kubota.masters.warranty.controller;

import com.i4o.dms.kubota.masters.warranty.repository.WarrantyMtPartFailureCodeRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/warranty")
public class WarrantyMtPartFailureCodeController {

    @Autowired
    private WarrantyMtPartFailureCodeRepo warrantyMtPartFailureCodeRepo;

    @GetMapping("/autoCompletePartFailureCode")
    public ResponseEntity<?> autoCompletePartFailureCode(@RequestParam(required = true) Long machineMasterId,@RequestParam String code)
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(warrantyMtPartFailureCodeRepo.autoCompletePartFailureCode(machineMasterId,code));
        apiResponse.setMessage("get auto complete part failure code");
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping("/getPartFailureCodeByCode")
    public ResponseEntity<?> getPartFailureCodeByCode(@RequestParam String code)
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(warrantyMtPartFailureCodeRepo.getPartFailureCode(code));
        apiResponse.setMessage("get part failure code and desc complete");
        return ResponseEntity.ok(apiResponse);
    }
}
