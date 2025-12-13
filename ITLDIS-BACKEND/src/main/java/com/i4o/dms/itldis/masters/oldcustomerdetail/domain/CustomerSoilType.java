package com.i4o.dms.itldis.masters.oldcustomerdetail.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "customer_soil_type")

public class CustomerSoilType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String soilName;

    @ManyToOne
    @JsonBackReference
    private OldCustomer oldCustomer;
}
