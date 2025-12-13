package com.i4o.dms.itldis.masters.service.servicebooking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "SV_MT_SOURCE_OF_BOOKING")
public class ServiceMtSourceOfBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sourceOfBooking;

    private String activeStatus;
}
