package com.i4o.dms.kubota.masters.potype.controller;

import com.i4o.dms.kubota.masters.potype.domain.PurchaseOrderType;
import com.i4o.dms.kubota.masters.potype.repository.PurchaseOrderTypeRepository;
import com.i4o.dms.kubota.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/purchaseOrderType")
public class PurchaseOrderTypeController {

    @Autowired
    private PurchaseOrderTypeRepository purchaseOrderTypeRepository;


    @PostMapping(value = "/savePurchaseOrderType")
    public ResponseEntity<?> savePurchaseOrderType(@RequestBody PurchaseOrderType purchaseOrderType) {
        ApiResponse apiResponse = new ApiResponse();
        purchaseOrderTypeRepository.save(purchaseOrderType);
        apiResponse.setMessage("Purchase Order Type Successfully Saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getPurchaseOrderType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Purchase order Type get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(purchaseOrderTypeRepository.getPurchaseOrderType());
        return ResponseEntity.ok(apiResponse);
    }
}
