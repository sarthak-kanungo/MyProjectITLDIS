package com.i4o.dms.kubota.salesandpresales.marketingactivity.marketingactivityproposal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.kubota.common.model.SystemLookUpEntity;
import com.i4o.dms.kubota.common.sys.controller.SysLookupRepo;
import com.i4o.dms.kubota.constant.model.DmsConstants;
import com.i4o.dms.kubota.utils.ApiResponse;

@RestController
@CrossOrigin(allowedHeaders = {"Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization"},
        methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/salesandpresales/marketingActivityStatus")
public class MarketingActivityStatusController {

    @Autowired
    private SysLookupRepo sysLookupRepo;

    @PostMapping(value = "/saveMarketingActivityStatus")
    public ResponseEntity<?> saveMarketingActivityStatus(@RequestBody SystemLookUpEntity systemLookUpEntity)
    {
        ApiResponse apiResponse = new ApiResponse();
        sysLookupRepo.save(systemLookUpEntity);
        apiResponse.setMessage("marketing activity status successfully saved.");
        apiResponse.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getMarketingActivityStatus() {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("status get successfully.");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setResult(sysLookupRepo.findByLookuptypecode(DmsConstants.SM_ACTIVITY_STATUS));
        return ResponseEntity.ok(apiResponse);
    }
}
