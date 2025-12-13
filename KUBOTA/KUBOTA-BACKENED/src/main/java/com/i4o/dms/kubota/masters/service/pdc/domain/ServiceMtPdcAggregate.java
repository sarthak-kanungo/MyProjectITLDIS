package com.i4o.dms.kubota.masters.service.pdc.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class ServiceMtPdcAggregate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status can't be blank")
    private String activeStatus;

    @Size(max = 100)
    @NotBlank(message = "aggregate type can't be blank")
    private String aggregate;

}
