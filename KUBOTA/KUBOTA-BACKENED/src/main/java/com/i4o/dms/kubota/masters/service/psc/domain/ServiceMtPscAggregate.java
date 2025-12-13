package com.i4o.dms.kubota.masters.service.psc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class ServiceMtPscAggregate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
    @NotBlank(message = "aggregate can't be null")
    private String aggregate;

    @NotNull
    @NotBlank(message = "active status can't be blank")
    private String activeStatus;

}
