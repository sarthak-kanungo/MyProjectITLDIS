package com.i4o.dms.itldis.masters.spares.dbentities.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class SparesMtPriceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "price type is mandatory")
    @Column(length = 50,unique = true)
    private String priceType;

}
