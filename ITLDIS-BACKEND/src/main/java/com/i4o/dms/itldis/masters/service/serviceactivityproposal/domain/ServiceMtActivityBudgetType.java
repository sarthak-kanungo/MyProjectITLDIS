package com.i4o.dms.itldis.masters.service.serviceactivityproposal.domain;

import com.i4o.dms.itldis.masters.service.serviceactivityproposal.domain.ServiceMtActivityType;
import com.i4o.dms.itldis.masters.usermanagement.kubotausers.domain.KubotaEmployeeMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Entity
public class ServiceMtActivityBudgetType
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date createdDate = new Date();

    @NotBlank(message = "number of machines can't be blank")
    private Integer noOfMachines;

    @NotBlank(message = "no of persons can't blank")
    private Integer noOfPersons;

    @NotBlank(message = "no of camps can't blank")
    private Integer noOfCamps;

    private Double reimbursementPerCamp;

    @NotBlank(message = "expenditure percentage can't be blank")
    private Double expenditurePercent;

    @NotBlank(message = "active status can't be blank")
    private String activeStatus;

    private Double perMachineCost;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private KubotaEmployeeMaster kubotaEmployeeMaster;

    @ManyToOne
    @JoinColumn(name = "activity_type")
    private ServiceMtActivityType serviceMtActivityType;

}
