package com.i4o.dms.kubota.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "CM_GEO_COUNTRY")

public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String activeStatus = "Y";

    @OneToMany(mappedBy = "country")
    @JsonManagedReference
    private Set<State> states;


}
