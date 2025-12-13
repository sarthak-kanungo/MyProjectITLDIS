package com.i4o.dms.itldis.masters.service.jobcard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "SV_MT_PLACE_OF_SERVICE")
public class ServiceMtPlaceOfService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "place of service   can't be blank")
    @Size(max = 100)
    private String placeOfService;

    @NotBlank(message = "status  can't be blank")
    private String activeStatus;
}
