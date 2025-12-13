package com.i4o.dms.kubota.masters.service.serviceactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name="sv_mt_sub_activity_type")
public class ServiceMtSubActivityType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Sub Activity can't be blank")
    private String subActivity;

    @Column(length = 1)
    private String optionalActivity;

    @NotBlank(message = "sequence no can't be blank")
    private String sequenceNo;

//    private Double approvedAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    @JsonBackReference
    private ServiceMtActivityType serviceMtActivityType;

}
