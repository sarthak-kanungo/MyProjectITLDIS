package com.i4o.dms.itldis.spares.purchase.grn.domain;

import com.i4o.dms.itldis.masters.spares.sparepartmaster.domain.SparePartMaster;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class BinningSlip { //incomplete
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spare_grn_id")
    private SparePartGrn sparePartGrn;

    @ManyToOne
    @JoinColumn(name = "item_no",referencedColumnName = "itemNo")
    private SparePartMaster sparePartMaster;
}
