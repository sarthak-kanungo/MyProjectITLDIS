package com.i4o.dms.kubota.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CM_GEO_DISTRICT")
@Getter
@Setter
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String district;
    private String activeStatus = "Y";
    @ManyToOne
    @JsonBackReference
    private State state;
    @OneToMany(mappedBy = "district")
    @JsonManagedReference
    private Set<Tehsil> tehsils;

}
