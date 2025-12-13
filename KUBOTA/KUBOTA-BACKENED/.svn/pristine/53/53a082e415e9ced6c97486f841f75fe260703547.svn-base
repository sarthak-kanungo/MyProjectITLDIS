package com.i4o.dms.kubota.service.jobcard.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "service_jobcard_customer_concern")
public class CustomerConcerns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerConcern;

    @NotNull(message = "is selected can't not be null")
    private Boolean isSelected=false;
//    @ManyToOne
//    @JsonBackReference
//    private ServiceJobCard serviceJobCard;
}
