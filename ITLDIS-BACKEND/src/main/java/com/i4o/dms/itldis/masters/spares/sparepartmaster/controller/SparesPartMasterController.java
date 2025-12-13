package com.i4o.dms.itldis.masters.spares.sparepartmaster.controller;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.repository.SparesPartMasterRepo;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.repository.DealerEmployeeMasterRepo;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.security.service.UserAuthentication;
import com.i4o.dms.itldis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"}, methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = "/api/spares/sparePartMaster")
public class SparesPartMasterController {

    @Autowired
    private SparesPartMasterRepo sparesPartMasterRepo;

    @Autowired
    private UserAuthentication userAuthentication;
    
    @Autowired
    private DealerEmployeeMasterRepo dealerEmpRepo;

    @GetMapping(value = "/getSparePartItemDetailsForMrc")
    public ResponseEntity<?> getSparePartItemDetailsForMrc(@RequestParam String itemNumber) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Part Item Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparesPartMasterRepo.getSparePartItemDetailsForMrc(itemNumber));
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/autocompletePartNo")
    public ResponseEntity<?> autoCompletePartNumber(@RequestParam String itemNumber,
                                                    @RequestParam(required = false) String itemId) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Part Item Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        List<Map<String, Object>>list= sparesPartMasterRepo.autoCompleteSparePartNumber(itemNumber, itemId, userAuthentication.getDealerId());
        apiResponse.setResult(list);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/getSparePartDetailsForQuotation")
    public ResponseEntity<?> getSparePartDetailsForQuotation(@RequestParam String itemNumber,
                                                             @RequestParam String state) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Spare Part Item Details");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sparesPartMasterRepo.getSparePartDetailsForQuotation(
                itemNumber, userAuthentication.getBranchId(), state));
        return ResponseEntity.ok(apiResponse);
    }
}
