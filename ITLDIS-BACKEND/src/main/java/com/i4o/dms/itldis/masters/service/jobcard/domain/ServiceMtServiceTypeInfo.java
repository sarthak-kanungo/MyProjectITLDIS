package com.i4o.dms.itldis.masters.service.jobcard.domain;

import com.i4o.dms.itldis.masters.products.domain.ModelMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "SV_MT_SV_TYPE_INFO")
public class ServiceMtServiceTypeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "service type can't not be bank")
    @Size(max = 100)
    private String serviceType;

    /*@ManyToOne
    @JoinColumn(name = "model_id",nullable = false)
    private ModelMaster modelMaster;

    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private ServiceMtCategory serviceMtCategory;


    @NotBlank(message = "service  hours can't not be bank")
    private Integer serviceHours;

    @NotBlank(message = "max hours can't not be bank")
    private Integer maxHours;

    @NotBlank(message = "service days can't not be bank")
    private Integer serviceDays;

    @NotBlank(message = "claim days can't not be bank")
    private Integer claimDays;

    @NotBlank(message = "max days can't not be bank")
    private Integer maxDays;

    private Date lastModifiedDate=new Date();*/

}
