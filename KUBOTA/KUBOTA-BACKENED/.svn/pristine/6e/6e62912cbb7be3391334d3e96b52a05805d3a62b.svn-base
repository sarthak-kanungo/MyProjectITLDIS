package com.i4o.dms.kubota.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CM_GEO_TEHSIL")
@Getter
@Setter
public class Tehsil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tehsil;
    private String activeStatus = "Y";
    @ManyToOne
    @JsonBackReference
    private District district;


    @OneToMany(mappedBy = "tehsil")
    @JsonManagedReference
    private Set<City> city;

}
