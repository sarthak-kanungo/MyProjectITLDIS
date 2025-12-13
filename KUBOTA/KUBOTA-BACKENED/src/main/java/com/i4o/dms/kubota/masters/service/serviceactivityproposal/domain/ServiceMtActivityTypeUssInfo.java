package com.i4o.dms.kubota.masters.service.serviceactivityproposal.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ServiceMtActivityTypeUssInfo
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer noOfPerson;

    private Double allowedBudget;

    @ManyToOne
    @JoinColumn(name = "activity_budget_type_id")
    private ServiceMtActivityBudgetType serviceMtActivityBudgetType;

}
