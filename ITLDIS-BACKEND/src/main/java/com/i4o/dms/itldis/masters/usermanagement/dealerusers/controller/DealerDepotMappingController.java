//package com.i4o.dms.itldis.masters.usermanagement.dealerusers.controller;
//
//
//import com.i4o.dms.itldis.utils.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//
//public class DealerDepotMappingController {
//
//    @GetMapping()
//    public ResponseEntity<?> getFollowUpType() {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Depot get successfully.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(branchDepotMasterRepository.getAllDepot());
//        return ResponseEntity.ok(apiResponse);
//    }
//}
