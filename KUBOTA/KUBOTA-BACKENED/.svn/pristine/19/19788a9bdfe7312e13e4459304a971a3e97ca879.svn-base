package com.i4o.dms.kubota.masters.dbentities.service.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="SV_MT_MACHINE_INSTALLATION_TYPE")
public class ServiceMtMachineInstallationType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(unique = true)
//    private String installationTypes;

    @Column(unique = true)
    private String installationType;

    private String activeStatus;

}
