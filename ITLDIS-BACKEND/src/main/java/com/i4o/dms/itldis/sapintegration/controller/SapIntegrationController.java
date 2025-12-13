package com.i4o.dms.itldis.sapintegration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.sapintegration.RemoteFunctionCallAPNService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * SAP Integration Controller
 * Handles SAP RFC calls and integration operations
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/sap")
public class SapIntegrationController {
    
    private static final Logger logger = LoggerFactory.getLogger(SapIntegrationController.class);
    
    @Autowired
    private RemoteFunctionCallAPNService remoteFunctionCallAPNService;
    
    /**
     * Sync APN (Alternate Part Number) data from SAP
     * 
     * @param date Optional date parameter
     * @return Response with number of rows synced
     */
    @PostMapping("/sync/apn")
    public ResponseEntity<ApiResponse> syncAPNData(@RequestParam(required = false) String date) {
        try {
            String syncDate = date != null ? date : new java.util.Date().toString();
            long rowsInserted = remoteFunctionCallAPNService.consumeABAPFMSAP(syncDate);
            
            return ResponseEntity.ok(ApiResponse.success(
                String.format("Successfully synced %d rows from SAP APN RFC", rowsInserted)
            ));
        } catch (Exception e) {
            logger.error("Error syncing APN data from SAP", e);
            return ResponseEntity.ok(ApiResponse.error("Error syncing APN data: " + e.getMessage()));
        }
    }
}

