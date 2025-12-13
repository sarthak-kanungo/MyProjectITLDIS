package com.i4o.dms.kubota.spares.inventorymanagement.bintransfer.domain;

import com.i4o.dms.kubota.masters.spares.sparepartmaster.domain.SparePartMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.BinLocationMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.DealerMaster;
import com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain.StoreMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity 
public class  BinTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //BBTB01/1920/0001
    private String binTransferNo = "BBT-"+System.currentTimeMillis();

    @Column(updatable = false)
    private Date binTransferDate = new Date();

    @ManyToOne
    @JoinColumn(name = "item_no",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    @ManyToOne
    @JoinColumn(name = "bin_location_id")
    private BinLocationMaster binLocationMaster;

    private Integer binStock;

    private Double unitPrice;

    private Boolean defaultBin = false;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private DealerMaster dealerMaster;
}
