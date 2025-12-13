package com.i4o.dms.kubota.masters.service.serviceactivityproposal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "SV_MT_ACTIVITY_TYPE")
public class ServiceMtActivityType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "activity type can't be blank")
    private String activityType;

    @NotBlank(message = "sequence number can't be blank")
    private Integer sequenceNo;

    @NotBlank(message = "allowed budget can't be blank")
    private Double maxAllowedBudget;

    @OneToMany(mappedBy = "serviceMtActivityType",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceMtActivityTypeHeads> serviceMtsHeadsList;

    @OneToMany(mappedBy = "serviceMtActivityType",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceMtSubActivityType> serviceMtSubActivityTypeList;

}
