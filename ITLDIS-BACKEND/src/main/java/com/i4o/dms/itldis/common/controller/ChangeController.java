package com.i4o.dms.itldis.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * Change Controller
 * Handles change/password change operations
 * Reference: action.ChangeAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/change")
public class ChangeController {
    
    private static final Logger logger = LoggerFactory.getLogger(ChangeController.class);
    
    /**
     * Change Password
     * Reference: ChangeAction
     */
    @GetMapping("/password")
    public ResponseEntity<ApiResponse> changePassword(@RequestParam String oldPassword,
                                                       @RequestParam String newPassword) {
        try {
            // TODO: Implement password change logic
            // Reference: ChangeAction
            return ResponseEntity.ok(ApiResponse.success("Password changed successfully"));
        } catch (Exception e) {
            logger.error("Error changing password", e);
            return ResponseEntity.ok(ApiResponse.error("Error changing password: " + e.getMessage()));
        }
    }
}
