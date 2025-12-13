package com.i4o.dms.itldis.masters.products.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate"}, allowSetters = true)
public class ModelMaster {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "model cannot blank")
    @Column(unique = true)
    private String model;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private Character activeStatus;

}
