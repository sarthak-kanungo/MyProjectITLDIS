package com.i4o.dms.itldis.masters.postatus.controller;

import com.i4o.dms.itldis.masters.postatus.domain.PurchaseOrderStatus;
import com.i4o.dms.itldis.masters.postatus.repository.PurchaseOrderStatusRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/purchaseOrderStatus")
public class PurchaseOrderStatusController {

    @Autowired
    private PurchaseOrderStatusRepo purchaseOrderStatusRepo;

    @PostMapping(value = "/savePurchaseOrderStatus")
    public ResponseEntity<?> savePurchaseOrderStatus(@RequestBody PurchaseOrderStatus purchaseOrderStatus) {
        ApiResponse apiResponse = new ApiResponse();
        purchaseOrderStatusRepo.save(purchaseOrderStatus);
        apiResponse.setMessage("Purchase Order Status Successfully Saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getPurchaseOrderStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Purchase order Status get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(purchaseOrderStatusRepo.getPurchaseOrderStatus());
        return ResponseEntity.ok(apiResponse);
    }
}
