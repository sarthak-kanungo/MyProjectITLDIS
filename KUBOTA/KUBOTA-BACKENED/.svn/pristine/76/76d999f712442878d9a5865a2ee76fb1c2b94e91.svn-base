package com.i4o.dms.kubota.service.jobcard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "service_jobcard_mechanic_concern")
public class MechanicConcerns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String mechanicObservation;

    @NotNull(message = "is selected can't not be null")
    private Boolean isSelected=false;


//    @ManyToOne
//    @JsonBackReference
//    private ServiceJobCard serviceJobCard;

}
