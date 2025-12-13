package com.i4o.dms.itldis.eamg.servicebulletin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.i4o.dms.itldis.eamg.servicebulletin.dto.ServiceBulletinRequestDto;
import com.i4o.dms.itldis.eamg.servicebulletin.dto.ServiceBulletinResponseDto;
import com.i4o.dms.itldis.eamg.servicebulletin.service.EamgServiceBulletinService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * EAMG Service Bulletin Controller
 * Handles Service Bulletin operations
 * Reference: EAMG_Service_Bulletin.EAMG_ServiceBulletinAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/eamg/service-bulletin")
public class EamgServiceBulletinController {
    
    private static final Logger logger = LoggerFactory.getLogger(EamgServiceBulletinController.class);
    
    @Autowired
    private EamgServiceBulletinService serviceBulletinService;
    
    /**
     * Initialize Service Bulletin Update
     * Reference: option="initSBUpdate"
     */
    @GetMapping("/init-update")
    public ResponseEntity<ApiResponse> initServiceBulletinUpdate() {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.initUpdate();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error initializing service bulletin update", e);
            return ResponseEntity.ok(ApiResponse.error("Error initializing update: " + e.getMessage()));
        }
    }
    
    /**
     * Insert/Update Service Bulletin
     * Reference: option="insertUpdateSB"
     */
    @PostMapping("/insert-update")
    public ResponseEntity<ApiResponse> insertUpdateServiceBulletin(
            @RequestPart("data") ServiceBulletinRequestDto request,
            @RequestPart(value = "files", required = false) MultipartFile[] files) {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.insertUpdateServiceBulletin(request, files);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error inserting/updating service bulletin", e);
            return ResponseEntity.ok(ApiResponse.error("Error inserting/updating: " + e.getMessage()));
        }
    }
    
    /**
     * View Bulletin
     * Reference: option="viewBulletin"
     */
    @GetMapping("/view")
    public ResponseEntity<ApiResponse> viewBulletin(@RequestParam String bulletinNo) {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.viewBulletin(bulletinNo);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error viewing bulletin", e);
            return ResponseEntity.ok(ApiResponse.error("Error viewing bulletin: " + e.getMessage()));
        }
    }
    
    /**
     * View Bulletin Details
     * Reference: option="viewBulletinDetails"
     */
    @GetMapping("/view-details")
    public ResponseEntity<ApiResponse> viewBulletinDetails(@RequestParam String type, 
                                                           @RequestParam String yearOfIssue) {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.viewBulletinDetails(type, yearOfIssue);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error viewing bulletin details", e);
            return ResponseEntity.ok(ApiResponse.error("Error viewing bulletin details: " + e.getMessage()));
        }
    }
    
    /**
     * Initialize Service Bulletin Delete
     * Reference: option="initSBDelete"
     */
    @GetMapping("/init-delete")
    public ResponseEntity<ApiResponse> initServiceBulletinDelete() {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.initDelete();
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error initializing service bulletin delete", e);
            return ResponseEntity.ok(ApiResponse.error("Error initializing delete: " + e.getMessage()));
        }
    }
    
    /**
     * Delete View Bulletin
     * Reference: option="deleteViewBulletin"
     */
    @GetMapping("/delete-view")
    public ResponseEntity<ApiResponse> deleteViewBulletin(@RequestParam String type, 
                                                          @RequestParam String yearOfIssue) {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.getBulletinsForDelete(type, yearOfIssue);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting bulletins for delete", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting bulletins: " + e.getMessage()));
        }
    }
    
    /**
     * Delete Bulletin
     * Reference: option="deleteViewBulletinDetails"
     */
    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteBulletin(@RequestBody ServiceBulletinRequestDto request) {
        try {
            ServiceBulletinResponseDto response = serviceBulletinService.deleteBulletin(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error deleting bulletin", e);
            return ResponseEntity.ok(ApiResponse.error("Error deleting bulletin: " + e.getMessage()));
        }
    }
    
    /**
     * Download Service Bulletin File
     * Reference: EAMG_Service_Bulletin.FileDownloadServlet
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadBulletinFile(@RequestParam String bulletinNo, 
                                                       @RequestParam String fileName) {
        try {
            byte[] fileData = serviceBulletinService.downloadBulletinFile(bulletinNo, fileName);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(fileData);
        } catch (Exception e) {
            logger.error("Error downloading bulletin file", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
