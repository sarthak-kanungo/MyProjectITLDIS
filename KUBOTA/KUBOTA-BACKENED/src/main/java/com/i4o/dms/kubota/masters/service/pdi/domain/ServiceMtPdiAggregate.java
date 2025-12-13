package com.i4o.dms.kubota.masters.service.pdi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
public class ServiceMtPdiAggregate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 1)
    @NotBlank(message = "active status cannot blank")
    private String active_status;

    @Size(max = 100)
    @NotBlank(message = "aggregate type cannot blank")
    private String aggregate;




}



