package com.i4o.dms.itldis.service.machinereinstallation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SV_REINSTALLATION_REPRESENTATIVE_INFO")
public class ServiceReinstallationRepresentativeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String representativeName;

    @Column(length = 50)
    private String representativeType;

    @JoinColumn(name="service_reinstallation_chassis_info_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ServiceReinstallationChassisInfo serviceReinstallationChassisInfo;

}


