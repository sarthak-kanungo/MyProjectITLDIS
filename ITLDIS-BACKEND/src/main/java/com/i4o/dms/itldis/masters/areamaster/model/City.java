package com.i4o.dms.itldis.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CM_GEO_CITY")
@Getter
@Setter
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String activeStatus = "Y";
    @ManyToOne
    @JsonBackReference
    private Tehsil tehsil;
    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private Set<PinCode> pinCode;
}
