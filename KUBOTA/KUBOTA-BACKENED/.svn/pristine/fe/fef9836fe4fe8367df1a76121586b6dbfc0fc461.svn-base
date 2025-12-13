package com.i4o.dms.kubota.salesandpresales.sales.deliverychallan.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.i4o.dms.kubota.salesandpresales.grn.domain.MachineInventory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="SA_DELIVERY_MACHINE_DETAIL")
public class DcMachineDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_challan_id")
    @JsonBackReference
    private DeliveryChallan deliveryChallan;

    @ManyToOne
    @JoinColumn(name = "machine_inventory_id")
    private MachineInventory machineInventory;

    private Long quantity;

    private Boolean deleteFlag= false;

}
