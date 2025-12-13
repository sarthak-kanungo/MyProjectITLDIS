package com.i4o.dms.itldis.masters.sales.controller;

import com.i4o.dms.itldis.masters.dealermaster.customermaster.domain.CustomerMaster;
import com.i4o.dms.itldis.masters.sales.domain.Sales;
import com.i4o.dms.itldis.masters.sales.repository.SalesRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.i4o.dms.itldis.utils.Constants.SUCCESS;
import static com.i4o.dms.itldis.utils.Constants.STATUS;
import static com.i4o.dms.itldis.utils.Constants.MESSAGE;
import static com.i4o.dms.itldis.utils.Constants.RESULT;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("api/sales")
public class SalesController {

    @Autowired
    private SalesRepo salesRepo;

    @PostMapping(value = "/addSales")
    public ResponseEntity<?> addSales(@RequestBody Sales sales) {
        ApiResponse apiResponse = new ApiResponse();
        Sales sales1 = salesRepo.save(sales);
        apiResponse.setMessage("Sales Added Successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sales1);
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getSalesSearch")
    public ResponseEntity<?> getSalesSearch(@RequestParam(required = false) String chassisNo,
                                            @RequestParam(required = false) String customerCode,
                                            @RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "10") Integer size) {


        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Sales Search get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(salesRepo.getSalesSearch(chassisNo,customerCode, page, size));
        //Integer getEnquirySearchCount = enquiryRepo.getEnquirySearchCount(enquiryNumber, enquiryType, model, salesPerson, fromDate, toDate, source, enquiryStatus, retailConversionActivity, product, series, subModel, variant, itemNo, finance, autoClose, subSidy, exchange, nextFollowFromDate, nextFollowToDate, tentativePurchaseFromDate, tentativePurchaseToDate, page, size);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("data get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(salesRepo.getById(id));
        return ResponseEntity.ok(apiResponse);
    }

}



