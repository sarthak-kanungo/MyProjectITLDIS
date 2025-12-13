package com.i4o.dms.kubota.masters.dealermaster.customermaster.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "customer_crop")
public class CustomerCrops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @Size(min = 2, max = 50)
    private String cropName;
    @Column(precision = 3, length = 30)
    private Double acres;
    @Column(precision = 3, length = 30)
    private Double qtyPerAcre;
    @Column(precision = 3, length = 30)
    private Double sellingPrice;

    @ManyToOne
    @JoinColumn(name = "customer_master_id", referencedColumnName = "CustomerCode")
    @JsonBackReference
    private CustomerMaster customerMaster;

}
