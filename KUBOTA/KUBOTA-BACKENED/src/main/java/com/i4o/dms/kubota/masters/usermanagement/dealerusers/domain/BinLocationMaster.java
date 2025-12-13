package com.i4o.dms.kubota.masters.usermanagement.dealerusers.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="SP_STOCK_STORE_BIN_MASTER")
public class BinLocationMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String binLocation;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private StoreMaster storeMaster;

    private Long branchId;
}
