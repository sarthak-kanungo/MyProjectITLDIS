package com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.controller;

import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.domain.DealerBankDetails;
import com.i4o.dms.kubota.salesandpresales.purchase.purchaseOrder.repository.DealerBankDetailsRepo;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/purchaseOrder/dealerbankdetails")
public class DealerBankDetailsController {

    @Autowired
    private DealerBankDetailsRepo dealerBankDetailsRepo;

    @PostMapping(value = "/saveDealerBankDetails")
    public ResponseEntity<?> saveDealerBankDetails(@RequestBody DealerBankDetails dealerBankDetails) {
        ApiResponse apiResponse = new ApiResponse();
        DealerBankDetails dealerBankDetails1=dealerBankDetailsRepo.save(dealerBankDetails);
        dealerBankDetails1.setAvailableAmount(dealerBankDetails.getCfCreditLimit()-dealerBankDetails1.getUtilisedLimit());
        dealerBankDetailsRepo.save(dealerBankDetails1);
        apiResponse.setMessage("dealer bank details successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getchannelfinanceavailable")
    public ResponseEntity<?> getchannelfinanceavailable(@RequestParam(required = false) String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("channel finance available get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getchannelfinanceavailable(dealerCode));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getDealerBankDetailsByBankAndDealer")
    public ResponseEntity<?> getDealerBankDetailsByBankAndDealer(@RequestParam String dealerCode,
                                                                 @RequestParam String bankName) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("details get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getDealerBankDetailsByBankAndDealer(dealerCode,bankName));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getBankListByDealer")
    public ResponseEntity<?> getBankListByDealer(@RequestParam(required = false) String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("dealer bank get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getBankListByDealer(dealerCode));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getDealerCategory")
    public ResponseEntity<?> getDealerCategory() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("get dealer category successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getDealerCategory());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBankList")
    public ResponseEntity<?> getBankList() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("banks get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getBankList());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getDealerAvailableCreditByDealerCode")
    public ResponseEntity<?> getDealerAvailableCreditByDealerCode(@RequestParam String dealerCode) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("details get successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(dealerBankDetailsRepo.getDealerBankAvailableForPo(dealerCode));
        return ResponseEntity.ok(apiResponse);
    }

}
