//package com.i4o.dms.itldis.salesandpresales.schemes.controller;
//
//import com.i4o.dms.itldis.salesandpresales.schemes.repository.IncentiveSchemeMasterRepo;
//
//import com.i4o.dms.itldis.utils.ApiResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.i4o.dms.itldis.configurations.Constants.MESSAGE;
//import static com.i4o.dms.itldis.configurations.Constants.RESPONSE;
//
//
//@RestController
//@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
//        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
//@RequestMapping("/api/salesPreSales/scheme/incentiveSchemeMaster")
//public class IncentiveSchemeMasterController {
//
//    @Autowired
//    private IncentiveSchemeMasterRepo incentiveSchemeMasterRepo;
//
//     private Map<String, Object> map = new HashMap<>();
//
//
//
//    @Deprecated
//    @GetMapping(value = "/searchItem")
//    public ResponseEntity<?> searchItem(@RequestParam(required = false) String item) {
//        ApiResponse apiResponse = new ApiResponse();
//        apiResponse.setMessage("Item list.");
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setResult(incentiveSchemeMasterRepo.searchItem(item));
//        return ResponseEntity.ok(apiResponse);
//    }
//
////    @GetMapping(value = "/getSchemeTypeDropDown")
////    public ResponseEntity<?> getSchemeTypeDropDown(){
////        map.clear();
////
////        map.put(MESSAGE,"Policy Name List");
////        map.put(RESPONSE,incentiveSchemeMasterRepo.getSchemeType());
////        return ResponseEntity.ok(map);
////    }
//
//    @Deprecated
//   @GetMapping(value = "/getProductDropDown")
//    public ResponseEntity<?> getProductDropDown(){
//        map.clear();
//        map.put(MESSAGE,"Product Name List");
//        map.put(RESPONSE,incentiveSchemeMasterRepo.getProduct());
//        return ResponseEntity.ok(map);
//    }
//
//
//
//}
