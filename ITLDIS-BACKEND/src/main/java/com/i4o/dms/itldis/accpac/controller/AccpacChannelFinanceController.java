package com.i4o.dms.itldis.accpac.controller;

import com.i4o.dms.itldis.accpac.domain.AccpacChannelFinanceInvoice;
import com.i4o.dms.itldis.accpac.repository.InvoiceDetailsRepo;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/accpac/invoiceDetails")
public class AccpacChannelFinanceController {

    @Autowired
    private InvoiceDetailsRepo invoiceDetailsRepo;

    @PostMapping(value = "/saveInvoiceDetails")
    public ResponseEntity<?> saveInvoiceDetails(@RequestBody AccpacChannelFinanceInvoice accpacChannelFinanceInvoice) {
        ApiResponse apiResponse = new ApiResponse();
        invoiceDetailsRepo.save(accpacChannelFinanceInvoice);
        apiResponse.setMessage("invoice details successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

}
//SB/2022/2/MH/350043