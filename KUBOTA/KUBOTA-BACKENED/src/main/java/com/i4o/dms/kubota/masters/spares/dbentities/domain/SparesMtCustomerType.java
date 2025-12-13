package com.i4o.dms.kubota.masters.spares.dbentities.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
public class SparesMtCustomerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    @NotNull(message = "Customer Type is mandatory")
    private String customerType;


}
