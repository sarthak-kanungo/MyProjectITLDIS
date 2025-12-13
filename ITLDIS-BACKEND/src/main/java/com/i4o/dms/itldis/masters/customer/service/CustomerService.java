package com.i4o.dms.itldis.masters.customer.service;

import com.i4o.dms.itldis.masters.customer.dto.CustomerRequestDto;
import com.i4o.dms.itldis.masters.customer.dto.CustomerResponseDto;

/**
 * Customer Service Interface
 * Reference: action.ManageCustomerAction
 */
public interface CustomerService {
    
    /**
     * Add Customer
     * Reference: ManageCustomerAction.addCustomer
     */
    CustomerResponseDto addCustomer(CustomerRequestDto request);
    
    /**
     * Modify Customer
     * Reference: ManageCustomerAction.modifyCustomer
     */
    CustomerResponseDto modifyCustomer(CustomerRequestDto request);
    
    /**
     * Get Customer List
     * Reference: ManageCustomerAction.getCustomerList
     */
    CustomerResponseDto getCustomerList(String dealerCode, String searchTerm);
    
    /**
     * Get Customer Details
     * Reference: ManageCustomerAction.getCustomerDetails
     */
    CustomerResponseDto getCustomerDetails(String customerId);
    
    /**
     * Search Customer
     */
    CustomerResponseDto searchCustomer(String searchTerm);
}
