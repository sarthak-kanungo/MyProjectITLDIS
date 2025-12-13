package com.i4o.dms.itldis.masters.customer.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Customer Entity
 * TODO: Map from HibernateMapping entities (CustomerMaster, etc.)
 */
@Entity
@Table(name = "customer_master")
@Data
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "customer_id", unique = true)
    private String customerId;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;
    
    @Column(name = "pin_code")
    private String pinCode;
    
    @Column(name = "mobile_no")
    private String mobileNo;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    
    @Column(name = "customer_category")
    private String customerCategory;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    // TODO: Add more fields based on HibernateMapping entities
}
