package com.i4o.dms.kubota.masters.oldcustomerdetail.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "customer_machinery_detail")
public class CustomerMachineryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String brand;
    @Column(length = 60)
    private String model;
    @Column(length = 10)
    private String yearOfPurchase;

    @ManyToOne
    @JsonBackReference
    private OldCustomer oldCustomer;
}
