package com.i4o.dms.kubota.masters.service.jobcard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "SV_MT_CATEGORY")
public class ServiceMtCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "category  can't be null")
    @Size(max = 100)
    private String category;

    @NotBlank(message = "status  can't be null")
    private String activeStatus;



}
