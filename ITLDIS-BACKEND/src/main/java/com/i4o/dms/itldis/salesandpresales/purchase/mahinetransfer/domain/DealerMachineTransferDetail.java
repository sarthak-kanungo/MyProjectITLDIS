package com.i4o.dms.itldis.salesandpresales.purchase.mahinetransfer.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.i4o.dms.itldis.masters.products.domain.MachineMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name="SA_MACHINE_TRANSFER_DTL")
public class DealerMachineTransferDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private MachineMaster machineMaster;

    @NotNull
    private Integer quantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "dealer_machine_transfer_id")
    private DealerMachineTransfer dealerMachineTransfer;
}
