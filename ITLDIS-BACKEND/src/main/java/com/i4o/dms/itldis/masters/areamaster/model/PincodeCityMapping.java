package com.i4o.dms.itldis.masters.areamaster.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PincodeCityMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name="pincode_id")
    @ManyToOne
    private PinCode pinCode;

    @JoinColumn(name="city_id")
    @ManyToOne
    private City city;
}
