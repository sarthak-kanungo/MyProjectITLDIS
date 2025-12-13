package com.i4o.dms.itldis.service.machinereinstallation.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.i4o.dms.itldis.salesandpresales.grn.domain.MachineInventory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="SV_REINSTALLATION_CHASSIS_INFO")
public class ServiceReinstallationChassisInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer representativeCount;

    @ManyToOne
    @JoinColumn(name="machine_inventory_id")
    private MachineInventory machineInventory;

    @JoinColumn(name="service_machine_reinstallation_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private ServiceMachineReinstallation serviceMachineReinstallation;

    @OneToMany(mappedBy = "serviceReinstallationChassisInfo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ServiceReinstallationRepresentativeInfo> serviceRepresentativeInfo;

}
