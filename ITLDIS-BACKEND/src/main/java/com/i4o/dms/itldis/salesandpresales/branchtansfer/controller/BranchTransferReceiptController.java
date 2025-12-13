package com.i4o.dms.itldis.salesandpresales.branchtansfer.controller;

import com.i4o.dms.itldis.salesandpresales.branchtansfer.domain.BranchTransferReceipt;
import com.i4o.dms.itldis.salesandpresales.branchtansfer.repository.BranchTransferReceiptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.i4o.dms.itldis.configurations.Constants.*;
import static com.i4o.dms.itldis.configurations.Constants.SUCCESS;

@RestController
@ResponseBody
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/branchTransferReceipt")
public class BranchTransferReceiptController {

    private Map<String, Object> map = new HashMap<>();

    @Autowired
    private BranchTransferReceiptRepo branchTransferReceiptRepo;

    @PostMapping
    public ResponseEntity<?> saveBranchTransferReceipt(@RequestBody BranchTransferReceipt branchTransferReceipt) {
        map.clear();
        map.put(MESSAGE, "Branch Transfer Receipt saved successfully");
        map.put(RESPONSE, branchTransferReceiptRepo.save(branchTransferReceipt));
        map.put(STATUS, SUCCESS);
        return ResponseEntity.ok(map);
    }


}
