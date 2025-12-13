package com.i4o.dms.kubota.accpac.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@Entity
public class AccpacStockQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String itemNo;
    private String accpacLocationCode;
    private Integer quantity;
    private Date lastSyncDate;
}
