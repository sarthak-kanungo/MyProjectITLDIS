package com.i4o.dms.kubota.masters.service.checkpointspecification.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class CheckpointSpecification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String specification;

    @NotNull
    @Size(max=1)
    private String activeStatus;

    @NotNull
    private Integer sequenceNo;

}
