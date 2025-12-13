package com.i4o.dms.itldis.service.machineinstallation.domain;


import com.i4o.dms.itldis.service.machineinstallation.domain.ServiceMachineInstallation;
import com.i4o.dms.itldis.service.mrc.domain.ServiceMrc;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="SV_INSTALLATION_PHOTOS")
public class ServiceInstallationPhotos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="service_installation_id")
    private ServiceMachineInstallation serviceMachineInstallationId;

    @NotNull
    @Column(length = 300)
    private String fileName;

    @Override
    public String toString() {
        return "ServiceInstallationPhotos{" +
                "id=" + id +
                ", serviceMachineInstallationId=" + serviceMachineInstallationId +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
