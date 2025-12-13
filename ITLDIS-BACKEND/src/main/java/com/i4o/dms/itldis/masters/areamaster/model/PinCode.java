package com.i4o.dms.itldis.masters.areamaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CM_GEO_PIN")
@Getter
@Setter
public class PinCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 1)
    private String activeStatus;

    private Integer pinCode;

    @ManyToOne
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "pinCode")
    @JsonManagedReference
    private List<PostOffices> postOffice;

}
