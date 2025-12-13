package com.i4o.dms.itldis.masters.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.i4o.dms.itldis.masters.customer.dto.CustomerRequestDto;
import com.i4o.dms.itldis.masters.customer.dto.CustomerResponseDto;
import com.i4o.dms.itldis.masters.customer.service.CustomerService;
import com.i4o.dms.itldis.utils.ApiResponse;

/**
 * Customer Management Controller
 * Handles customer creation, modification, and management
 * Reference: action.ManageCustomerAction
 */
@RestController
@CrossOrigin(allowedHeaders = { "Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization" }, 
    methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping(value = "/api/masters/customer")
public class CustomerController {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerService customerService;
    
    /**
     * Add Customer
     * Reference: ManageCustomerAction.addCustomer
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerRequestDto request) {
        try {
            CustomerResponseDto response = customerService.addCustomer(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error adding customer", e);
            return ResponseEntity.ok(ApiResponse.error("Error adding customer: " + e.getMessage()));
        }
    }
    
    /**
     * Modify Customer
     * Reference: ManageCustomerAction.modifyCustomer
     */
    @PutMapping("/modify")
    public ResponseEntity<ApiResponse> modifyCustomer(@RequestBody CustomerRequestDto request) {
        try {
            CustomerResponseDto response = customerService.modifyCustomer(request);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error modifying customer", e);
            return ResponseEntity.ok(ApiResponse.error("Error modifying customer: " + e.getMessage()));
        }
    }
    
    /**
     * Get Customer List
     * Reference: ManageCustomerAction.getCustomerList
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getCustomerList(@RequestParam(required = false) String dealerCode,
                                                       @RequestParam(required = false) String searchTerm) {
        try {
            CustomerResponseDto response = customerService.getCustomerList(dealerCode, searchTerm);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting customer list", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting customer list: " + e.getMessage()));
        }
    }
    
    /**
     * Get Customer Details
     * Reference: ManageCustomerAction.getCustomerDetails
     */
    @GetMapping("/details")
    public ResponseEntity<ApiResponse> getCustomerDetails(@RequestParam String customerId) {
        try {
            CustomerResponseDto response = customerService.getCustomerDetails(customerId);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error getting customer details", e);
            return ResponseEntity.ok(ApiResponse.error("Error getting customer details: " + e.getMessage()));
        }
    }
    
    /**
     * Search Customer
     */
    @GetMapping("/search")
    public ResponseEntity<ApiResponse> searchCustomer(@RequestParam String searchTerm) {
        try {
            CustomerResponseDto response = customerService.searchCustomer(searchTerm);
            return ResponseEntity.ok(ApiResponse.success(response));
        } catch (Exception e) {
            logger.error("Error searching customer", e);
            return ResponseEntity.ok(ApiResponse.error("Error searching customer: " + e.getMessage()));
        }
    }
}
