/*
package com.i4o.dms.itldis.salesandpresales.grn.domain;

import com.i4o.dms.itldis.masters.products.domain.MachineMaster;
import com.i4o.dms.itldis.masters.usermanagement.dealerusers.domain.DealerMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MachineStockInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalQuantity;

    @ManyToOne
    @JoinColumn(name = "machine_master_id")
    private MachineMaster machineMaster;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;

}
*/
