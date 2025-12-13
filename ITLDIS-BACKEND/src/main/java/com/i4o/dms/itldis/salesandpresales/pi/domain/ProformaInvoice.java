package com.i4o.dms.itldis.salesandpresales.pi.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

/**
 * Proforma Invoice Entity
 * TODO: Map from HibernateMapping entities
 */
@Entity
@Table(name = "proforma_invoice")
@Data
public class ProformaInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "pi_no", unique = true)
    private String piNo;
    
    @Column(name = "dealer_code")
    private String dealerCode;
    
    @Column(name = "pi_date")
    private Date piDate;
    
    @Column(name = "customer_code")
    private String customerCode;
    
    @Column(name = "total_amount")
    private Double totalAmount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "updated_date")
    private Date updatedDate;
    
    // TODO: Add more fields based on HibernateMapping entities
}
