package com.i4o.dms.itldis.masters.service.serviceactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name="sv_mt_activity_type_heads")
public class    ServiceMtActivityTypeHeads
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "head can't be blank")
    private String head;

    @NotBlank(message = "sequence number can't be blank")
    private Integer sequenceNo;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
     @JsonBackReference
    private ServiceMtActivityType serviceMtActivityType;
}
