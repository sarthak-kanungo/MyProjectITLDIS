package com.i4o.dms.itldis.masters.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.i4o.dms.itldis.masters.customer.domain.Customer;
import java.util.List;

/**
 * Customer Repository
 * TODO: Add custom query methods based on legacy DAO methods
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    /**
     * Find customer by customer ID
     */
    Customer findByCustomerId(String customerId);
    
    /**
     * Find customers by dealer code
     */
    List<Customer> findByDealerCode(String dealerCode);
    
    /**
     * Search customers
     */
    @Query("SELECT c FROM Customer c WHERE c.dealerCode = :dealerCode AND " +
           "(c.customerId LIKE %:searchTerm% OR c.customerName LIKE %:searchTerm% OR " +
           "c.mobileNo LIKE %:searchTerm%)")
    List<Customer> searchCustomers(@Param("dealerCode") String dealerCode,
                                   @Param("searchTerm") String searchTerm);
}
