package com.i4o.dms.itldis.masters.spares.dbentities.controller;


import com.i4o.dms.itldis.masters.spares.dbentities.repository.OrderTypeRepository;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/dbEntities")
public class OrderTypeContoller {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getCanonicalName());

    @Autowired
    private OrderTypeRepository sparePartRepository;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @GetMapping(value = "/getOrderTypeDropdown")
    public ResponseEntity<?> getOrderTypeDropdown() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Order Type Dropdown list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getOrderTypeDropdown());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getTransferModeDropdown")
    public ResponseEntity<?> getTransferModeDropdown() {
    ApiResponse  apiResponse=new ApiResponse();

        apiResponse.setMessage("Transfer Mode Dropdown list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getTransferModeDropdown());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getTransporterDropdown")
    public ResponseEntity<?> getTransporterDropdown() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Transporter  Dropdown list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getTransporterDropdown());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getPurchaseOrderStatusDropdown")
    public ResponseEntity<?> getPurchaseOrderStatus() {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<List<Map<String, Object>>>();
        apiResponse.setMessage("Purchase Order status Dropdown list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getPurchaseOrderStatus());
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/getSupplierNameAutoComplete")
    public ResponseEntity<?> getSupplierNameAutoComplete(@RequestParam String  supplierName) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Supplier Name Auto Complete list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getSupplierName(supplierName));
        return ResponseEntity.ok(apiResponse);
    }
    
    @GetMapping(value = "/getVendorNameAutoComplete")
    public ResponseEntity<?> getVendorNameAutoComplete(@RequestParam String  vendorName) {
        ApiResponse<List<Map<String, Object>>> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Vendor Name Auto Complete list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getVendorName(vendorName, userAuthentication.getDealerId()));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSparePartItemDetailsForMrc")
    public ResponseEntity<?> getSparePartItemDetailsForMrc(@RequestParam String  itemNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Part Item Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getSparePartItemDetailsForMrc(itemNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSparePriceTypeDropdown")
    public ResponseEntity<?> getSparePriceType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("spare price type list");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getSparesPriceTypeDropdown());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSpareCustomerTypeDropdown")
    public ResponseEntity<?> getSpareCustomerTypeDropdown(@RequestParam String documentType) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Customer Type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getSparesCustomerTypeDropdown(documentType));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSparePoType")
    public ResponseEntity<?> getSparePoType() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Po Type");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparePartRepository.getSparesPoTypeDropdown());
        return ResponseEntity.ok(apiResponse);
    }
}
