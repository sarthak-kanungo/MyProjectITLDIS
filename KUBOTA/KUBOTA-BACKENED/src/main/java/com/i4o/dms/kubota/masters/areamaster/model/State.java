package com.i4o.dms.kubota.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CM_GEO_STATE")
@Getter
@Setter
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state;
    private String activeStatus = "Y";

    @ManyToOne
    @JsonBackReference
    private Country country;

    @OneToMany(mappedBy = "state")
    @JsonManagedReference
    private Set<District> districts;

}



