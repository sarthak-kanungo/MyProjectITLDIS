package com.i4o.dms.kubota.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CM_GEO_PIN")
@Getter
@Setter
public class PostOffices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postOffice;
    @ManyToOne
    @JsonBackReference
    private PinCode pinCode;

}
